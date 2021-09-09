package com.cygni.restservicewebflux.externalmodel.musicbrainz;

import static com.cygni.restservicewebflux.externalmodel.WikiType.WIKIDATA;
import static com.cygni.restservicewebflux.externalmodel.WikiType.WIKIPEDIA;

import com.cygni.restservicewebflux.externalmodel.WikiType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MusicBrainzDeserializer extends JsonDeserializer<MusicBrainzDto> {

  @Override
  public MusicBrainzDto deserialize(
      JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
    JsonNode relations = rootNode.findPath("relations");
    Optional<String> wikidataId = Optional.empty();

    JsonNode releaseGroups = rootNode.findPath("release-groups");

    Optional<String> wikipediaId = extractWikiId(relations, WIKIPEDIA);
    if (wikipediaId.isEmpty()) {
      wikidataId = extractWikiId(relations, WIKIDATA);
    }
    return new MusicBrainzDto(
        rootNode.path("id").asText(),
        wikipediaId.orElseGet(() -> ""),
        wikidataId.orElseGet(() -> ""),
        convertToReleaseGroupDtos(releaseGroups));
  }

  private List<ReleaseGroupDto> convertToReleaseGroupDtos(JsonNode releaseGroups) {
    return StreamSupport.stream(releaseGroups.spliterator(), false)
        .map(
            jsonNode -> {
              return new ReleaseGroupDto(
                  jsonNode.path("id").asText(), jsonNode.path("title").asText());
            })
        .collect(Collectors.toList());
  }

  private Optional<String> extractWikiId(JsonNode relations, WikiType wikiType) {
    return StreamSupport.stream(relations.spliterator(), false)
        .filter(jsonNode -> wikiType.getWikiSite().equals(jsonNode.path("type").asText()))
        .findFirst()
        .map(jsonNode -> jsonNode.path("url").path("resource").asText())
        .map(resource -> StringUtils.substringAfterLast(resource, "/"));
  }
}