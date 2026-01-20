package br.com.drs.radio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "blocoHorario_db")
public class BlocoHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String horario;

    public String programa;

    public List<Integer> musicas = new ArrayList<>();

    public List<Integer> comerciais = new ArrayList<>();

    public int duracaoAlvo;

    public BlocoHorario(String horario, String programa, int duracaoAlvo) {
        this.horario = horario;
        this.programa = programa;
        this.duracaoAlvo = duracaoAlvo;
    }
}
