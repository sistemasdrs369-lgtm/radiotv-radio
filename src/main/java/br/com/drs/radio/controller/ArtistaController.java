package br.com.drs.radio.controller;

import br.com.drs.radio.model.Artista;
import br.com.drs.radio.service.ArtistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/artista")
@RequiredArgsConstructor
public class ArtistaController {

    private final ArtistaService artistaService;

    @PostMapping
    public Artista salvar(@RequestBody Artista artista){
        return artistaService.salvar(artista);
    }

    @GetMapping
    public List<Artista> findAll() {
        return artistaService.findAll();
    }

    @GetMapping("/{nome}")
    public Optional<Artista> findByNome(@PathVariable String nome){
        return artistaService.findByNome(nome);
    }

    @PutMapping("/{id}")
    public Artista atualizar(@PathVariable Long id, @RequestBody Artista artista){
        artista.setId(id);
        return artistaService.salvar(artista);
    }
}
