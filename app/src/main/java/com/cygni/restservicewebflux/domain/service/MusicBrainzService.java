package com.cygni.restservicewebflux.domain.service;

import com.cygni.restservicewebflux.domain.client.MusicBrainzClient;
import com.cygni.restservicewebflux.externalmodel.musicbrainz.MusicBrainzResponseDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MusicBrainzService {
  private final MusicBrainzClient musicBrainzClient;

  public MusicBrainzService(MusicBrainzClient musicBrainzClient) {
    this.musicBrainzClient = musicBrainzClient;
  }

  public Mono<MusicBrainzResponseDto> retrieveArtistInfo(String mbId) {
    return musicBrainzClient.sendRequest(mbId);
  }
}
