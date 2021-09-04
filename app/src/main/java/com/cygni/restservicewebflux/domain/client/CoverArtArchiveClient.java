package com.cygni.restservicewebflux.domain.client;

import com.cygni.restservicewebflux.externalmodel.coverartarchive.CoverArtArchiveResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CoverArtArchiveClient implements Client<CoverArtArchiveResponseDto, String>{
    private static final Logger log = LoggerFactory.getLogger(WikidataClient.class);
    public static final String BASE_URL = "https://coverartarchive.org";
    private final WebClient webClient;

    public CoverArtArchiveClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    @Override
    public Mono<CoverArtArchiveResponseDto> sendRequest(String albumId) {
        log.info("fetching from coverArtArchive");
        return webClient
            .get()
            .uri("/release-group/{mbid}",
                albumId)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(CoverArtArchiveResponseDto.class);
    }
}
