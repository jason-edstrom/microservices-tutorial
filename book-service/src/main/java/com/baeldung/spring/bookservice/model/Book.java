package com.baeldung.spring.bookservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {

  @Id
  private long id;
  private String title;
  private String author;
}
