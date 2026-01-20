package br.com.drs.radio.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Especiais {

    NACIONAL("Nacional"),
    SERTANEJO("Sertanejo"),
    MADRUGADA("Madrugada"),
    ROCK("Rock");

    private String descricao;
}
