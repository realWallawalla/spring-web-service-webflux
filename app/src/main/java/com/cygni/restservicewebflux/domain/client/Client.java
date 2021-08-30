package com.cygni.restservicewebflux.domain.client;

import reactor.core.publisher.Mono;

public interface Client<R, I> {
    Mono<R> sendRequest(I input);
}
