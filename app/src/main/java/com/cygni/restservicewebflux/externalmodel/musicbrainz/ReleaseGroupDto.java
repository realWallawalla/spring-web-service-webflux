package com.cygni.restservicewebflux.externalmodel.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReleaseGroupDto(@JsonProperty("id") String id, @JsonProperty("title") String title) {
}
