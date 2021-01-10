package com.stats.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.stats.Controller.StatisticsController;
import com.stats.DTO.EventDTO;
import com.stats.service.StatisticsService;


@WebMvcTest(value = StatisticsController.class)
public class StatisticsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StatisticsService service;

	@Test
	public void getStats() throws Exception {	
		when(service.getStats()).thenReturn(null);
		MvcResult mvcResult =this.mockMvc.perform(get("/stats")).andExpect(status().isOk()).andReturn();
		assertEquals(mvcResult.getResponse().getContentType(),null);

	}
	
	@Test
	public void addEvents() throws Exception {	
		MvcResult mvcResult =this.mockMvc.perform(post("/events")).andDo(print()).andReturn();
		assertEquals(mvcResult.getResponse().getStatus(),status().isOk());

	}
	
	

}
