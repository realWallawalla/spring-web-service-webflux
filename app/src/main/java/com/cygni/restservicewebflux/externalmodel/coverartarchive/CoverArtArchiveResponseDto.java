package com.cygni.restservicewebflux.externalmodel.coverartarchive;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CoverArtArchiveResponseDto(@JsonProperty("images") List<ImageDto> imageDtos) {
}
