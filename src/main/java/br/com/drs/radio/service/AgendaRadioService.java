package br.com.drs.radio.service;

import br.com.drs.radio.model.AgendaRadio;
import br.com.drs.radio.repository.AgendaRadioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgendaRadioService {

    private final AgendaRadioRepository agendaRadioRepository;

    public AgendaRadio save(@RequestBody AgendaRadio agendaRadio) {
        return agendaRadioRepository.save(agendaRadio);
    }

    public List<AgendaRadio> findByDataOrderByHorarioAsc(LocalDate data) {
        return agendaRadioRepository.findByDataOrderByHorarioAsc(data);
    }

    public AgendaRadio atualizar(@PathVariable Long id, @RequestBody AgendaRadio agendaRadio) {
        if(agendaRadio.getId() == null) {
            throw new EntityNotFoundException("Agenda não encontrada");
        }
        return agendaRadioRepository.save(agendaRadio);
    }

    public AgendaRadio remarcarHoras(Long id, int horas) {
        Optional<AgendaRadio> agendaOptional = agendaRadioRepository.findById(id);
        if (agendaOptional.isPresent()) {
            AgendaRadio agenda = agendaOptional.get();
            LocalTime novoHorario = agenda.getHorario().plusHours(horas);
            agenda.setHorario(novoHorario);
            return agendaRadioRepository.save(agenda);
        }
        return null;
    }

    public AgendaRadio remarcaDias(Long id, int dias) {
        Optional<AgendaRadio> agendaOptional = agendaRadioRepository.findById(id);
        if (agendaOptional.isPresent()) {
            AgendaRadio agenda = agendaOptional.get();
            LocalDate novoDia = agenda.getData().plusDays(dias);
            agenda.setData(novoDia);
            return agendaRadioRepository.save(agenda);
        }
        return null;
    }

    public List<AgendaRadio> marcarVariosMeses(Long id, int quantidadeMeses) {
        Optional<AgendaRadio> originalOpt = agendaRadioRepository.findById(id);
        List<AgendaRadio> novosAgendamentos = new ArrayList<>();
        if (originalOpt.isPresent()) {
            AgendaRadio original = originalOpt.get();
            for (int i = 1; i <= quantidadeMeses; i++) {
                AgendaRadio novaAgenda = new AgendaRadio();
                novaAgenda.setConteudo(original.getConteudo());
                novaAgenda.setHorario(original.getHorario());
                novaAgenda.setData(original.getData().plusMonths(i));
                novaAgenda.setAtivo(true);
                novosAgendamentos.add(agendaRadioRepository.save(novaAgenda));
            }
        }
        return novosAgendamentos;
    }
}
