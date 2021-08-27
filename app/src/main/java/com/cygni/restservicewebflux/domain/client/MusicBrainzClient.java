package com.cygni.restservicewebflux.domain.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MusicBrainzClient {
    public static final String BASE_URL = "http://musicbrainz.org/ws/2/artist";
    private final WebClient webClient;

    public MusicBrainzClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }
}
