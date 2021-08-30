package com.cygni.restservicewebflux.domain.client;

import com.cygni.restservicewebflux.externalmodel.wikipedia.WikipediaResponseDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class WikipediaClient implements Client<WikipediaResponseDto, String> {

    @Override
    public Mono<WikipediaResponseDto> sendRequest(String title) {
        return Mono.empty();
    }
}
