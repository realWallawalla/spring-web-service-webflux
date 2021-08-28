package com.cygni.restservicewebflux.externalmodel.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UrlDto(@JsonProperty("resource") String resource,
                     @JsonProperty("id") String id) {
}
