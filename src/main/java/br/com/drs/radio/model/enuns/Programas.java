package br.com.drs.radio.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Programas {

    JORNALEP("Jornal da EP"),
    EXPRESSONACIONAL("Expresso Nacional"),
    EPHORAEXTRA("EP Hora Extra"),
    EPBANANASHOW("EP Banana Show"),
    RETROVISOREP("Retrovisor EP"),
    EPMIXDESUCESSOS("EP Mix de Sucessos"),
    EPLIGHT("EP Light"),
    POWEREP("Power EP"),
    EPALTAVOLTAGEM("EP Alta Voltagem"),
    EPREWORK("EP Rework"),
    EPSUPERSUNDAY("EP Super Sunday");

    private String descricao;
}
