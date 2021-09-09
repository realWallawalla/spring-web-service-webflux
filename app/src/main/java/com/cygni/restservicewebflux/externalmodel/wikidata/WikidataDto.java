package com.cygni.restservicewebflux.externalmodel.wikidata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = WikidataDeserializer.class)
public record WikidataDto(@JsonProperty("title") String title) {

}
