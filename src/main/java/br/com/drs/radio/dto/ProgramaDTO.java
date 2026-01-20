package br.com.drs.radio.dto;

import br.com.drs.radio.model.enuns.DiasSemana;
import br.com.drs.radio.model.enuns.Especiais;
import br.com.drs.radio.model.enuns.TipoPrograma;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramaDTO {

    private String nomePrograma;

    private LocalTime horarioInicio;

    private LocalTime horarioTermino;

    private List<DiasSemana> diasSemana;

    private Boolean feriado;

    private Boolean breakProprio;

    private TipoPrograma tipoPrograma;

    private List<Especiais> especias;

    private Boolean ativo;
}
