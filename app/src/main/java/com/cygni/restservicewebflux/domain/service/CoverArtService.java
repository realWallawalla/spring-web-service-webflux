package com.cygni.restservicewebflux.domain.service;

import com.cygni.restservicewebflux.domain.client.CoverArtArchiveClient;
import org.springframework.stereotype.Component;

@Component
public class CoverArtService {
    private final CoverArtArchiveClient coverArtArchiveClient;

    public CoverArtService(CoverArtArchiveClient coverArtArchiveClient) {
        this.coverArtArchiveClient = coverArtArchiveClient;
    }
}
