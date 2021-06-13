package com.baeldung.spring.commonservice.dto;

import java.time.LocalDateTime;

public class AbstractDto<E> {

  private E id;

  private LocalDateTime createAt;

  private LocalDateTime lastModifiedAt;

  private String createdBy;

  private String lastModifiedBy;

  public E getId() {
    return id;
  }

  public void setId(E id) {
    this.id = id;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getLastModifiedAt() {
    return lastModifiedAt;
  }

  public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
    this.lastModifiedAt = lastModifiedAt;
  }
}