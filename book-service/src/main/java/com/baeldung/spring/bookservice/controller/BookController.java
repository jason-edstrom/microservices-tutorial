package com.baeldung.spring.bookservice.controller;

import com.baeldung.spring.bookservice.dto.BookDto;
import com.baeldung.spring.bookservice.mapper.BookMapper;
import com.baeldung.spring.bookservice.model.Book;
import com.baeldung.spring.bookservice.service.BookService;
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

@RequestMapping("/api/book")
@RestController
public class BookController {
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated BookDto bookDto) {
    bookService.save(bookDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDto> findById(@PathVariable("id") long id) {
    BookDto book = bookService.findById(id);
    return ResponseEntity.ok(book);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") long id) {
    bookService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/page-query")
  public ResponseEntity<Page<BookDto>> pageQuery(BookDto bookDto,
      @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<BookDto> bookPage = bookService.findByCondition(bookDto, pageable);
    return ResponseEntity.ok(bookPage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated BookDto bookDto, @PathVariable("id") long id) {
    bookService.update(bookDto, id);
    return ResponseEntity.ok().build();
  }
}