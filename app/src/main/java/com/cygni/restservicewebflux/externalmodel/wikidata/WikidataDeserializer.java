package com.cygni.restservicewebflux.externalmodel.wikidata;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WikidataDeserializer extends JsonDeserializer<WikidataDto> {
  private static final Logger log = LoggerFactory.getLogger(WikidataDeserializer.class);

  @Override
  public WikidataDto deserialize(
      JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    log.debug("Deserializing wikidataResponse");
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    return new WikidataDto(node.findPath("enwiki").path("title").asText());
  }
}
