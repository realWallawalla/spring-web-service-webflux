package com.cygni.restservicewebflux.externalmodel.wikipedia;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class WikipediaDeserializer extends JsonDeserializer<WikipediaDto> {

  @Override
  public WikipediaDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
    return new WikipediaDto(jsonNode.findPath("extract").asText());
  }
}
