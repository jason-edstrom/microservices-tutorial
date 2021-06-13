package com.baeldung.spring.bookservice.mapper;

import com.baeldung.spring.bookservice.dto.BookDto;
import com.baeldung.spring.bookservice.model.Book;
import com.baeldung.spring.commonservice.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDto, Book> {
}