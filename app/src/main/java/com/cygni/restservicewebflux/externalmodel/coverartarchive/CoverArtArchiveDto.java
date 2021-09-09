package com.cygni.restservicewebflux.externalmodel.coverartarchive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = CoverArtArchiveDeserializer.class)
public record CoverArtArchiveDto(@JsonProperty String imageUrl) {}
