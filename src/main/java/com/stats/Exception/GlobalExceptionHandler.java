package com.stats.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler class to map the validations with exceptions
 */

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidEventException.class)
	public ResponseEntity<Object> handleTransactionInvalidException(InvalidEventException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(MissingEventsException.class)
	public ResponseEntity<Object> handleTransactionMissingEventsException(MissingEventsException exception) {
		return new ResponseEntity<>("No events for last 60 seconds", HttpStatus.NO_CONTENT);
	}

}
