package com.stats.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.stats.DTO.EventDTO;
import com.stats.Exception.InvalidEventException;
import com.stats.util.EventsUtil;

@Repository
public class StatisticsDAO {

	private static final List<EventDTO> EVENT_LIST = new ArrayList<>();
	private Object lock = new Object();

	public void addEvent(EventDTO event) throws InvalidEventException {
		synchronized (lock) {
			EVENT_LIST.add(event);
		}
	}

	@Scheduled(fixedRate = 6000, initialDelay = 6000)
	public void clearOld() {
		synchronized (lock) {
			EVENT_LIST.removeIf(event -> (EventsUtil.isValidEvent(event.getTimeStamp())));
		}
	}

	public List<EventDTO> getEvents() {
		return EVENT_LIST;
	}

}
