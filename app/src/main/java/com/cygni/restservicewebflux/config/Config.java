package com.cygni.restservicewebflux.config;

import com.cygni.restservicewebflux.domain.util.ExternalApiType;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class Config {

  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager(
        ExternalApiType.MUSIC_BRAINZ.cacheName,
        ExternalApiType.WIKIPEDIA.cacheName,
        ExternalApiType.WIKIDATA.cacheName,
        ExternalApiType.COVER_ART_ARCHIVE.cacheName);
  }
}
