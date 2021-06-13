package com.baeldung.spring.bookservice.service;

import com.baeldung.spring.bookservice.dto.BookDto;
import com.baeldung.spring.bookservice.mapper.BookMapper;
import com.baeldung.spring.bookservice.model.Book;
import com.baeldung.spring.bookservice.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookService {
  private final BookRepository repository;
  private final BookMapper bookMapper;

  public BookService(BookRepository repository, BookMapper bookMapper) {
    this.repository = repository;
    this.bookMapper = bookMapper;
  }

  public BookDto save(BookDto bookDto) {
    Book entity = bookMapper.toEntity(bookDto);
    return bookMapper.toDto(repository.save(entity));
  }

  public void deleteById(long id) {
    repository.deleteById(id);
  }

  public BookDto findById(long id) {
    return bookMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
  }

  public Page<BookDto> findByCondition(BookDto bookDto, Pageable pageable) {
    Book book = bookMapper.toEntity(bookDto);
    Page<Book> entityPage = repository.findAll(pageable);
    List<Book> entities = entityPage.getContent();
    return new PageImpl<>(bookMapper.toDto(entities), pageable, entityPage.getTotalElements());
  }

  public BookDto update(BookDto bookDto, long id) {
    BookDto data = findById(id);
    Book entity = bookMapper.toEntity(bookDto);
    BeanUtils.copyProperties(data, entity);
    return save(bookMapper.toDto(entity));
  }
}