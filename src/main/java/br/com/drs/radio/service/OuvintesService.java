package br.com.drs.radio.service;

import br.com.drs.radio.model.Ouvintes;
import br.com.drs.radio.repository.OuvintesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OuvintesService {

    private final OuvintesRepository ouvintesRepository;

    public Ouvintes salvarOuvintes(@RequestBody Ouvintes ouvintes) {
        ouvintesRepository.save(ouvintes);
        return ouvintes;
    }

    public Optional<Ouvintes> buscarPorNome(@PathVariable String nome) {
        return ouvintesRepository.findByNomeOuvinte(nome);
    }

    public Ouvintes atualizar(@PathVariable Long id, Ouvintes ouvintes) {
        if(ouvintesRepository.findById(id).isPresent()) {
            return ouvintesRepository.save(ouvintes);
        }
        throw new EntityNotFoundException("Ouvinte não encontrado");
    }

    public void deletarOuvintes(Long id) {
        ouvintesRepository.deleteById(id);
    }
}
