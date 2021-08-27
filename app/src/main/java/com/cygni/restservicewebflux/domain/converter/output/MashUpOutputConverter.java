package com.cygni.restservicewebflux.domain.converter.output;

import com.cygni.mashup._1_0.MashUp;
import com.cygni.restservicewebflux.domain.converter.Converter;
import com.cygni.restservicewebflux.domain.dto.MashUpDto;
import org.springframework.stereotype.Component;

@Component
public class MashUpOutputConverter implements Converter<MashUp, MashUpDto> {
  @Override
  public MashUp convert(MashUpDto mashUpDto) {
    MashUp mashUp = new MashUp();
    mashUp.setMbId(mashUpDto.mbId());
    mashUp.setDescription(mashUpDto.description());
    mashUp.getAlbums().addAll(mashUpDto.albums());
    return mashUp;
  }
}
