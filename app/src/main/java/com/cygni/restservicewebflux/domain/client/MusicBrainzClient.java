package com.cygni.restservicewebflux.domain.client;

import com.cygni.restservicewebflux.domain.exception.ExternalApiException;
import com.cygni.restservicewebflux.domain.util.ExternalApiType;
import com.cygni.restservicewebflux.domain.util.ExternalApiType.CacheNames;
import com.cygni.restservicewebflux.externalmodel.musicbrainz.MusicBrainzDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MusicBrainzClient implements Client<MusicBrainzDto, String> {
  private static final Logger log = LoggerFactory.getLogger(MusicBrainzClient.class);
  public static final String BASE_URL = "http://musicbrainz.org/ws/2/artist";
  private final WebClient webClient;

  public MusicBrainzClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
  }

  @Override
  @Cacheable(value = CacheNames.MUSIC_BRAINZ_CACHE, key = "#mbId")
  public Mono<MusicBrainzDto> sendRequest(String mbId) {
    log.info("fetching from musicBrainApi");
    return webClient
        .get()
        .uri("/{mbid}?inc=url-rels+release-groups&fmt=json", mbId)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(HttpStatus::isError, clientResponse ->
            Mono.error(new ExternalApiException(clientResponse.statusCode(), ExternalApiType.MUSIC_BRAINZ)))
        .bodyToMono(MusicBrainzDto.class)
        .doOnError(throwable -> log.error("Could not fetch from {}, message: {}", ExternalApiType.MUSIC_BRAINZ, throwable.getMessage()));
  }
}
