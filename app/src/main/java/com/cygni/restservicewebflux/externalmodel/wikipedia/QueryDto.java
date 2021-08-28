package com.cygni.restservicewebflux.externalmodel.wikipedia;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QueryDto(@JsonProperty("pages") PagesDto pages) {}
