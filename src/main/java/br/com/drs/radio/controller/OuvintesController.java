package br.com.drs.radio.controller;

import br.com.drs.radio.model.Ouvintes;
import br.com.drs.radio.service.OuvintesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/ouvintes")
@RequiredArgsConstructor
public class OuvintesController {

    private final OuvintesService ouvintesService;

    @PostMapping
    // ADICIONADO @RequestBody aqui
    public ResponseEntity<Ouvintes> salvarOuvintes(@RequestBody Ouvintes ouvintes){
        Ouvintes salvo = ouvintesService.salvarOuvintes(ouvintes);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Ouvintes> buscarOuvintes(@PathVariable String nome){
        return ouvintesService.buscarPorNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ouvintes> atualizar(@PathVariable Long id, @RequestBody Ouvintes ouvintes){
        return ResponseEntity.ok(ouvintesService.atualizar(id, ouvintes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOuvintes(@PathVariable Long id){
        ouvintesService.deletarOuvintes(id); // Faltava chamar o service
        return ResponseEntity.noContent().build();
    }
}
