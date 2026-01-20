package br.com.drs.radio.model;

import br.com.drs.radio.model.enuns.Estilo;
import br.com.drs.radio.model.enuns.Periodo;
import br.com.drs.radio.model.enuns.Repeticao;
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
@Table(name = "musica_db")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Artista artista;

    private String nome;

    private List<String> compositor;

    private LocalTime tempoMusica;

    private LocalTime introcucao;

    @Column(length = 4)
    private Integer anoLancamento;

    @ElementCollection
    @CollectionTable(name = "musica_estilos", joinColumns = @JoinColumn(name = "musica_id"))
    @Enumerated(EnumType.STRING)
    private List<Estilo> estilos;

    private List<Periodo> periodos;

    @OneToMany
    private List<Programa> programas;

    private Repeticao repeticao;

    private int quantidade;

    private String observacao;

    private Boolean nacional;

    private Boolean ativo;

    public long getDuracaoEmSegundos() {
        if (tempoMusica == null) return 0;
        return (tempoMusica.getHour() * 3600L) +
                (tempoMusica.getMinute() * 60L) +
                (tempoMusica.getSecond());
    }
}
