package com.cygni.restservicewebflux.domain.client;

import com.cygni.restservicewebflux.domain.exception.ExternalApiException;
import com.cygni.restservicewebflux.domain.util.ExternalApiType;
import com.cygni.restservicewebflux.externalmodel.wikidata.WikiDataResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WikidataClient implements Client<WikiDataResponseDto, String> {
  private static final Logger log = LoggerFactory.getLogger(WikidataClient.class);
  public static final String BASE_URL = "https://www.wikidata.org/w/api.php";
  private final WebClient webClient;

  public WikidataClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
  }

  @Override
  public Mono<WikiDataResponseDto> sendRequest(String wikiDataPageId) {
    log.info("fetching from wikiData");
    return webClient
        .get()
        .uri(
            "?action=wbgetentities&ids={wikiDataId}&format=json&props=sitelinks",
            wikiDataPageId)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(HttpStatus::isError, clientResponse ->
            Mono.error(new ExternalApiException(clientResponse.statusCode(), ExternalApiType.WIKIDATA)))
        .bodyToMono(WikiDataResponseDto.class)
        .doOnError(throwable -> log.error("Could not fetch from {}, message: {}", ExternalApiType.WIKIDATA, throwable.getMessage()));
  }
}
