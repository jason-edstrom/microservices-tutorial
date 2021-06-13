package com.baeldung.spring.ratingservice.controller;

import com.baeldung.spring.ratingservice.dto.RatingDto;
import com.baeldung.spring.ratingservice.mapper.RatingMapper;
import com.baeldung.spring.ratingservice.model.Rating;
import com.baeldung.spring.ratingservice.service.RatingService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/rating")
@RestController
public class RatingController {
  private final RatingService ratingService;

  public RatingController(RatingService ratingService) {
    this.ratingService = ratingService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated RatingDto ratingDto) {
    ratingService.save(ratingDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<RatingDto> findById(@PathVariable("id") long id) {
    RatingDto rating = ratingService.findById(id);
    return ResponseEntity.ok(rating);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") long id) {
    ratingService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/page-query")
  public ResponseEntity<Page<RatingDto>> pageQuery(RatingDto ratingDto,
      @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<RatingDto> ratingPage = ratingService.findByCondition(ratingDto, pageable);
    return ResponseEntity.ok(ratingPage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated RatingDto ratingDto, @PathVariable("id") long id) {
    ratingService.update(ratingDto, id);
    return ResponseEntity.ok().build();
  }
}