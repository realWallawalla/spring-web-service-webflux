package com.cygni.restservicewebflux.domain.exception;

import com.cygni.restservicewebflux.domain.util.ExternalApiType;
import org.springframework.http.HttpStatus;

public class ExternalApiException extends Exception {
  private final HttpStatus httpStatus;
  private final ExternalApiType externalApiType;

  public ExternalApiException(HttpStatus httpStatus, ExternalApiType externalApiType) {
    this.httpStatus = httpStatus;
    this.externalApiType = externalApiType;
  }
}
