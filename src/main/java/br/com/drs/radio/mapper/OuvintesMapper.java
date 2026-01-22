package br.com.drs.radio.mapper;

import br.com.drs.radio.dto.OuvintesDTO;
import br.com.drs.radio.model.Ouvintes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OuvintesMapper {

    Ouvintes toEntity(OuvintesDTO dto);

    OuvintesDTO toDTO(Ouvintes ouvintes);
}
