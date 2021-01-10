package com.stats.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stats.DTO.EventDTO;
import com.stats.DTO.StatisticsDTO;
import com.stats.service.StatisticsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StatisticsController {

	private final StatisticsService service;

	@GetMapping("/stats")
	public StatisticsDTO get() {
		return service.getStats();
	}

	@PostMapping(path = "/event")
	public void addEvent(@RequestBody EventDTO event) {
		service.addEvent(event);
	}

}
