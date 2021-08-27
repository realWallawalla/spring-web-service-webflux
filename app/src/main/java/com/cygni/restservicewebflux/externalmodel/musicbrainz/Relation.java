package com.cygni.restservicewebflux.externalmodel.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Relation {

  @JsonProperty("type")
  private String type;
  @JsonProperty("url")
  private Url url;
  @JsonProperty("title")
  private String title;

  public Relation() {
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Url getUrl() {
    return url;
  }

  public void setUrl(Url url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "RelationDto{" +
        "type='" + type + '\'' +
        ", urlDto=" + url +
        ", title='" + title + '\'' +
        '}';
  }
}
