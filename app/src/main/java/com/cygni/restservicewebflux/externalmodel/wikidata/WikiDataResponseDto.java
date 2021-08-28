package com.cygni.restservicewebflux.externalmodel.wikidata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WikiDataResponseDto(@JsonProperty("entities") EntitiesDto entities) {}
