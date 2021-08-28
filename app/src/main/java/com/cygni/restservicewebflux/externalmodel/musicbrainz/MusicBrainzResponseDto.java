package com.cygni.restservicewebflux.externalmodel.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MusicBrainzResponseDto(@JsonProperty("id") String mbId,
                                     @JsonProperty("relations") List<RelationDto> relations,
                                     @JsonProperty("release-groups") List<ReleaseGroupDto> releaseGroupDtos) {}
