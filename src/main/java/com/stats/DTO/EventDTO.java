package com.stats.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventDTO {

	private Long timeStamp;

	private BigDecimal x;

	private int y;

}
