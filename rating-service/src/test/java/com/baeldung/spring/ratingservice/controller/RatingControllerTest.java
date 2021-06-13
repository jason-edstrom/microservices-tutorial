package com.baeldung.spring.ratingservice.controller;

import com.baeldung.spring.ratingservice.dto.RatingDto;
import com.baeldung.spring.ratingservice.service.RatingService;
import java.util.Collections;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class RatingControllerTest {
  private static final String ENDPOINT_URL = "/ratings";
  @InjectMocks
  private RatingController ratingController;
  @Mock
  private RatingService ratingService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(ratingController)
        //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        //.addFilter(CustomFilter::doFilter)
        .build();
  }

  @Test
  public void findAllByPage() throws Exception {
    Page<RatingDto> page = new PageImpl<>(Collections.singletonList(RatingBuilder.getDto()));

    Mockito.when(ratingService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

    ResultActions resultActions = mockMvc
        .perform(MockMvcRequestBuilders.get(ENDPOINT_URL).accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

    Mockito.verify(ratingService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
    Mockito.verifyNoMoreInteractions(ratingService);

  }

  @Test
  public void getById() throws Exception {
    Mockito.when(ratingService.findById(ArgumentMatchers.anyLong())).thenReturn(RatingBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
    Mockito.verify(ratingService, Mockito.times(1)).findById(1);
    Mockito.verifyNoMoreInteractions(ratingService);
  }

  @Test
  public void save() throws Exception {
    Mockito.when(ratingService.save(ArgumentMatchers.any(RatingDto.class))).thenReturn(RatingBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_URL).contentType(MediaType.APPLICATION_JSON)
        .content(CustomUtils.asJsonString(RatingBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    Mockito.verify(ratingService, Mockito.times(1)).save(ArgumentMatchers.any(RatingDto.class));
    Mockito.verifyNoMoreInteractions(ratingService);
  }

  @Test
  public void update() throws Exception {
    Mockito.when(ratingService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
        .thenReturn(RatingBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT_URL + "/1").contentType(MediaType.APPLICATION_JSON)
        .content(CustomUtils.asJsonString(RatingBuilder.getDto()))).andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(ratingService, Mockito.times(1))
        .update(ArgumentMatchers.any(RatingDto.class), ArgumentMatchers.anyLong());
    Mockito.verifyNoMoreInteractions(ratingService);
  }

  @Test
  public void delete() throws Exception {
    Mockito.doNothing().when(ratingService).deleteById(ArgumentMatchers.anyLong());
    mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1").contentType(MediaType.APPLICATION_JSON)
        .content(CustomUtils.asJsonString(RatingBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(ratingService, Mockito.times(1)).deleteById(Mockito.anyLong());
    Mockito.verifyNoMoreInteractions(ratingService);
  }
}