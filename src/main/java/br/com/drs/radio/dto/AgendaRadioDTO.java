package br.com.drs.radio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRadioDTO {

    private LocalDate data;

    private LocalTime horario;

    private String conteudo;

    private boolean ativo;
}
