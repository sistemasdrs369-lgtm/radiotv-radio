package br.com.drs.radio.service;

import br.com.drs.radio.model.Programa;
import br.com.drs.radio.repository.ProgramaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgramaService {

    private final ProgramaRepository programaRepository;

    public Programa save(@RequestBody Programa programa) {
        return programaRepository.save(programa);
    }

    public List<Programa> findAll() {
        return programaRepository.findAll();
    }

    public Optional<Programa> findBynomePrograma(String nomePrograma) {
        return programaRepository.findBynomePrograma(nomePrograma);
    }

    public Optional<Programa> findByHorarioInicioAndHorarioTermino(LocalTime horarioInicio, LocalTime horarioTermino) {
        return programaRepository.findByHorarioInicioAndHorarioTermino(horarioInicio, horarioTermino);
    }

    public Programa atualizarPrograma(@RequestBody Programa programa, @PathVariable Long id) {
        programa.setId(programa.getId());
        return programaRepository.save(programa);
    }

    public Programa inativarPrograma(Programa programa) {
        programa.setAtivo(false);
        return programaRepository.save(programa);
    }
}
