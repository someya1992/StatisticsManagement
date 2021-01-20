package com.org.stats.DAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.org.stats.service.StatisticsCalculator;
import com.org.stats.util.EventsUtil;
import com.org.stats.DTO.StatisticsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.org.stats.DTO.EventDTO;

@Repository
@RequiredArgsConstructor
public class StatisticsDAO {

    private static final List<EventDTO> eventList = new ArrayList<>();
    private final StatisticsCalculator statisticsCalculator;
    private StatisticsDTO stats = new StatisticsDTO(0, BigDecimal.ZERO,BigDecimal.ZERO,0,0);
    private static final Object lock = new Object();

    public void addEvent(EventDTO event) {
        synchronized (lock) {
            eventList.add(event);
            stats = statisticsCalculator.calculateStats(eventList);
        }
    }

    @Scheduled(fixedRate = 1000, initialDelay = 6000)
    public void clearOld() {
        synchronized (lock) {
            eventList.removeIf(event -> (EventsUtil.isExpired(event.getTimeStamp())));
            stats = statisticsCalculator.calculateStats(eventList);
        }

    }

    public StatisticsDTO getStats() {
        return stats;
    }


}
