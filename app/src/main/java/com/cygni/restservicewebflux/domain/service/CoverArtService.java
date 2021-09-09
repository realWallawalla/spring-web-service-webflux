package com.cygni.restservicewebflux.domain.service;

import com.cygni.restservicewebflux.domain.client.CoverArtArchiveClient;
import com.cygni.restservicewebflux.externalmodel.coverartarchive.CoverArtArchiveDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CoverArtService {
  private final CoverArtArchiveClient coverArtArchiveClient;

  public CoverArtService(CoverArtArchiveClient coverArtArchiveClient) {
    this.coverArtArchiveClient = coverArtArchiveClient;
  }

  public Mono<String> getCoverPhotoUrl(String albumId) {
    return coverArtArchiveClient.sendRequest(albumId).map(CoverArtArchiveDto::imageUrl);
  }
}
