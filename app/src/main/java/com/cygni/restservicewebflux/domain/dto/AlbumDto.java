package com.cygni.restservicewebflux.domain.dto;

import javax.xml.bind.annotation.XmlElement;

public record AlbumDto(String id, String title, String imageUrl) { }
