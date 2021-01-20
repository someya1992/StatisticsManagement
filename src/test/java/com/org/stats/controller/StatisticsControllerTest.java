package com.org.stats.controller;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.org.stats.Controller.StatisticsController;
import com.org.stats.DTO.StatisticsDTO;
import com.org.stats.service.StatisticsService;

@WebMvcTest(value = StatisticsController.class)
public class StatisticsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StatisticsService service;

	@Test
	public void getStats_statsPresent_successful() throws Exception {
		when(service.getStats())
				.thenReturn(new StatisticsDTO(3, valueOf(0.09876543212), valueOf(0.09876543212), 5L, 2.5d));
		MvcResult mvcResult = this.mockMvc.perform(get("/stats")).andExpect(status().isOk()).andReturn();
		String[] response = mvcResult.getResponse().getContentAsString().split(",");
		assertEquals(Integer.valueOf(response[0]), 3);
	}


	@Test
	public void addEvents_InvalidEvent_unsuccessful() throws Exception {
		String invalidData = "null,null,null";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/event").content(invalidData))
				.andExpect(status().isNoContent());

	}

	@Test
	public void addEvents_validEvent_successful() throws Exception {
		Long timeStamp = System.currentTimeMillis();
		String invalidData = timeStamp + ",0.1234567890,23456789";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/event").content(invalidData))
				.andExpect(status().isAccepted());

	}

	@Test
	public void addEvents_FutureEvent_successful() throws Exception {
		Long timeStamp = System.currentTimeMillis() + 90000;
		String invalidData = timeStamp + ",0.1234567890,23456789";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/event").content(invalidData))
				.andExpect(status().isNoContent());

	}

}
