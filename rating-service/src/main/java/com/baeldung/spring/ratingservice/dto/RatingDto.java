package com.baeldung.spring.ratingservice.dto;

import com.baeldung.spring.commonservice.dto.AbstractDto;

public class RatingDto extends AbstractDto<Long> {
  private long id;
  private Long bookId;
  private int stars;

  public RatingDto() {
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public Long getBookId() {
    return this.bookId;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }

  public int getStars() {
    return this.stars;
  }
}