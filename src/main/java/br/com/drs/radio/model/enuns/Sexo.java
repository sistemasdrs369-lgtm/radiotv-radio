package br.com.drs.radio.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sexo {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NAOINFORMADO("Não Informado");

    private String descricao;
}
