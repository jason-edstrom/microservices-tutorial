package com.baeldung.spring.bookservice.controller;

import com.baeldung.spring.bookservice.dto.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;

public class BookBuilder {
  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static BookDto getDto() {
    BookDto dto = new BookDto();
    dto.setId(1);
    return dto;
  }
}