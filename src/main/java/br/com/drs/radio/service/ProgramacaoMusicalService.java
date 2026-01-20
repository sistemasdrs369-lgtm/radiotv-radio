package br.com.drs.radio.service;

import br.com.drs.radio.model.BlocoHorario;
import br.com.drs.radio.model.ConfigRadio;
import br.com.drs.radio.model.Programa;
import br.com.drs.radio.model.ProgramacaoMusical;
import br.com.drs.radio.repository.ProgramaRepository;
import br.com.drs.radio.repository.ProgramacaoMusicalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgramacaoMusicalService {

    private final ProgramacaoMusicalRepository repository;
    private final ProgramaRepository programaRepository;
    private final ObjectMapper objectMapper;

    public ProgramacaoMusical salvarProgramacao(ProgramacaoMusical programacaoMusical, ConfigRadio configRadio) {
        List<BlocoHorario> grade = gerarGradeDoDia(configRadio, programacaoMusical.getDataProgramacao());
        salvarJson(grade, configRadio, programacaoMusical.getDataProgramacao());
        return repository.save(programacaoMusical);
    }

    public List<BlocoHorario> gerarGradeDoDia(ConfigRadio configRadio, LocalDate data) {
        List<BlocoHorario> gradeSugerida = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        List<Programa> todosProgramas = programaRepository.findAll().stream()
                .filter(p -> p.getAtivo() != null && p.getAtivo())
                .collect(Collectors.toList());

        LocalTime tempoAtual = LocalTime.MIDNIGHT;
        int intervaloPadrao = (configRadio.getIntervaloMusical() != null && configRadio.getIntervaloMusical() > 0)
                ? configRadio.getIntervaloMusical() : 60;

        // Usamos um while em vez de for para controlar o avanço do tempo manualmente
        while (tempoAtual.isBefore(LocalTime.MAX) && !tempoAtual.equals(LocalTime.MIDNIGHT.minusSeconds(1))) {
            Programa programaAtual = buscarProgramaNoBanco(tempoAtual, data, todosProgramas);

            if (programaAtual != null && programaAtual.getTemBreaks() != null && !programaAtual.getTemBreaks()) {
                // Caso 1: Programa SEM BREAKS (ex: A Voz do Brasil)
                // Calcula a duração total do programa em minutos
                long duracaoTotal = java.time.Duration.between(programaAtual.getHorarioInicio(), programaAtual.getHorarioTermino()).toMinutes();
                if (duracaoTotal < 0) duracaoTotal += 1440; // Trata virada de dia

                gradeSugerida.add(new BlocoHorario(
                        tempoAtual.format(dtf),
                        programaAtual.getNomePrograma(),
                        (int) duracaoTotal // Duração alvo é o tempo total do programa
                ));

                // Salta o tempo direto para o fim do programa
                tempoAtual = programaAtual.getHorarioTermino();
            } else {
                // Caso 2: Programação normal com breaks (fatiada de 15 em 15 min)
                String nomeProg = (programaAtual != null) ? programaAtual.getNomePrograma() : "Programação Musical";

                gradeSugerida.add(new BlocoHorario(
                        tempoAtual.format(dtf),
                        nomeProg,
                        configRadio.getDuracaoMusical()
                ));

                tempoAtual = tempoAtual.plusMinutes(intervaloPadrao);
            }

            // Condição de saída para evitar loop infinito na meia-noite
            if (tempoAtual.equals(LocalTime.MIDNIGHT)) break;
        }
        return gradeSugerida;
    }

    private Programa buscarProgramaNoBanco(LocalTime hora, LocalDate data, List<Programa> programas) {
        // Traduz o dia da semana do Java para o índice ou nome do seu Enum
        int diaSemanaJava = data.getDayOfWeek().getValue(); // 1 (Seg) a 7 (Dom)

        return programas.stream()
                .filter(p -> p.getDiasSemana().stream().anyMatch(d -> {
                    // Compara pelo ordinal (posição no Enum) para evitar erro de nomes (Inglês vs Português)
                    // Se seu Enum segue a ordem: SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO
                    return (d.ordinal() + 1) == diaSemanaJava;
                }))
                .filter(p -> {
                    LocalTime inicio = p.getHorarioInicio();
                    LocalTime fim = p.getHorarioTermino();

                    // Trata programas que terminam meia-noite
                    if (fim.equals(LocalTime.MIDNIGHT)) {
                        return !hora.isBefore(inicio);
                    }

                    if (inicio.isBefore(fim)) {
                        // Horário normal (ex: 08:00 as 12:00)
                        return (hora.equals(inicio) || hora.isAfter(inicio)) && hora.isBefore(fim);
                    } else {
                        // Horário que vira o dia (ex: 22:00 as 02:00)
                        return (hora.equals(inicio) || hora.isAfter(inicio)) || hora.isBefore(fim);
                    }
                })
                .findFirst()
                .orElse(null);
    }

    public void salvarJson(List<BlocoHorario> grade, ConfigRadio config, LocalDate data) {
        String nomeArquivo = "programacaoMusical_" + data.toString() + ".json";
        String pasta = (config.getPastaRoteiroMusical() != null) ? config.getPastaRoteiroMusical() : "roteiros";

        File diretorio = new File(pasta);
        if (!diretorio.exists()) diretorio.mkdirs();

        File arquivo = new File(diretorio, nomeArquivo);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, grade);
        log.info("Roteiro salvo em: " + arquivo.getAbsolutePath());
    }
}