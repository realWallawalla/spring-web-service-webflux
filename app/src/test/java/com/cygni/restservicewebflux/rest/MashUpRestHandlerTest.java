package com.cygni.restservicewebflux.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.cygni.mashup._1_0.Album;
import com.cygni.mashup._1_0.MashUp;
import com.cygni.restservicewebflux.domain.dto.AlbumDto;
import com.cygni.restservicewebflux.domain.dto.MashUpDto;
import com.cygni.restservicewebflux.domain.service.MashUpService;
import com.cygni.restservicewebflux.domain.service.ThrottlingService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestClientException;
import reactor.core.publisher.Mono;
import v1.MessageTypes;
import v1.RestPaths;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MashUpRestHandlerTest {
  private WebTestClient webTestClient;
  private final String mbId = "f90e8b26-9e52-4669-a5c9-e28529c47894";

  @MockBean private MashUpService mashUpService;
  @MockBean private ThrottlingService throttlingService;

  @Autowired private MashUpRestHandler mashUpRestHandler;

  @BeforeEach
  void setUp() {
    webTestClient = WebTestClient.bindToController(mashUpRestHandler).build();
  }

  @Test
  void getMashUp_should_return_mashupMessage_according_to_contract() {
    MashUpDto expectedMashUp =
        new MashUpDto(mbId, "desc", List.of(new AlbumDto("albumId", "albumTitle", "imageUrl")));
    when(mashUpService.createMashupMessage(mbId)).thenReturn(Mono.just(expectedMashUp));

    webTestClient
        .get()
        .uri(RestPaths.GET_MASH_UP, mbId)
        .accept(MediaType.parseMediaType(MessageTypes.MASH_UP_1_JSON))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(MashUp.class)
        .value(
            mashUpOutput -> {
              Album albumOutput = mashUpOutput.getAlbums().get(0);
              assertThat(mashUpOutput.getMbId()).isEqualTo(mbId);
              assertThat(mashUpOutput.getDescription()).isEqualTo("desc");
              assertThat(albumOutput.getId()).isEqualTo("albumId");
              assertThat(albumOutput.getTitle()).isEqualTo("albumTitle");
              assertThat(albumOutput.getImageUrl()).isEqualTo("imageUrl");
            });
  }

  @Test
  void getMashUp_should_return_failed_dependency_as_status_on_error() {
    when(mashUpService.createMashupMessage(mbId)).thenReturn(Mono.error(() -> new RestClientException("error")));
    webTestClient.get()
        .uri(RestPaths.GET_MASH_UP, mbId)
        .accept(MediaType.parseMediaType(MessageTypes.MASH_UP_1_JSON))
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatus.FAILED_DEPENDENCY);
  }

  @Test
  void getMashUp_should_return_bad_request_when_mbId_is_incorrect_length() {
    webTestClient.get()
        .uri(RestPaths.GET_MASH_UP, "f90e8b26-9e52-4669-e285")
        .accept(MediaType.parseMediaType(MessageTypes.MASH_UP_1_JSON))
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  void getMashUp_should_return_bad_request_when_mbId_is_not_hexadecimal() {
    webTestClient.get()
        .uri(RestPaths.GET_MASH_UP, "k90e8b26-9e52-4669-a5c9-e28529c47894")
        .accept(MediaType.parseMediaType(MessageTypes.MASH_UP_1_JSON))
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatus.BAD_REQUEST);
  }
}
