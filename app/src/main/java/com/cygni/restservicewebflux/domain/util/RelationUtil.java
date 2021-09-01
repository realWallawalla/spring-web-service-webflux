package com.cygni.restservicewebflux.domain.util;

import com.cygni.restservicewebflux.externalmodel.musicbrainz.MusicBrainzResponseDto;
import com.cygni.restservicewebflux.externalmodel.musicbrainz.RelationDto;
import com.cygni.restservicewebflux.externalmodel.musicbrainz.UrlDto;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

public class RelationUtil {
  public static Optional<String> lookForTitle(List<RelationDto> relations, WikiType wikiType) {
    return relations.stream()
        .filter(relationDto -> wikiType.getWikiSite().equals(relationDto.type()))
        .map(RelationDto::url)
        .map(UrlDto::resource)
        .findFirst();
  }

  public static Optional<String> extractTitle(String resource) {
    return Optional.of(StringUtils.substringAfterLast(resource, "/"));
  }
}
