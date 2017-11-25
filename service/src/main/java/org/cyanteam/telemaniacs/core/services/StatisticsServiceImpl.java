package org.cyanteam.telemaniacs.core.services;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Override
    public int Sum(Collection<Integer> items) {
        return items.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public double Average(Collection<Integer> items) {
        return (double) Sum(items) / items.size();
    }
}
