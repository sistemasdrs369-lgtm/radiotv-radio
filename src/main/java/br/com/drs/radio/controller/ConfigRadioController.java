package br.com.drs.radio.controller;

import br.com.drs.radio.model.ConfigRadio;
import br.com.drs.radio.service.ConfigRadioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configRadio")
@RequiredArgsConstructor
public class ConfigRadioController {

    private final ConfigRadioService configRadioService;

    @PostMapping
    public ResponseEntity<ConfigRadio> save(@RequestBody ConfigRadio configRadio){
        configRadioService.salvarConfiguracao(configRadio);
        return ResponseEntity.ok().body(configRadio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfigRadio> atualizar(@PathVariable Long id, @RequestBody ConfigRadio configRadio) {
        configRadio.setId(id);
        ConfigRadio atualizado = configRadioService.atualizarConfiguracao(configRadio);
        return ResponseEntity.ok(atualizado);
    }
}
