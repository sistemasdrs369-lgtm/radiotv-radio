package br.com.drs.radio.controller;

import br.com.drs.radio.model.Musica;
import br.com.drs.radio.model.enuns.Estilo;
import br.com.drs.radio.service.MusicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/musica")
@RequiredArgsConstructor
public class MusicaController {

    private final MusicaService musicaService;

    @PostMapping
    public Musica cadastrarMusica(@RequestBody Musica musica){
        return musicaService.salvar(musica);
    }

    @GetMapping
    public List<Musica> listarMusicas(){
        return musicaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Musica> buscarMusicaPorId(@PathVariable Long id){
        return musicaService.findByid(id);
    }

    @GetMapping("/{nome}")
    public Optional<Musica> listarMusicasPorNome(@PathVariable String nome){
        return musicaService.findByNome(nome);
    }

    @GetMapping("/{ano}")
    public Optional<Musica> listarMusicasPorAno(@PathVariable Integer ano){
        return musicaService.findByAno(ano);
    }

    @GetMapping("/{estilo}")
    public Optional<Musica> listarMusicasPorEstilo(@PathVariable Estilo estilo){
        return (Optional<Musica>) musicaService.findByEstilos(estilo);
    }

    @PutMapping("/{id}")
    public Musica atualizarMusica(@PathVariable Long id, @RequestBody Musica musica){
        musica.setId(id);
        return musicaService.salvar(musica);
    }
}
