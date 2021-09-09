package com.cygni.restservicewebflux.externalmodel.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(using = MusicBrainzDeserializer.class)
public record MusicBrainzDto(@JsonProperty("id") String artistMbId,
                             @JsonProperty("title") String wikipediaId,
                             @JsonProperty("wikidataId") String wikidataId,
                             @JsonProperty("release-groups") List<ReleaseGroupDto> releaseGroupDtos) {}
