package br.com.drs.radio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlocoHorario {
    private String horario;
    private String programa;
    private List<Integer> musicas;
    private int duracaoAlvo;

    public BlocoHorario(String horario, String programa, int duracaoAlvo) {
        this.horario = horario;
        this.programa = programa;
        this.duracaoAlvo = duracaoAlvo;
    }
}
