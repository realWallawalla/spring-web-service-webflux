package com.cygni.restservicewebflux.domain.util;

public enum WikiType {
    WIKIDATA("wikidata"),
    WIKIPEDIA("wikipedia");

    private final String wikiSite;

    WikiType(String wikiSite) {
        this.wikiSite = wikiSite;
    }

    public String getWikiSite() {
        return wikiSite;
    }
}
