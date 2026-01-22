package br.com.drs.radio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Expondo a pasta física na URL /arquivos-audio/**
        registry.addResourceHandler("/arquivos-audio/**")
                .addResourceLocations("file:C:/radio/censura/");
    }
}