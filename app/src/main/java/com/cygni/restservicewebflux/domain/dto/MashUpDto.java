package com.cygni.restservicewebflux.domain.dto;

import java.util.List;

public record MashUpDto(String mbId, String description, List<AlbumDto> albums) {}
