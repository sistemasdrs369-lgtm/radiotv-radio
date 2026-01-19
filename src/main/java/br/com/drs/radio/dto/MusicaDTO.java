package br.com.drs.radio.dto;

import br.com.drs.radio.model.Artista;
import br.com.drs.radio.model.enuns.Estilo;
import br.com.drs.radio.model.enuns.Periodo;
import br.com.drs.radio.model.enuns.Programas;
import br.com.drs.radio.model.enuns.Repeticao;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
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
public class MusicaDTO {

    private Artista artista;

    private String nome;

    private List<String> compositor;

    private LocalTime tempoMusica;

    private LocalTime introcucao;

    @Column(length = 4)
    private String anoLancamento;

    private List<Estilo> estilos;

    private List<Periodo> periodos;

    private List<Programas>  programas;

    private Repeticao repeticao;

    private int quantidade;

    private String observacao;

    private Boolean ativo;
}
