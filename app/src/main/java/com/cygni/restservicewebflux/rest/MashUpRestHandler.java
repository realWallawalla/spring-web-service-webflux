package com.cygni.restservicewebflux.rest;

import com.cygni.mashup._1_0.MashUp;
import com.cygni.restservicewebflux.domain.converter.output.MashUpOutputConverter;
import com.cygni.restservicewebflux.domain.service.MashUpService;
import com.cygni.restservicewebflux.domain.service.ThrottlingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import v1.MessageTypes;
import v1.RestPaths;

@RestController
public class MashUpRestHandler {
  private final MashUpService mashUpService;
  private final ThrottlingService throttlingService;
  private final MashUpOutputConverter mashUpOutputConverter;

  public MashUpRestHandler(
      MashUpService mashUpService,
      ThrottlingService throttlingService,
      MashUpOutputConverter mashUpOutputConverter) {
    this.mashUpService = mashUpService;
    this.throttlingService = throttlingService;
    this.mashUpOutputConverter = mashUpOutputConverter;
  }

  @GetMapping(value = RestPaths.GET_MASH_UP, produces = MessageTypes.MASH_UP_1_JSON)
  public Mono<MashUp> getMashUp(@PathVariable(value = "mbid") final String mbId) {
    return mashUpService.createMashupMessage(mbId).map(mashUpOutputConverter::convert);
  }
}
