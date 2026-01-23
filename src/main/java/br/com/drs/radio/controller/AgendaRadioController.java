package br.com.drs.radio.controller;

import br.com.drs.radio.model.AgendaRadio;
import br.com.drs.radio.service.AgendaRadioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/agenda")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AgendaRadioController {

    private final AgendaRadioService agendaRadioService;

    @PostMapping
    public ResponseEntity<AgendaRadio> criar(@RequestBody AgendaRadio agendaRadio) {
        return ResponseEntity.ok(agendaRadioService.save(agendaRadio));
    }

    @GetMapping("/{data}")
    public ResponseEntity<List<AgendaRadio>> listarPorData(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(agendaRadioService.findByDataOrderByHorarioAsc(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaRadio> atualizar(@PathVariable Long id, @RequestBody AgendaRadio agendaRadio) {
        // Garante que o ID da URL seja o mesmo do objeto
        agendaRadio.setId(id);
        return ResponseEntity.ok(agendaRadioService.atualizar(id, agendaRadio));
    }

    @PatchMapping("/{id}/remarcar-horas")
    public ResponseEntity<AgendaRadio> remarcarHoras(@PathVariable Long id, @RequestParam int horas) {
        AgendaRadio atualizada = agendaRadioService.remarcarHoras(id, horas);
        return atualizada != null ? ResponseEntity.ok(atualizada) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/remarcar-dias")
    public ResponseEntity<AgendaRadio> remarcarDias(@PathVariable Long id, @RequestParam int dias) {
        AgendaRadio atualizada = agendaRadioService.remarcaDias(id, dias);
        return atualizada != null ? ResponseEntity.ok(atualizada) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/replicar-meses")
    public ResponseEntity<List<AgendaRadio>> marcarVariosMeses(@PathVariable Long id, @RequestParam int meses) {
        List<AgendaRadio> novasAgendas = agendaRadioService.marcarVariosMeses(id, meses);
        return ResponseEntity.ok(novasAgendas);
    }
}