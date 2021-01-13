package com.stats.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.stats.DAO.StatisticsDAO;
import com.stats.DTO.EventDTO;
import com.stats.DTO.StatisticsDTO;
import com.stats.Exception.InvalidEventException;
import com.stats.Exception.MissingEventsException;

@SpringBootTest
public class StatisticsServiceTest {

	@InjectMocks
	private StatisticsService service;

	@Mock
	private StatisticsDAO statisticsDAO;
	
	@Captor
	ArgumentCaptor<EventDTO> eventCaptor; 

	@Test()
	public void getStats_missingEvents_exception() {
		when(statisticsDAO.getEvents()).thenReturn(Collections.emptyList());
		Assertions.assertThrows(MissingEventsException.class, () -> {
			service.getStats();
		});
	}

	@Test
	public void getStats_validEvents_statsPresent() throws MissingEventsException {
		when(statisticsDAO.getEvents()).thenReturn(getEvents());
		StatisticsDTO stats = service.getStats();
		assertThat(stats.getTotal()).isEqualTo(2);
		assertThat(stats.getAvgY()).isEqualTo(1234567890l);
		assertThat(stats.getSumY()).isEqualTo(2469135780l);
		assertThat(stats.getSumX()).isEqualTo(BigDecimal.valueOf(46));
	}

	@Test
	public void addEvent_ValidEvent_InvalidEventException() throws InvalidEventException {
			service.addEvent(new EventDTO(System.currentTimeMillis() - 8000, BigDecimal.ONE, 1234567890l));
			verify(statisticsDAO).addEvent(eventCaptor.capture());
			assertThat(eventCaptor.getValue().getX()).isEqualTo(BigDecimal.ONE);
	}
	
	@Test
	public void addEvent_OldEvent_InvalidEventException() {
		Assertions.assertThrows(InvalidEventException.class, () -> {
			service.addEvent(new EventDTO(System.currentTimeMillis() - 80000, BigDecimal.ONE, 1234567890l));
		});

	}
	
	@Test
	public void addEvent_FutureEvent_InvalidEventException() {
		Assertions.assertThrows(InvalidEventException.class, () -> {
			service.addEvent(new EventDTO(System.currentTimeMillis() + 80000, BigDecimal.ONE, 1234567890l));
		});

	}


	private List<EventDTO> getEvents() {

		List<EventDTO> eventList = new LinkedList() {
			{
				add(new EventDTO(System.currentTimeMillis() - 5000, BigDecimal.ONE, 1234567890l));
				add(new EventDTO(System.currentTimeMillis() - 10000, BigDecimal.valueOf(45), 1234567890l));
			}
		};

		return eventList;
	}
}
