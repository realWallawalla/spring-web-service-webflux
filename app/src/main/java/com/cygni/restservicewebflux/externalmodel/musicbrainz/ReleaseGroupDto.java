package com.cygni.restservicewebflux.externalmodel.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReleaseGroupDto(@JsonProperty("id") String artistId, @JsonProperty("title") String albumTitle) {
}
