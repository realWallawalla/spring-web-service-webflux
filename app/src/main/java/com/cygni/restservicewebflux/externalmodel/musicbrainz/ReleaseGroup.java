package com.cygni.restservicewebflux.externalmodel.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReleaseGroup {
  @JsonProperty("id")
  private String id;
  @JsonProperty("title")
  private String title;

  public ReleaseGroup() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "ReleaseGroupDto{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        '}';
  }
}
