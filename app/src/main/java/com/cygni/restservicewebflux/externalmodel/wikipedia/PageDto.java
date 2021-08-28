package com.cygni.restservicewebflux.externalmodel.wikipedia;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PageDto(@JsonProperty("pageId") String pageId, @JsonProperty("title") String title, @JsonProperty("extract") String extract) {}
