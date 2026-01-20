package br.com.drs.radio.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "configRadio_db")
public class ConfigRadio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String placaSomMesa;

    public String placaSomRetorno;

    public String placaSomReserva;

    public Integer intervalosComercial;

    public Integer duracaoBreakComercial;

    public Integer intervaloMusical;

    public Integer duracaoMusical;

    private String pastaRoteiroMusical;

    private String pastaRoteiroComercial;

    private String pastaMusicas;
}
