package com.cygni.restservicewebflux.externalmodel.coverartarchive;

import com.cygni.restservicewebflux.externalmodel.wikidata.WikidataDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoverArtArchiveDeserializer extends JsonDeserializer<CoverArtArchiveDto> {
  private static final Logger log = LoggerFactory.getLogger(WikidataDeserializer.class);

  @Override
  public CoverArtArchiveDto deserialize(
      JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    log.debug("Deserializing coverArtArchiveResponse");
    JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
    return new CoverArtArchiveDto(jsonNode.findPath("image").asText());
  }
}
