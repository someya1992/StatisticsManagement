package com.stats.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.LongSummaryStatistics;

import org.springframework.stereotype.Service;

import com.stats.DAO.StatisticsDAO;
import com.stats.DTO.EventDTO;
import com.stats.DTO.StatisticsDTO;
import com.stats.Exception.InvalidEventException;
import com.stats.Exception.MissingEventsException;
import com.stats.util.EventsUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StatisticsService {

	private final StatisticsDAO statisticDAO;

	public StatisticsDTO getStats() throws MissingEventsException {
		StatisticsDTO stats = calculateStats(statisticDAO.getEvents());
		return stats;
	}

	public void addEvent(EventDTO event) throws InvalidEventException {
		if (EventsUtil.isInvalidEvent(event.getTimeStamp())) {
			throw new InvalidEventException("Event has old timestamp : more than 60 seconds");
		}
		statisticDAO.addEvent(event);
	}
	
	private StatisticsDTO calculateStats(List<EventDTO> eventList) throws MissingEventsException {
		LongSummaryStatistics Ystat = eventList.stream().mapToLong(value -> value.getY()).summaryStatistics();
		if (Long.compare(Ystat.getCount(), 0) == 0) {
			throw new MissingEventsException();
		}
		BigDecimal Xsum = eventList.stream().map(value -> value.getX()).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal Xavg = Xsum.divide(new BigDecimal(Ystat.getCount()), 10, RoundingMode.CEILING);
		return new StatisticsDTO((int) Ystat.getCount(), Xsum, Xavg, Ystat.getSum(), Ystat.getAverage());
	}


}
