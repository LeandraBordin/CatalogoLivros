package com.leandra.LivroService.business;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.Duration;
import java.util.Optional;

/**
 * Busca a URL da capa de um livro no Open Library (gratuito, sem chave de API).
 * Não baixa nem guarda a imagem: só confirma que ela existe e devolve a URL.
 */
@Service
public class CapaService {

    private static final String BASE = "https://covers.openlibrary.org/b/isbn/";

    private final RestClient client;

    public CapaService() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(3));
        factory.setReadTimeout(Duration.ofSeconds(3));
        this.client = RestClient.builder().requestFactory(factory).build();
    }


    public Optional<String> buscarCapa(String isbn) {
        String limpo = normalizar(isbn);
        if (limpo.isEmpty()) {
            return Optional.empty();
        }

        String url = BASE + limpo + "-L.jpg";

        try {

            client.get()
                    .uri(url + "?default=false")
                    .retrieve()
                    .toBodilessEntity();
            return Optional.of(url);
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }

    private String normalizar(String isbn) {
        return isbn == null ? "" : isbn.replaceAll("[^0-9Xx]", "");
    }
}
