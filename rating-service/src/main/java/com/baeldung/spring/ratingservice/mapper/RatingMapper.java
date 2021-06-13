package com.baeldung.spring.ratingservice.mapper;

import com.baeldung.spring.commonservice.mapper.EntityMapper;
import com.baeldung.spring.ratingservice.dto.RatingDto;
import com.baeldung.spring.ratingservice.model.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper extends EntityMapper<RatingDto, Rating> {
}