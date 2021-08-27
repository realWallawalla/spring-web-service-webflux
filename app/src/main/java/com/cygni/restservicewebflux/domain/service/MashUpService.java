package com.cygni.restservicewebflux.domain.service;

import com.cygni.restservicewebflux.domain.client.MusicBrainzClient;
import com.cygni.restservicewebflux.domain.dto.MashUpDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class MashUpService {
  private final MusicBrainzClient musicBrainzClient;

  public MashUpService(MusicBrainzClient musicBrainzClient) {
    this.musicBrainzClient = musicBrainzClient;
  }

  public Mono<MashUpDto> createMashupMessage(String mbId) {
    return musicBrainzClient
        .sendRequest(mbId)
        .map(
            musicBrainzResponse ->
                new MashUpDto(mbId, "musicBrainzResponse.getRelations()", new ArrayList<>()));
  }
}
