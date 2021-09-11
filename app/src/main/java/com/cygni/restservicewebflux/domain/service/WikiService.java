package com.cygni.restservicewebflux.domain.service;

import com.cygni.restservicewebflux.domain.client.WikidataClient;
import com.cygni.restservicewebflux.domain.client.WikipediaClient;
import com.cygni.restservicewebflux.externalmodel.wikidata.WikidataDto;
import com.cygni.restservicewebflux.externalmodel.wikipedia.WikipediaDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class WikiService {
  private final WikipediaClient wikipediaClient;
  private final WikidataClient wikidataClient;

  public WikiService(WikipediaClient wikipediaClient, WikidataClient wikidataClient) {
    this.wikipediaClient = wikipediaClient;
    this.wikidataClient = wikidataClient;
  }

  public Mono<String> fetchArtistDescription(String title) {
    return wikipediaClient.sendRequest(title).map(WikipediaDto::extract);
  }

  public Mono<String> fetchWikipediaId(String wikiDataPageId) {
    return wikidataClient.sendRequest(wikiDataPageId).map(WikidataDto::title);
  }
}
