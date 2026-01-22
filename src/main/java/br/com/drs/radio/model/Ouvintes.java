package br.com.drs.radio.model;

import br.com.drs.radio.model.enuns.Sexo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ouvintes_db")
public class Ouvintes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeOuvinte;

    private String telefone;

    private String celular;

    private LocalDate dataParticipacao;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;
}
