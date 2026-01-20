package br.com.drs.radio.repository;

import br.com.drs.radio.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {

    Optional<Programa> findBynomePrograma(String nomePrograma);

    Optional<Programa> findByHorarioInicioAndHorarioTermino(LocalTime horarioInicio, LocalTime horarioTermino);
}
