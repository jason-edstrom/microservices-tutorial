package com.baeldung.spring.ratingservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rating {

  @Id
  private long id;
  private Long bookId;
  private int stars;
}
