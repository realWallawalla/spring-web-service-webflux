package com.cygni.restservicewebflux.rest;

import static v1.RestPaths.GET_MASH_UP;

import com.cygni.mashup._1_0.MashUp;
import com.cygni.restservicewebflux.domain.converter.output.MashUpOutputConverter;
import com.cygni.restservicewebflux.domain.service.MashUpService;
import com.cygni.restservicewebflux.domain.service.ThrottlingService;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import v1.MessageTypes;

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

  @GetMapping(value = GET_MASH_UP, produces = MessageTypes.MASH_UP_1_JSON)
  public Mono<ResponseEntity<MashUp>> getMashUp(@PathVariable(value = "mbid") final String mbId) {
    log.info("fetching mashup");
    return isMbIdValid(mbId)
        ? mashUpService
            .createMashupMessage(mbId)
            .map(mashUpOutputConverter::convert)
            .map(mashUp -> ResponseEntity.ok().body(mashUp))
            .onErrorReturn(ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build())
        : Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MashUp()));
  }

  private boolean isMbIdValid(String mbId) {
    String[] strings = mbId.split("-");
    return mbId.length() == 36
        && strings.length == 5
        && strings[0].length() == 8
        && strings[1].length() == 4
        && strings[2].length() == 4
        && strings[3].length() == 4
        && strings[4].length() == 12
        && Arrays.stream(strings).allMatch(s -> s.matches("-?[0-9a-fA-F]+"));
  }
}
