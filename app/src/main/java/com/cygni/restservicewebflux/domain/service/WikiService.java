package com.cygni.restservicewebflux.domain.service;

import com.cygni.restservicewebflux.domain.client.WikidataClient;
import com.cygni.restservicewebflux.domain.client.WikipediaClient;
import com.cygni.restservicewebflux.externalmodel.wikidata.WikiDataResponseDto;
import com.cygni.restservicewebflux.externalmodel.wikipedia.WikipediaResponseDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class WikiService {
  private static final String WIKI_DATA_MAP_KEY = "bandId";
  private static final String KEY_PAGE_MAP = "pageId";
  private final WikipediaClient wikipediaClient;
  private final WikidataClient wikidataClient;

  public WikiService(WikipediaClient wikipediaClient, WikidataClient wikidataClient) {
    this.wikipediaClient = wikipediaClient;
    this.wikidataClient = wikidataClient;
  }

  public Mono<String> getArtistDescription(String title) {
    return wikipediaClient.sendRequest(title).map(this::extractDescription);
  }

  public Mono<String> getTitle(String wikiDataPageId) {
    return wikidataClient.sendRequest(wikiDataPageId).map(this::getTitleFromWikiDataResp);
  }

  private String getTitleFromWikiDataResp(WikiDataResponseDto wikiDataResponseDto) {
    return wikiDataResponseDto
        .entities()
        .getWikiDataMap()
        .get(WIKI_DATA_MAP_KEY)
        .siteLinksDto()
        .enWikiDto()
        .title();
  }

  private String extractDescription(WikipediaResponseDto wikipediaResponse) {
    return wikipediaResponse.query().pages().getPageMap().get(KEY_PAGE_MAP).extract();
  }
}
