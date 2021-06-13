package com.baeldung.spring.ratingservice.service;

import com.baeldung.spring.ratingservice.dto.RatingDto;
import com.baeldung.spring.ratingservice.mapper.RatingMapper;
import com.baeldung.spring.ratingservice.model.Rating;
import com.baeldung.spring.ratingservice.repository.RatingRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RatingService {
  private final RatingRepository repository;
  private final RatingMapper ratingMapper;

  public RatingService(RatingRepository repository, RatingMapper ratingMapper) {
    this.repository = repository;
    this.ratingMapper = ratingMapper;
  }

  public RatingDto save(RatingDto ratingDto) {
    Rating entity = ratingMapper.toEntity(ratingDto);
    return ratingMapper.toDto(repository.save(entity));
  }

  public void deleteById(long id) {
    repository.deleteById(id);
  }

  public RatingDto findById(long id) {
    return ratingMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
  }

  public Page<RatingDto> findByCondition(RatingDto ratingDto, Pageable pageable) {
    Rating rating = ratingMapper.toEntity(ratingDto);
    Page<Rating> entityPage = repository.findAll(pageable);
    List<Rating> entities = entityPage.getContent();
    return new PageImpl<>(ratingMapper.toDto(entities), pageable, entityPage.getTotalElements());
  }

  public RatingDto update(RatingDto ratingDto, long id) {
    RatingDto data = findById(id);
    Rating entity = ratingMapper.toEntity(ratingDto);
    BeanUtils.copyProperties(data,entity);
    return save(ratingMapper.toDto(entity));
  }
}