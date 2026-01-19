package br.com.drs.radio.mapper;

import br.com.drs.radio.dto.ArtistaDTO;
import br.com.drs.radio.model.Artista;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistaMapper {

    ArtistaDTO toDTO(Artista artista);
    Artista toArtista(ArtistaDTO artistaDTO);
}
