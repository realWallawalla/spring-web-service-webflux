package com.cygni.restservicewebflux.externalmodel.wikidata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EnWikiDto(@JsonProperty("title") String title,
                        @JsonProperty("site") String site) {}
