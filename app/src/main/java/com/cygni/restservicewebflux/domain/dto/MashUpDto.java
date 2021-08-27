package com.cygni.restservicewebflux.domain.dto;

import com.cygni.mashup._1_0.Album;

import java.util.List;

public record MashUpDto(String mbId, String description, List<Album> albums) {}
