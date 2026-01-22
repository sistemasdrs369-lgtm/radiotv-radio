package br.com.drs.radio.repository;

import br.com.drs.radio.model.Ouvintes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OuvintesRepository extends JpaRepository<Ouvintes, Long> {
    Optional<Ouvintes> findByNomeOuvinte(String nomeOuvinte);
}
