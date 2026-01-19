package br.com.drs.radio.service;

import br.com.drs.radio.dto.ArtistaDTO;
import br.com.drs.radio.model.Artista;
import br.com.drs.radio.repository.ArtistaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public Artista salvar(Artista artista){
        return artistaRepository.save(artista);
    }

    public List<Artista> findAll() {
        return artistaRepository.findAll();
    }

    public Optional<Artista> findByNome(String nome) {
        return artistaRepository.findByNome(nome);
    }

    public Artista atualizar(Artista artista, Long id) {
        artista.setId(id);
        return artistaRepository.save(artista);
    }
}
