package br.com.drs.radio.mapper;

import br.com.drs.radio.dto.AgendaRadioDTO;
import br.com.drs.radio.model.AgendaRadio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendaRadioMapper {

    AgendaRadioDTO toDto(AgendaRadio agendaRadio);

    AgendaRadio toEntity(AgendaRadioDTO dto);
}
