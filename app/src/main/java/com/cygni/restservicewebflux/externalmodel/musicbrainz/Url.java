package com.cygni.restservicewebflux.externalmodel.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Url {
  @JsonProperty("resource")
  private String resource;
  @JsonProperty("id")
  private String id;

  public Url() {
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "UrlDto{" +
        "resource='" + resource + '\'' +
        ", id='" + id + '\'' +
        '}';
  }
}
