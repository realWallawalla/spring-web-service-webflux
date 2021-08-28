package com.cygni.restservicewebflux.externalmodel.wikipedia;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class PagesDto {
  private final Map<String, PageDto> pageMap = new HashMap<>();

  public PagesDto() {
  }

  @JsonAnySetter
  public void add(String key, PageDto value) {
    pageMap.put("pageId", value);
  }

  @JsonAnyGetter
  public Map<String, PageDto> getPageMap() {
    return pageMap;
  }
}
