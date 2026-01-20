package br.com.drs.radio.service;

import br.com.drs.radio.model.ConfigRadio;
import br.com.drs.radio.model.RegistrarMusica;
import br.com.drs.radio.repository.RegistrarMusicaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrarMusicaService {

    private final RegistrarMusicaRepository registrarMusicaRepository;

    public RegistrarMusica salvarRegistro(@RequestBody RegistrarMusica registrarMusica) {
        return registrarMusicaRepository.save(registrarMusica);
    }

    public List<RegistrarMusica> listarRegistroMusicas() {
        return registrarMusicaRepository.findAll();
    }

    public RegistrarMusica atualizarRegistro(@PathVariable Long id, @RequestBody RegistrarMusica registrarMusica ) {
        if(!registrarMusicaRepository.existsById(id)) {
            throw new EntityNotFoundException("Registro não encontrado com o Id: " + registrarMusica.getId());
        }
        return registrarMusicaRepository.save(registrarMusica);
    }
}