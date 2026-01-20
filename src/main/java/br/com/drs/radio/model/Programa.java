package br.com.drs.radio.model;

import br.com.drs.radio.model.enuns.DiasSemana;
import br.com.drs.radio.model.enuns.Especiais;
import br.com.drs.radio.model.enuns.TipoPrograma;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "programas_db")
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePrograma;

    private LocalTime horarioInicio;

    private LocalTime horarioTermino;

    @ElementCollection(targetClass = DiasSemana.class)
    @CollectionTable(name = "programas_dias_semana", joinColumns = @JoinColumn(name = "programa_id"))
    @Enumerated(EnumType.STRING)
    private List<DiasSemana> diasSemana;

    private Boolean feriado;

    private Boolean breakProprio;

    private TipoPrograma tipoPrograma;

    @ElementCollection(targetClass = Especiais.class)
    @CollectionTable(name = "programas_especiais", joinColumns = @JoinColumn(name = "programa_id"))
    @Enumerated(EnumType.STRING)
    private List<Especiais> especias;

    private Boolean ativo;
}
