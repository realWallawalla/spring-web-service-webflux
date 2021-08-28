package com.cygni.restservicewebflux.externalmodel.coverartarchive;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImageDto(@JsonProperty("image") String image) {
}
