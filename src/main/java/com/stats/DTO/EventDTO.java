package com.stats.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class EventDTO {

	@NotNull(message = "TimeStamp is required")
	private Long timeStamp;

	@NotNull(message = "x is required")
	private BigDecimal x;

	@NotNull(message = "y is required")
	private int y;

}
