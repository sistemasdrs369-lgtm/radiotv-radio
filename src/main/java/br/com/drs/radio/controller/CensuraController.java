package br.com.drs.radio.controller;

import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/censura")
@CrossOrigin(origins = "*") // Para seu React conseguir acessar
public class CensuraController {

    private final String PATH_GRAVACOES = "C:/radio/censura/";

    @GetMapping("/arquivos/{data}")
    public List<String> listarArquivos(@PathVariable String data) {
        File pasta = new File(PATH_GRAVACOES);
        File[] arquivos = pasta.listFiles();

        if (arquivos == null) return List.of();

        // Filtra arquivos que começam com a data (ex: 22012026)
        return Arrays.stream(arquivos)
                .map(File::getName)
                .filter(nome -> nome.startsWith(data))
                .sorted() // Garante a ordem cronológica
                .collect(Collectors.toList());
    }
}