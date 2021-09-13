package com.cygni.restservicewebflux.domain.client;

import com.cygni.restservicewebflux.domain.exception.ExternalApiException;
import com.cygni.restservicewebflux.domain.util.ExternalApiType;
import com.cygni.restservicewebflux.domain.util.ExternalApiType.CacheNames;
import com.cygni.restservicewebflux.externalmodel.wikipedia.WikipediaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.yaml.snakeyaml.util.UriEncoder;
import reactor.core.publisher.Mono;

@Component
public class WikipediaClient implements Client<WikipediaDto, String> {
  private static final Logger log = LoggerFactory.getLogger(WikidataClient.class);
  public static final String BASE_URL = "https://en.wikipedia.org/w/api.php";
  private final WebClient webClient;

  public WikipediaClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
  }

  @Override
  @Cacheable(value = CacheNames.WIKIPEDIA_CACHE, key = "#title")
  public Mono<WikipediaDto> sendRequest(String title) {
    log.info("fetching from wikiPedia");
    return webClient
        .get()
        .uri(
            "?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles={title}",
            UriEncoder.encode(title.replace(" ", "_")))
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(
            HttpStatus::isError,
            clientResponse ->
                Mono.error(
                    new ExternalApiException(
                        clientResponse.statusCode(), ExternalApiType.WIKIPEDIA)))
        .bodyToMono(WikipediaDto.class)
        .doOnError(
            throwable ->
                log.error(
                    "Could not fetch from {}, message: {}",
                    ExternalApiType.WIKIPEDIA,
                    throwable.getMessage()));
  }
}
