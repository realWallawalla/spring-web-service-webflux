package com.cygni.restservicewebflux.externalmodel.wikidata;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class EntitiesDto {
  private final Map<String, WikiDataDto> wikiDataMap = new HashMap<>();

  public EntitiesDto() {
  }

  @JsonAnySetter
  public void add(String key, WikiDataDto value) {
    wikiDataMap.put("bandId", value);
  }

  @JsonAnyGetter
  public Map<String, WikiDataDto> getWikiDataMap() {
    return wikiDataMap;
  }
}
