package br.com.drs.radio.service;

import br.com.drs.radio.model.ConfigRadio;
import br.com.drs.radio.repository.ConfigRadioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConfigRadioService {

    private final ConfigRadioRepository configRadioRepository;

    public ConfigRadio salvarConfiguracao(@RequestBody ConfigRadio configRadio) {
        // Verifica se os campos essenciais foram enviados
        if (configRadio.getIntervalosComercial() != null && configRadio.getDuracaoBreakComercial() != null) {

            int intervalo = configRadio.getIntervalosComercial();
            int duracao = configRadio.getDuracaoBreakComercial();

            // Evita divisão por zero se o intervalo for 0
            if (intervalo > 0) {
                int quatidadeBreaksHora = 60 / intervalo;
                int tempoBreakshora = quatidadeBreaksHora * duracao;
                int tempoDuracaoMusical = (60 - tempoBreakshora) / quatidadeBreaksHora;

                configRadio.setDuracaoMusical(tempoDuracaoMusical);
                configRadio.setIntervaloMusical(intervalo);
            }
        }
        return configRadioRepository.save(configRadio);
    }

    public ConfigRadio atualizarConfiguracao(ConfigRadio configRadio) {
        if (!configRadioRepository.existsById(configRadio.getId())) {
            throw new EntityNotFoundException("Configuração não encontrada com o ID: " + configRadio.getId());
        }
        return configRadioRepository.save(configRadio);
    }
}
