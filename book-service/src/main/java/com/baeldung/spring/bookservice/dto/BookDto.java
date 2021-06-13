package com.baeldung.spring.bookservice.dto;

import com.baeldung.spring.commonservice.dto.AbstractDto;

public class BookDto extends AbstractDto<Long> {
  private long id;
  private String title;
  private String author;

  public BookDto() {
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getAuthor() {
    return this.author;
  }
}