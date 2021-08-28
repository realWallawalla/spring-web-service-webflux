package com.cygni.restservicewebflux.externalmodel.wikidata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SiteLinksDto(@JsonProperty("enwiki") EnWikiDto enWikiDto) {
}
