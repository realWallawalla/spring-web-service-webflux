package com.cygni.restservicewebflux.domain.client;

import com.cygni.restservicewebflux.externalmodel.wikidata.WikiDataResponseDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class WikidataClient implements Client<WikiDataResponseDto, String>{
    @Override
    public Mono<WikiDataResponseDto> sendRequest(String input) {
        return null;
    }
}
