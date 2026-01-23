package br.com.drs.radio.repository;

import br.com.drs.radio.model.AgendaRadio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AgendaRadioRepository extends JpaRepository<AgendaRadio, Long> {

    List<AgendaRadio> findByDataOrderByHorarioAsc(LocalDate data);
}
