package br.com.drs.radio.mapper;

import br.com.drs.radio.dto.MusicaDTO;
import br.com.drs.radio.model.Musica;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicaMapper {

    Musica toMusica(MusicaDTO musicaDTO);
    MusicaDTO toDTO(Musica musica);
}
