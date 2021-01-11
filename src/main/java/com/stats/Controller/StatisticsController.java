package com.stats.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stats.Exception.InvalidEventException;
import com.stats.Exception.MissingEventsException;
import com.stats.service.StatisticsService;
import com.stats.util.EventsUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StatisticsController {

	private final StatisticsService service;

	@GetMapping("/stats")
	public ResponseEntity<String> get() throws MissingEventsException {
		return ResponseEntity.ok(EventsUtil.convertToString(service.getStats()));
	}

	@PostMapping(path = "/event")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void addEvent(@RequestBody String event) throws InvalidEventException {
		service.addEvent(EventsUtil.convertToDTO(event));
	}

}
