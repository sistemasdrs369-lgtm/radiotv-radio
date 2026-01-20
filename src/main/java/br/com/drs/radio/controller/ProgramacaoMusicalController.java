package br.com.drs.radio.controller;

import br.com.drs.radio.dto.ProgramacaoRequestDTO;
import br.com.drs.radio.model.ProgramacaoMusical;
import br.com.drs.radio.service.ProgramacaoMusicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/programacaoMusical")
@RequiredArgsConstructor
public class ProgramacaoMusicalController {

    private final ProgramacaoMusicalService programacaoMusicalService;

    @PostMapping
    public ResponseEntity<ProgramacaoMusical> salvar(@RequestBody ProgramacaoRequestDTO request) {
        ProgramacaoMusical salva = programacaoMusicalService.salvarProgramacao(
                request.getProgramacaoMusical(),
                request.getConfigRadio()
        );
        return ResponseEntity.ok().body(salva);
    }
}
