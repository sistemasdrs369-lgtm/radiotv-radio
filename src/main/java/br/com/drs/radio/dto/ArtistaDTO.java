package br.com.drs.radio.dto;

import br.com.drs.radio.model.enuns.Classificacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistaDTO {

    private Classificacao classificacao;

    private String nome;

    private List<String> formacao;

    private LocalDate inicio;

    private Boolean nacional;

    private String Observacao;
}
