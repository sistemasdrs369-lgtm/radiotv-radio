package br.com.drs.radio.mapper;

import br.com.drs.radio.dto.ProgramaDTO;
import br.com.drs.radio.model.Programa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProgramaMapper {

    Programa toEntity(ProgramaDTO programaDTO);
    ProgramaDTO toDTO(Programa programa);
}
