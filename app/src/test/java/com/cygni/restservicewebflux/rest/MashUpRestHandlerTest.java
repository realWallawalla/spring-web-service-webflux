package com.cygni.restservicewebflux.rest;

import com.cygni.mashup._1_0.MashUp;
import com.cygni.restservicewebflux.domain.converter.output.MashUpOutputConverter;
import com.cygni.restservicewebflux.domain.dto.MashUpDto;
import com.cygni.restservicewebflux.domain.service.MashUpService;
import com.cygni.restservicewebflux.domain.service.ThrottlingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import v1.MessageTypes;
import v1.RestPaths;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MashUpRestHandlerTest {
  private WebTestClient webTestClient;

  private final String mbId = "f90e8b26-9e52-4669-a5c9-e28529c47894";
  @Mock private MashUpService mashUpService;
  @Mock private ThrottlingService throttlingService;
  @Mock private MashUpOutputConverter mashUpOutputConverter;

  private MashUpRestHandler mashUpRestHandler;

  @BeforeEach
  void setUp() {
    mashUpRestHandler =
        new MashUpRestHandler(mashUpService, throttlingService, mashUpOutputConverter);
  }

  @Disabled
  void getMashUp_should_return_mashupMessage_according_to_contract() {
    //MashUpDto mashUp1 = new MashUpDto(mbId, "desc", new ArrayList<>());
    //BDDMockito.given(mashUpService.createMashupMessage(mbId)).willReturn(Mono.just(mashUp1));
    webTestClient = WebTestClient.bindToController(mashUpRestHandler).build();

    webTestClient
        .get()
        .uri(RestPaths.GET_MASH_UP, mbId)
        .accept(MediaType.parseMediaType(MessageTypes.MASH_UP_1_JSON))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(MashUp.class)
        .value(
            mashUp -> {
              assertThat(mashUp.getMbId()).isEqualTo(mbId);
              assertThat(mashUp.getAlbums()).isNotEmpty();
            });
  }
}
