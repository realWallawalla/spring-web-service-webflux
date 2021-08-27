package com.cygni.restservicewebflux.domain.converter;

import reactor.core.publisher.Mono;

public interface Converter<R, T> {
    public R convert(T t);
}
