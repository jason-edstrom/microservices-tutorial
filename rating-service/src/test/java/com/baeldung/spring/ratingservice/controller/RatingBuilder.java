package com.baeldung.spring.ratingservice.controller;

import com.baeldung.spring.ratingservice.dto.RatingDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;

public class RatingBuilder {
  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static RatingDto getDto() {
    RatingDto dto = new RatingDto();
    dto.setId(1);
    return dto;
  }
}