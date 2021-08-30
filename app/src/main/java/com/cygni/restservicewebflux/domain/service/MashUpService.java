package com.cygni.restservicewebflux.domain.service;

import com.cygni.restservicewebflux.domain.client.MusicBrainzClient;
import com.cygni.restservicewebflux.domain.dto.MashUpDto;
import com.cygni.restservicewebflux.domain.util.RelationUtil;
import com.cygni.restservicewebflux.domain.util.WikiType;
import com.cygni.restservicewebflux.externalmodel.wikidata.WikiDataResponseDto;
import com.cygni.restservicewebflux.externalmodel.wikipedia.WikipediaResponseDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class MashUpService {
    private final MusicBrainzClient musicBrainzClient;
  private final WikiService wikiService;
  private final CoverArtService coverArtService;

  public MashUpService(MusicBrainzClient musicBrainzClient, WikiService wikiService, CoverArtService coverArtService) {
    this.musicBrainzClient = musicBrainzClient;
      this.wikiService = wikiService;
      this.coverArtService = coverArtService;
  }

  public Mono<MashUpDto> createMashupMessage(String mbId) {
    MashUpDto mashUpDto = new MashUpDto();
    musicBrainzClient
        .sendRequest(mbId)
            .doOnNext(musicBrainzResponse -> {
                Optional<String> title = RelationUtil.lookForTitle(musicBrainzResponse, WikiType.WIKIPEDIA)
                        .flatMap(RelationUtil::extractTitle);
                if (title.isPresent()) {
                    wikiService.getArtistDescription(title.get())
                            .subscribe(mashUpDto::setDescription);
                } else {
                    wikiService.getTitle(RelationUtil.lookForTitle(musicBrainzResponse, WikiType.WIKIDATA)
                                    .flatMap(RelationUtil::extractTitle).orElse(""))
                            .flatMap(wikiService::getArtistDescription)
                            .subscribe(mashUpDto::setDescription);
                }
            });
    return Mono.empty();
  }
}
