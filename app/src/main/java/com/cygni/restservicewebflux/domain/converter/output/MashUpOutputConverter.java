package com.cygni.restservicewebflux.domain.converter.output;

import com.cygni.mashup._1_0.Album;
import com.cygni.mashup._1_0.MashUp;
import com.cygni.restservicewebflux.domain.converter.Converter;
import com.cygni.restservicewebflux.domain.dto.AlbumDto;
import com.cygni.restservicewebflux.domain.dto.MashUpDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MashUpOutputConverter implements Converter<MashUp, MashUpDto> {
  @Override
  public MashUp convert(MashUpDto mashUpDto) {
    MashUp mashUp = new MashUp();
    mashUp.setMbId(mashUpDto.mbId());
    mashUp.setDescription(mashUpDto.description());
    mashUp.getAlbums().addAll(convertToAlbums(mashUpDto.albums()));
    return mashUp;
  }

  private List<Album> convertToAlbums(List<AlbumDto> mashUpDto) {
    return mashUpDto.stream().map(this::convertAlbum).collect(Collectors.toList());
  }

  private Album convertAlbum(AlbumDto albumDto) {
    Album album = new Album();
    album.setTitle(albumDto.title());
    album.setImageUrl(albumDto.imageUrl());
    album.setId(albumDto.id());
    return album;
  }
}
