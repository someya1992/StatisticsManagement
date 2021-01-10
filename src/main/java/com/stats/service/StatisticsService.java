package com.stats.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stats.DTO.EventDTO;
import com.stats.DTO.StatisticsDTO;

@Service
public class StatisticsService {

	private static final List<EventDTO> EVENT_LIST = new ArrayList<>();
	private StatisticsDTO stats;

	public StatisticsDTO getStats() {
		return stats;
	}

	public void addEvent(EventDTO event) {

		EVENT_LIST.add(event);
	}

}
