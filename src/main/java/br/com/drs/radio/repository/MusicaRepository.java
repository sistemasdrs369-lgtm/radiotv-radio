package br.com.drs.radio.repository;

import br.com.drs.radio.model.Musica;
import br.com.drs.radio.model.enuns.Estilo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

    Optional<Musica> findByNome(String nome);

    List<Musica> findByEstilosContaining(Estilo estilo);

    Optional<Musica> findByAnoLancamento(int anoLancamento);

    Optional<Musica> findById(Long id);

    List<Musica> findByAtivoTrue();
}
