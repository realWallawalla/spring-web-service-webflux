package com.cygni.restservicewebflux.domain.dto;

import com.cygni.mashup._1_0.Album;

import java.util.List;

public class MashUpDto {
    private String mbId;
    private String description;
    private List<Album> albums;

    public String getMbId() {
        return mbId;
    }

    public void setMbId(String mbId) {
        this.mbId = mbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
