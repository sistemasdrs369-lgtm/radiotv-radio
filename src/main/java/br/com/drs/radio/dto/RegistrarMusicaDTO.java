package br.com.drs.radio.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarMusicaDTO {

    private String musica;

    private String cantor;

    private LocalTime tempoMusica;

    private LocalDate dataInicio;

    private LocalDate dataFinal;
}
