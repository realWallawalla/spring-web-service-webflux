package com.cygni.restservicewebflux.domain.client;

import com.cygni.restservicewebflux.domain.util.ExternalApiType.CacheNames;
import com.cygni.restservicewebflux.externalmodel.coverartarchive.CoverArtArchiveDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
public class CoverArtArchiveClient implements Client<CoverArtArchiveDto, String> {
  private static final Logger log = LoggerFactory.getLogger(WikidataClient.class);
  public static final String BASE_URL = "https://coverartarchive.org";
  private final WebClient webClient;

  public CoverArtArchiveClient(WebClient.Builder webClientBuilder) {
    this.webClient =
        webClientBuilder
            .clientConnector(
                new ReactorClientHttpConnector(HttpClient.create().followRedirect(true)))
            .baseUrl(BASE_URL)
            .build();
  }

  @Override
  @Cacheable(value = CacheNames.MUSIC_BRAINZ_CACHE, key = "#albumId")
  public Mono<CoverArtArchiveDto> sendRequest(String albumId) {
    log.info("fetching from coverArtArchive");
    return webClient
        .get()
        .uri("/release-group/{mbid}", albumId)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(CoverArtArchiveDto.class)
        .doOnError(
            throwable ->
                log.info(
                    "Continuing and dropping erroneous elements. Message: {}",
                    throwable.getMessage()))
        .onErrorResume(throwable -> Mono.just(new CoverArtArchiveDto("N/A")));
  }
}
