package com.org.stats.service;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import com.org.stats.DTO.StatisticsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.org.stats.DAO.StatisticsDAO;
import com.org.stats.DTO.EventDTO;
import com.org.stats.Exception.InvalidEventException;

@SpringBootTest
public class StatisticsServiceTest {

    @InjectMocks
    private StatisticsService service;

    @Mock
    private StatisticsDAO statisticsDAO;

    @Captor
    ArgumentCaptor<EventDTO> eventCaptor;

    public void getStat_successfulRequest() {
        when(service.getStats())
                .thenReturn(new StatisticsDTO(3, valueOf(0.09876543212), valueOf(0.09876543212), 5L, 2.5d));
        verify(statisticsDAO).getStats();
    }

    @Test
    public void addEvent_ValidEvent_InvalidEventException() throws InvalidEventException {
        service.addEvent(new EventDTO(System.currentTimeMillis() - 8000, BigDecimal.ONE, 1234567890L));
        verify(statisticsDAO).addEvent(eventCaptor.capture());
        assertThat(eventCaptor.getValue().getX()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void addEvent_OldEvent_InvalidEventException() {
        Assertions.assertThrows(InvalidEventException.class, () -> service.addEvent(new EventDTO(System.currentTimeMillis() - 80000, BigDecimal.ONE, 1234567890L)));

    }

    @Test
    public void addEvent_FutureEvent_InvalidEventException() {
        Assertions.assertThrows(InvalidEventException.class, () -> service.addEvent(new EventDTO(System.currentTimeMillis() + 80000, BigDecimal.ONE, 1234567890L)));

    }
}
