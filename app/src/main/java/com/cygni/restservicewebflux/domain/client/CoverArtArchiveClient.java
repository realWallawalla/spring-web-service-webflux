package com.cygni.restservicewebflux.domain.client;

import com.cygni.restservicewebflux.externalmodel.coverartarchive.CoverArtArchiveResponseDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CoverArtArchiveClient implements Client<CoverArtArchiveResponseDto, String>{
    @Override
    public Mono<CoverArtArchiveResponseDto> sendRequest(String albumId) {
        return null;
    }
}
