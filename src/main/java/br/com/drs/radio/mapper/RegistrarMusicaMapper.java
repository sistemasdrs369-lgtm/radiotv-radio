package br.com.drs.radio.mapper;

import br.com.drs.radio.dto.RegistrarMusicaDTO;
import br.com.drs.radio.model.RegistrarMusica;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrarMusicaMapper {

    RegistrarMusicaDTO toDTO(RegistrarMusica registrarMusica);
    RegistrarMusica toEntity(RegistrarMusicaDTO dto);
}
