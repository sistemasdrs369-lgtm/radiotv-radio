package br.com.drs.radio.controller;

import br.com.drs.radio.model.RegistrarMusica;
import br.com.drs.radio.service.RegistrarMusicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrarMusica")
@RequiredArgsConstructor
public class RegistrarMusicaController {

    private final RegistrarMusicaService registrarMusicaService;

    @PostMapping
    public ResponseEntity<RegistrarMusica> salvarRegistro(@RequestBody RegistrarMusica registrarMusica) {
        return ResponseEntity.ok(registrarMusicaService.salvarRegistro(registrarMusica));
    }

    @GetMapping
    public List<RegistrarMusica> listarRegistroMusicas() {
        return registrarMusicaService.listarRegistroMusicas();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrarMusica> atualizar(@RequestBody RegistrarMusica registrarMusica, @PathVariable Long id) {
        registrarMusica.setId(id);
        registrarMusicaService.salvarRegistro(registrarMusica);
        return ResponseEntity.ok(registrarMusica);
    }
}
