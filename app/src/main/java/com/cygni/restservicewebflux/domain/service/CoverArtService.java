package com.cygni.restservicewebflux.domain.service;

import com.cygni.restservicewebflux.domain.client.CoverArtArchiveClient;
import com.cygni.restservicewebflux.externalmodel.coverartarchive.CoverArtArchiveResponseDto;
import com.cygni.restservicewebflux.externalmodel.coverartarchive.ImageDto;
import java.util.Objects;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CoverArtService {
    private final CoverArtArchiveClient coverArtArchiveClient;

    public CoverArtService(CoverArtArchiveClient coverArtArchiveClient) {
        this.coverArtArchiveClient = coverArtArchiveClient;
    }

    public Mono<String> getCoverPhotoUrl(String albumId) {
        return coverArtArchiveClient
            .sendRequest(albumId)
            .map(this::extractCoverArtUrl);
    }

    private String extractCoverArtUrl(CoverArtArchiveResponseDto coverArtArchiveResponseDto) {
        //its a list of images but take first
        return coverArtArchiveResponseDto
            .imageDtos()
            .stream()
            .filter(Objects::nonNull)
            .map(ImageDto::imageUrl)
            .findFirst()
            .orElseGet(() -> "");
    }
}
