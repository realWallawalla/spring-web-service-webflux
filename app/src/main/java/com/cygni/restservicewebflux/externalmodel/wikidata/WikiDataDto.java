package com.cygni.restservicewebflux.externalmodel.wikidata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WikiDataDto(@JsonProperty("type") String type,
                          @JsonProperty("id") String id,
                          @JsonProperty("sitelinks") SiteLinksDto siteLinksDto
                          ) {
}
