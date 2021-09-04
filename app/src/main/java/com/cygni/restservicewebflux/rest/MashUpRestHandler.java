package com.cygni.restservicewebflux.rest;

import com.cygni.mashup._1_0.MashUp;
import com.cygni.restservicewebflux.domain.converter.output.MashUpOutputConverter;
import com.cygni.restservicewebflux.domain.service.MashUpService;
import com.cygni.restservicewebflux.domain.service.ThrottlingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MashUpRestHandler {
  private static final Logger log = LoggerFactory.getLogger(MashUpRestHandler.class);
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

  @GetMapping(value = "/mashup/{mbid}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<MashUp>> getMashUp(@PathVariable(value = "mbid") final String mbId) {
    log.info("fetching mashup");
    return mashUpService
        .createMashupMessage(mbId)
        .map(mashUpOutputConverter::convert)
        .map(mashUp -> ResponseEntity.ok().body(mashUp))
        .onErrorReturn(ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build());
  }
}
