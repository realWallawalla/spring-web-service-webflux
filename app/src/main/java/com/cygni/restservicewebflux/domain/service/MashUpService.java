package com.cygni.restservicewebflux.domain.service;

import com.cygni.restservicewebflux.domain.dto.AlbumDto;
import com.cygni.restservicewebflux.domain.dto.MashUpDto;
import com.cygni.restservicewebflux.externalmodel.musicbrainz.MusicBrainzDto;
import com.cygni.restservicewebflux.externalmodel.musicbrainz.ReleaseGroupDto;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MashUpService {
  private static final Logger log = LoggerFactory.getLogger(MashUpService.class);

  private final MusicBrainzService musicBrainzService;
  private final WikiService wikiService;
  private final CoverArtService coverArtService;

  public MashUpService(
      MusicBrainzService musicBrainzService,
      WikiService wikiService,
      CoverArtService coverArtService) {
    this.musicBrainzService = musicBrainzService;
    this.wikiService = wikiService;
    this.coverArtService = coverArtService;
  }

  public Mono<MashUpDto> createMashupMessage(String mbId) {
    return musicBrainzService
        .retrieveArtistInfo(mbId)
        .flatMap(
            musicBrainzDto -> {
              return retrieveArtistDescription(musicBrainzDto)
                  .zipWith(
                      retrieveAlbums(musicBrainzDto.releaseGroupDtos()),
                      (description, albums) -> {
                        return new MashUpDto(musicBrainzDto.artistMbId(), description, albums);
                      });
            })
        .doOnError(
            throwable -> {
              log.error("Could not reach underlying apis: {}", throwable.getMessage());
              throw new RestClientException(throwable.getMessage(), throwable);
            });
  }

  private Mono<String> retrieveArtistDescription(MusicBrainzDto musicBrainzDto) {
    return musicBrainzDto.wikipediaId().isBlank()
        ? wikiService
            .getTitle(musicBrainzDto.wikidataId())
            .flatMap(wikiService::getArtistDescription)
        : wikiService.getArtistDescription(musicBrainzDto.wikipediaId());
  }

  private Mono<List<AlbumDto>> retrieveAlbums(List<ReleaseGroupDto> releaseGroups) {
    return Flux.fromStream(releaseGroups.stream())
        .filter(Objects::nonNull)
        .flatMap(this::createAlbum)
        .collectList();
  }

  private Mono<AlbumDto> createAlbum(ReleaseGroupDto releaseGroupDto) {
    return coverArtService
        .getCoverPhotoUrl(releaseGroupDto.artistId())
        .map(
            imageUrl ->
                new AlbumDto(releaseGroupDto.artistId(), releaseGroupDto.albumTitle(), imageUrl));
  }
}
