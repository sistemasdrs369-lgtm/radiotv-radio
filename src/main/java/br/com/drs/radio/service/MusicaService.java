package br.com.drs.radio.service;

import br.com.drs.radio.model.Musica;
import br.com.drs.radio.model.enuns.Estilo;
import br.com.drs.radio.repository.MusicaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicaService {

    private final MusicaRepository musicaRepository;

    public Musica salvar(Musica musica) {
        return musicaRepository.save(musica);
    }

    public List<Musica> findAll() {
        return musicaRepository.findAll();
    }

    public Optional<Musica> findByid(Long id) {
        return musicaRepository.findById(id);
    }

    public Optional<Musica> findByNome(String nome) {
        return musicaRepository.findByNome(nome);
    }

    public Optional<Musica> findByAno(Integer ano) {
        return musicaRepository.findByAnoLancamento(ano);
    }

    public Object findByEstilos(Estilo estilo) {
        try {
            Estilo estiloEnum = Estilo.valueOf(String.valueOf(estilo));
            return musicaRepository.findByEstilosContaining(estilo);
        } catch (IllegalArgumentException e) {
            log.error("Estilo musical inválido: {}", estilo);
            return Optional.empty();
        }
    }

    public Musica atualizar(Musica musica, Long id) {
        musica.setId(id);
        return musicaRepository.save(musica);
    }
}
