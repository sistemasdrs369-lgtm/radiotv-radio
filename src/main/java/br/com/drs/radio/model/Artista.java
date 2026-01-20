package br.com.drs.radio.model;

import br.com.drs.radio.model.enuns.Classificacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "artista_db")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private List<String> formacao;

    @Column(length = 4)
    private String inicio;

    private Boolean nacional;

    private String Observacao;
}
