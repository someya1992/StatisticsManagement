package com.org.stats.service;

import com.org.stats.util.EventsUtil;
import org.springframework.stereotype.Service;

import com.org.stats.DAO.StatisticsDAO;
import com.org.stats.DTO.EventDTO;
import com.org.stats.DTO.StatisticsDTO;
import com.org.stats.Exception.InvalidEventException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StatisticsService {

    private final StatisticsDAO statisticDAO;

    public StatisticsDTO getStats() {
        return statisticDAO.getStats();
    }

    public void addEvent(EventDTO event) throws InvalidEventException {
        if (EventsUtil.isExpired(event.getTimeStamp())) {
            throw new InvalidEventException("Event has old timestamp : more than 60 seconds");
        }
        statisticDAO.addEvent(event);
    }

}
