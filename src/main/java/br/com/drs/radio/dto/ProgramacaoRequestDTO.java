package br.com.drs.radio.dto;

import br.com.drs.radio.model.ConfigRadio;
import br.com.drs.radio.model.ProgramacaoMusical;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramacaoRequestDTO {
    private ProgramacaoMusical programacaoMusical;
    private ConfigRadio configRadio;
}