package com.stats.util;

import java.math.BigDecimal;

import com.stats.DTO.EventDTO;
import com.stats.DTO.StatisticsDTO;
import com.stats.Exception.InvalidEventException;

public class EventsUtil {

	public static final int MAX_ALLOWED_IN_MILLI_SECONDS = 6000;

	public static boolean isValidEvent(Long eventTimestamp) {
		return System.currentTimeMillis() - eventTimestamp > MAX_ALLOWED_IN_MILLI_SECONDS;
	}

	public static EventDTO convertToDTO(String event) throws InvalidEventException {
		String eventPayload[] = event.split(",");
		if ((eventPayload[0].equals("null") || (eventPayload[1].equals("null")) || (eventPayload[2].equals("null")))) {
			throw new InvalidEventException("Not Valid Data");
		}
		Long timeStamp = Long.valueOf(eventPayload[0]);
		BigDecimal x = new BigDecimal(eventPayload[1]);
		Long y = Long.valueOf(eventPayload[2]);

		return new EventDTO(timeStamp, x, y);
	}

	public static String convertToString(StatisticsDTO stats) {
		int total = stats.getTotal();
		BigDecimal sumX = stats.getSumX();
		BigDecimal avgX = stats.getAvgX();
		Long sumY = stats.getSumY();
		Double avgY = stats.getAvgY();
		return total + "," + sumX + "," + avgX + "," + sumY + "," + avgY;
	}

}
