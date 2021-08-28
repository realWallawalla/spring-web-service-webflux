package com.cygni.restservicewebflux.externalmodel.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RelationDto(@JsonProperty("type") String type,
                          @JsonProperty("url") UrlDto url,
                          @JsonProperty("title") String title) {
}
