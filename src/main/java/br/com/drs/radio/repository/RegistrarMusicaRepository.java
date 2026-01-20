package br.com.drs.radio.repository;

import br.com.drs.radio.model.RegistrarMusica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrarMusicaRepository extends JpaRepository<RegistrarMusica, Long> {

    boolean existsByMusicaAndCantor(String musica, String cantor);
}