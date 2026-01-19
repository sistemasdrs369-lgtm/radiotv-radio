package br.com.drs.radio.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Estilo {

    POP("Pop"),
    ROCK("Rock"),
    METAL("Metal"),
    HIP_HOP("Hip Hop"),
    RAP("Rap"),
    JAZZ("Jazz"),
    BLUES("Blues"),
    COUNTRY("Country"),
    ELETRONICA("Eletrônica"),
    REGGAE("Reggae"),
    SERTANEJO("Sertanejo"),
    MPB("MPB"),
    SAMBA("Samba"),
    PAGODE("Pagode"),
    FORRO("Forró"),
    AXE("Axé"),
    FUNK("Funk"),
    CLASSICA("Clássica"),
    LOFI("Lo-fi"),
    INDIE("Indie");

    private final String descricao;
}