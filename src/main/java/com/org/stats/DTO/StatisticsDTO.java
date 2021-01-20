package com.org.stats.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatisticsDTO {

	private int total;

	private BigDecimal sumX;

	private BigDecimal avgX;

	private long sumY;

	private double avgY;

}
