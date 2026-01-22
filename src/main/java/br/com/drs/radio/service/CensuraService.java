package br.com.drs.radio.service;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class CensuraService {

    private final String PATH_GRAVACOES = "C:/radio/censura/"; // Altere para seu caminho

    @PostConstruct
    public void iniciarGravacao() {
        File diretorio = new File(PATH_GRAVACOES);
        if (!diretorio.exists()) diretorio.mkdirs();

        Thread threadGravacao = new Thread(() -> {
            try {
                ProcessBuilder pb = new ProcessBuilder(
                        "ffmpeg",
                        "-f", "dshow", "-i", "audio=NomeDaSuaPlaca", // No Linux use "-f", "alsa", "-i", "default"
                        "-acodec", "libmp3lame",
                        "-ab", "64k",
                        "-ac", "1",
                        "-f", "segment",
                        "-segment_time", "300",
                        "-strftime", "1",
                        "-segment_atclocktime", "1",
                        PATH_GRAVACOES + "%d%m%Y_%H%M%S.mp3"
                );

                pb.redirectErrorStream(true);
                Process processo = pb.start();
                System.out.println("Censura Iniciada!");

                // Mantém o processo vivo e monitora erros
                processo.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadGravacao.start();
    }
}