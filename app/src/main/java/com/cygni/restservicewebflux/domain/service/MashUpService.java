package com.cygni.restservicewebflux.domain.service;

import com.cygni.mashup._1_0.Album;
import com.cygni.restservicewebflux.domain.client.MusicBrainzClient;
import com.cygni.restservicewebflux.domain.dto.AlbumDto;
import com.cygni.restservicewebflux.domain.dto.MashUpDto;
import com.cygni.restservicewebflux.domain.util.RelationUtil;
import com.cygni.restservicewebflux.domain.util.WikiType;
import com.cygni.restservicewebflux.externalmodel.musicbrainz.MusicBrainzResponseDto;
import com.cygni.restservicewebflux.externalmodel.musicbrainz.ReleaseGroupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
public class MashUpService {
    private final MusicBrainzClient musicBrainzClient;
    private static final Logger log = LoggerFactory.getLogger(MashUpService.class);

    private final WikiService wikiService;
  private final CoverArtService coverArtService;

    public MashUpService(MusicBrainzClient musicBrainzClient, WikiService wikiService, CoverArtService coverArtService) {
    this.musicBrainzClient = musicBrainzClient;
      this.wikiService = wikiService;
      this.coverArtService = coverArtService;
  }

  public Mono<MashUpDto> createMashupMessage(String mbId) {
   return musicBrainzClient
        .sendRequest(mbId)
            .flatMap(musicBrainzResponse -> {
                Optional<String> title = RelationUtil.lookForTitle(musicBrainzResponse.relations(), WikiType.WIKIPEDIA)
                        .flatMap(RelationUtil::extractTitle);
                return getArtistDescription(musicBrainzResponse, title.orElse(""))
                        .zipWith(getAlbums(musicBrainzResponse.releaseGroupDtos()), (desc, albums) -> {
                            return new MashUpDto(musicBrainzResponse.mbId(), desc, albums);
                        });
            })
            .doOnError(throwable -> {
                log.error("could not reach underlying api: MusicBrainz:", throwable.getCause());
                throw new RestClientException(throwable.getMessage());
            });
  }

    private Mono<List<AlbumDto>> getAlbums(List<ReleaseGroupDto> releaseGroups) {
        return null;
    }

    private Mono<String> getArtistDescription(MusicBrainzResponseDto musicBrainzResponse, String title) {
        if (title.isBlank()) {
            return wikiService.getTitle(RelationUtil.lookForTitle(musicBrainzResponse.relations(), WikiType.WIKIDATA)
                    .flatMap(RelationUtil::extractTitle)
                    .orElseGet(() -> ""))
                    .flatMap(wikiService::getArtistDescription);
        } else {
            return wikiService.getArtistDescription(title);
        }
    }
}
