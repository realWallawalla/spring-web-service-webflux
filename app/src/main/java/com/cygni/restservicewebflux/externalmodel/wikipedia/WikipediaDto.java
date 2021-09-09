package com.cygni.restservicewebflux.externalmodel.wikipedia;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = WikipediaDeserializer.class)
public record WikipediaDto(@JsonProperty("extract") String extract) {}
