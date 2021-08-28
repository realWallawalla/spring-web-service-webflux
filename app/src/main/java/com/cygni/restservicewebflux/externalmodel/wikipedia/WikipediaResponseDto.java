package com.cygni.restservicewebflux.externalmodel.wikipedia;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WikipediaResponseDto(@JsonProperty("query") QueryDto query) {}
