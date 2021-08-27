package com.cygni.restservicewebflux.domain.service;

import com.cygni.mashup._1_0.MashUp;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MashUpService {

    public Mono<MashUp> createMashupMessage(String mbId) {
        return Mono.just(new MashUp());
    }
}
