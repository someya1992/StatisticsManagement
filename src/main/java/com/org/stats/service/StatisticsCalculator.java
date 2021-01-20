package com.org.stats.service;

import com.org.stats.DTO.EventDTO;
import com.org.stats.DTO.StatisticsDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.LongSummaryStatistics;

@Component
public class StatisticsCalculator {

    public StatisticsDTO calculateStats(List<EventDTO> eventList) {
        LongSummaryStatistics Ystat = eventList.stream().mapToLong(EventDTO::getY).summaryStatistics();
        BigDecimal Xsum = BigDecimal.ZERO;
        BigDecimal Xavg = BigDecimal.ZERO;
        if (!(Ystat.getCount() == 0)) {
            Xsum = eventList.stream().map(EventDTO::getX).reduce(BigDecimal.ZERO, BigDecimal::add);
            Xavg = Xsum.divide(new BigDecimal(Ystat.getCount()), 10, RoundingMode.CEILING);
        }
        return new StatisticsDTO((int) Ystat.getCount(), Xsum, Xavg, Ystat.getSum(), Ystat.getAverage());
    }

}
