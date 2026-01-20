package br.com.drs.radio.mapper;

import br.com.drs.radio.dto.ConfigRadioDTO;
import br.com.drs.radio.model.ConfigRadio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfigRadioMapper {

    ConfigRadioDTO toDTO (ConfigRadio configRadio);
    ConfigRadio toEntity (ConfigRadioDTO configRadioDTO);
}
