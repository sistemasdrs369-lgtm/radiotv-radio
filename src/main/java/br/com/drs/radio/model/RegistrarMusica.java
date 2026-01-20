package br.com.drs.radio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "registrarMusica_db", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"musica", "cantor"})
})
public class RegistrarMusica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String musica;

    @Column(nullable = false)
    private String cantor;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime tempoMusica;

    private LocalDate dataInicio;

    private LocalDate dataFinal;
}
