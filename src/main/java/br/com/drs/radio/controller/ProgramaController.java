package br.com.drs.radio.controller;

import br.com.drs.radio.model.Programa;
import br.com.drs.radio.repository.ProgramaRepository;
import br.com.drs.radio.service.ProgramaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/programas")
@RequiredArgsConstructor
public class ProgramaController {

    private final ProgramaService programaService;
    private final ProgramaRepository programaRepository;

    @PostMapping
    public Programa salvarPrograma(@RequestBody Programa programa){
        return programaService.save(programa);
    }

    @GetMapping
    public List<Programa> listarProgramas(){
        return programaService.findAll();
    }

    @GetMapping("/{nomePrograma}")
    public Optional<Programa> buscarPrograma(@PathVariable String nomePrograma){
        return programaService.findBynomePrograma(nomePrograma);
    }

    @GetMapping("/{horarioInicio}/{horarioTermino}")
    public Optional<Programa> buscarPorInicioTermino(@PathVariable LocalTime horarioInicio, @PathVariable LocalTime horarioTermino){
        return programaService.findByHorarioInicioAndHorarioTermino(horarioInicio, horarioTermino);
    }

    @PutMapping("/{id}")
    public Programa atualizarPrograma(@PathVariable Long id, @RequestBody Programa programa){
        programa.setId(id);
        return programaService.save(programa);
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<Programa> inativar(@PathVariable Long id) {
        return programaRepository.findById(id)
                .map(programa -> {
                    Programa inativado = programaService.inativarPrograma(programa);
                    return ResponseEntity.ok(inativado);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
