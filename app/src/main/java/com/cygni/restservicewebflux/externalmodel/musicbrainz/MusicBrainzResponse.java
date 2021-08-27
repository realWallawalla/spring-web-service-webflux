package com.cygni.restservicewebflux.externalmodel.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class MusicBrainzResponse {
  @JsonProperty("id")
  private String mbid;
  @JsonProperty("relations")
  private List<Relation> relations;
  @JsonProperty("release-groups")
  private List<ReleaseGroup> releaseGroups;

  public MusicBrainzResponse() {
  }

  public String getMbid() {
    return mbid;
  }

  public List<Relation> getRelations() {
    return relations;
  }

  public void setMbid(String mbid) {
    this.mbid = mbid;
  }

  public void setRelations(
      List<Relation> relations) {
    this.relations = relations;
  }

  public void setReleaseGroups(
      List<ReleaseGroup> releaseGroups) {
    this.releaseGroups = releaseGroups;
  }

  public List<ReleaseGroup> getReleaseGroups() {
    return releaseGroups;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MusicBrainzResponse that = (MusicBrainzResponse) o;
    return mbid.equals(that.mbid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mbid);
  }

  @Override
  public String toString() {
    return "MusicBrainzDto{" +
        "mbId='" + mbid + '\'' +
        ", relations=" + relations +
        ", releaseGroups=" + releaseGroups +
        '}';
  }
}
