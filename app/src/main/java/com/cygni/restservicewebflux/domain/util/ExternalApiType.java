package com.cygni.restservicewebflux.domain.util;

public enum ExternalApiType {
  MUSIC_BRAINZ(CacheNames.MUSIC_BRAINZ_CACHE),
  WIKIDATA(CacheNames.WIKIDATA_CACHE),
  WIKIPEDIA(CacheNames.WIKIPEDIA_CACHE),
  COVER_ART_ARCHIVE(CacheNames.COVER_ART_ARCHIVE_CACHE);

  public final String cacheName;

  ExternalApiType(String cacheName) {
    this.cacheName = cacheName;
  }

  public static class CacheNames {
    public static final String MUSIC_BRAINZ_CACHE = "musicBrainz";
    public static final String WIKIDATA_CACHE = "wikidata";
    public static final String WIKIPEDIA_CACHE = "wikipedia";
    public static final String COVER_ART_ARCHIVE_CACHE = "coverArtArchive";
  }
}
