package com.cygni.restservicewebflux.domain.client;

import com.cygni.restservicewebflux.externalmodel.musicbrainz.MusicBrainzResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MusicBrainzClient implements Client<MusicBrainzResponseDto, String> {
  public static final String BASE_URL = "http://musicbrainz.org/ws/2/artist";
  private final WebClient webClient;

  public MusicBrainzClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
  }

  public Mono<MusicBrainzResponseDto> sendRequest(String mbId) {
    return webClient
        .get()
        .uri("/{mbid}?inc=url-rels+release-groups&fmt=json", mbId)
        .retrieve()
        .bodyToMono(MusicBrainzResponseDto.class);
  }
}
