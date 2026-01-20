package br.com.drs.radio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfigRadioDTO {

    public String placaSomMesa;

    public String placaSomRetorno;

    public String placaSomReserva;

    public int intervalosComercial;

    public int duracaoBreakComercial;

    public int intervaloMusical;

    public int duracaoMusical;
}
