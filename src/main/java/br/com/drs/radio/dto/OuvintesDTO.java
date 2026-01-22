package br.com.drs.radio.dto;

import br.com.drs.radio.model.enuns.Sexo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OuvintesDTO {

    private String nomeOuvinte;

    private String telefone;

    private String celular;

    private LocalDate dataParticipacao;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;
}
