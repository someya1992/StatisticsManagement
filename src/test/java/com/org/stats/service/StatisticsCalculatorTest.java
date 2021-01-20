package com.org.stats.service;

import com.org.stats.DTO.EventDTO;
import com.org.stats.DTO.StatisticsDTO;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class StatisticsCalculatorTest {

    @InjectMocks
    private StatisticsCalculator statsCalc;

    @Test
    public void calculateStats_eventPresent_returnsStats() {
        StatisticsDTO stats = statsCalc.calculateStats(getEvents());
        assertThat(stats.getTotal()).isEqualTo(2);
        assertThat(stats.getAvgY()).isEqualTo(1234567890L);
        assertThat(stats.getSumY()).isEqualTo(2469135780L);
        assertThat(stats.getSumX()).isEqualTo(BigDecimal.valueOf(46));
    }

    @Test
    public void calculateStats_eventAbsent_returnsStats() {

        StatisticsDTO stats = statsCalc.calculateStats(Collections.emptyList());
        assertThat(stats.getTotal()).isEqualTo(0);
        assertThat(stats.getAvgY()).isEqualTo(0);
        assertThat(stats.getSumY()).isEqualTo(0);
        assertThat(stats.getSumX()).isEqualTo(BigDecimal.ZERO);
    }


    private List<EventDTO> getEvents() {

        List<EventDTO> eventList = new LinkedList<EventDTO>() {
            {
                add(new EventDTO(System.currentTimeMillis() - 5000, BigDecimal.ONE, 1234567890L));
                add(new EventDTO(System.currentTimeMillis() - 10000, BigDecimal.valueOf(45), 1234567890L));
            }
        };

        return eventList;
    }

}
