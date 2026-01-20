package br.com.drs.radio.service;

import br.com.drs.radio.model.BlocoHorario;
import br.com.drs.radio.model.ConfigRadio;
import br.com.drs.radio.model.Programa;
import br.com.drs.radio.model.ProgramacaoMusical;
import br.com.drs.radio.model.enuns.DiasSemana;
import br.com.drs.radio.repository.ProgramaRepository;
import br.com.drs.radio.repository.ProgramacaoMusicalRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgramacaoMusicalService {

    private final ProgramacaoMusicalRepository repository;
    private final ProgramaRepository programaRepository;
    private final ObjectMapper objectMapper;

    public ProgramacaoMusical salvarProgramacao(ProgramacaoMusical programacaoMusical, ConfigRadio configRadio) {
        List<BlocoHorario> grade = gerarGradeDoDia(configRadio, programacaoMusical.getDataProgramacao());
        try {
            salvarJson(grade, configRadio, programacaoMusical.getDataProgramacao());
        } catch (IOException e) {
            log.error("Erro ao salvar arquivo JSON: {}", e.getMessage());
        }
        return repository.save(programacaoMusical);
    }

    // Usamos JsonNode para ler o arquivo sem precisar das classes Musica/Artista
    private JsonNode catalogoJson;
    private final Set<Integer> idsTocadosNoDia = new HashSet<>();

    @PostConstruct
    public void carregarMusicas() {
        try {
            File file = new File("C:\\RadioTv\\Musicas.json");
            if (file.exists()) {
                // Tenta ler o catálogo
                this.catalogoJson = objectMapper.readTree(file);
                System.out.println("SISTEMA: Catálogo carregado com sucesso.");
            } else {
                System.err.println("SISTEMA: Arquivo Musicas.json não encontrado.");
            }
        } catch (Exception e) {
            // Se houver erro de sintaxe, o sistema NÃO TRAVA mais
            System.err.println("--- ERRO NO ARQUIVO JSON ---");
            System.err.println("Verifique se faltam vírgulas no arquivo Musicas.json.");
            System.err.println("Detalhe técnico: " + e.getMessage());
            System.err.println("----------------------------");
            this.catalogoJson = null; // Garante que o catálogo fique vazio mas o sistema ligue
        }
    }

    public List<BlocoHorario> gerarGradeDoDia(ConfigRadio configRadio, LocalDate data) {
        List<BlocoHorario> gradeSugerida = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        idsTocadosNoDia.clear();

        List<Programa> todosProgramas = programaRepository.findAll().stream()
                .filter(p -> p.getAtivo() != null && p.getAtivo())
                .toList();

        LocalTime tempoAtual = LocalTime.MIDNIGHT;
        int intervaloPadrao = (configRadio.getIntervaloMusical() != null && configRadio.getIntervaloMusical() > 0) ? configRadio.getIntervaloMusical() : 60;
        DiasSemana diaEnumHoje = converterParaSeuEnum(data.getDayOfWeek());

        while (true) {
            Programa programaAtual = buscarProgramaNoBanco(tempoAtual, diaEnumHoje, todosProgramas);
            String nomeProg = (programaAtual != null) ? programaAtual.getNomePrograma() : "Programação Musical";

            BlocoHorario bloco = new BlocoHorario(tempoAtual.format(dtf), nomeProg, configRadio.getDuracaoMusical());
            bloco.setMusicas(new ArrayList<>());

            if (programaAtual != null && Boolean.FALSE.equals(programaAtual.getTemBreaks())) {
                bloco.setDuracaoAlvo(0);
                gradeSugerida.add(bloco);
                tempoAtual = programaAtual.getHorarioTermino();
            } else {
                preencherBloco(bloco);
                gradeSugerida.add(bloco);
                tempoAtual = tempoAtual.plusMinutes(intervaloPadrao);
            }

            if (tempoAtual.equals(LocalTime.MIDNIGHT)) break;
        }
        return gradeSugerida;
    }

    private void preencherBloco(BlocoHorario bloco) {
        if (catalogoJson == null || !catalogoJson.isArray()) return;

        long alvoSegundos = bloco.getDuracaoAlvo() * 60L;
        long acumulado = 0;

        // Criamos uma lista temporária para embaralhar a seleção
        List<JsonNode> musicas = new ArrayList<>();
        catalogoJson.forEach(musicas::add);
        Collections.shuffle(musicas);

        for (JsonNode m : musicas) {
            boolean ativo = m.get("ativo").asBoolean();
            boolean nacional = m.get("nacional").asBoolean();
            int id = m.get("id").asInt();
            String tempoStr = m.get("tempoMusica").asText();

            // Filtro de programa
            if (!ativo) continue;
            if ("Expresso Nacional".equalsIgnoreCase(bloco.getPrograma()) && !nacional) continue;

            long duracaoM = converterTempoParaSegundos(tempoStr);

            if (duracaoM > 0 && !idsTocadosNoDia.contains(id)) {
                if (acumulado + duracaoM <= alvoSegundos + 45) {
                    bloco.getMusicas().add(id);
                    idsTocadosNoDia.add(id);
                    acumulado += duracaoM;
                }
            }
            if (acumulado >= alvoSegundos) return;
        }
    }

    private long converterTempoParaSegundos(String tempo) {
        try {
            String[] partes = tempo.split(":");
            return Long.parseLong(partes[0]) * 3600 + Long.parseLong(partes[1]) * 60 + Long.parseLong(partes[2]);
        } catch (Exception e) { return 0; }
    }

    private DiasSemana converterParaSeuEnum(java.time.DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY -> DiasSemana.SEGUNDA;
            case TUESDAY -> DiasSemana.TERCA;
            case WEDNESDAY -> DiasSemana.QUARTA;
            case THURSDAY -> DiasSemana.QUINTA;
            case FRIDAY -> DiasSemana.SEXTA;
            case SATURDAY -> DiasSemana.SABADO;
            case SUNDAY -> DiasSemana.DOMINGO;
        };
    }

    private Programa buscarProgramaNoBanco(LocalTime hora, DiasSemana diaHoje, List<Programa> programas) {
        return programas.stream()
                .filter(p -> p.getDiasSemana() != null && p.getDiasSemana().contains(diaHoje))
                .filter(p -> {
                    LocalTime inicio = p.getHorarioInicio();
                    LocalTime fim = p.getHorarioTermino();
                    if (fim.equals(LocalTime.MIDNIGHT)) return !hora.isBefore(inicio);
                    if (inicio.isBefore(fim)) return (hora.equals(inicio) || hora.isAfter(inicio)) && hora.isBefore(fim);
                    else return (hora.equals(inicio) || hora.isAfter(inicio)) || hora.isBefore(fim);
                })
                .findFirst().orElse(null);
    }

    public void salvarJson(List<BlocoHorario> grade, ConfigRadio config, LocalDate data) throws IOException {
        String nomeArquivo = "programacaoMusical_" + data.toString() + ".json";
        String pasta = (config.getPastaRoteiroMusical() != null) ? config.getPastaRoteiroMusical() : "roteiros";
        File diretorio = new File(pasta);
        if (!diretorio.exists()) diretorio.mkdirs();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(diretorio, nomeArquivo), grade);
    }
}