package com.org.stats.Controller;

import com.org.stats.Exception.InvalidEventException;
import com.org.stats.service.StatisticsService;
import com.org.stats.util.EventsUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StatisticsController {

	private final StatisticsService service;

    @GetMapping("/stats")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok(EventsUtil.convertToString(service.getStats()));
    }

    @PostMapping(path = "/event")
    public ResponseEntity<Object> addEvent(@RequestBody String event) throws InvalidEventException {
        service.addEvent(EventsUtil.convertToDTO(event));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
