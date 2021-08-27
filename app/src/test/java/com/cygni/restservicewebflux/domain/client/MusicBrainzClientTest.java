package com.cygni.restservicewebflux.domain.client;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(MockitoExtension.class)
class MusicBrainzClientTest {

    @Mock
    private WebClient webClient;
    @InjectMocks
    private MusicBrainzClient musicBrainzClient;
}