package org.cyanteam.telemaniacs.core.services;

import java.util.Collection;

/**
 * Simple statistics calculation
 * @author Michael Le
 */
public interface StatisticsService {
    /**
     * Sums all items in collection
     * @param items Collection of items
     * @return Sum of items
     */
    int Sum(Collection<Integer> items);

    /**
     * Calculates average of items in collection
     * @param items Collection of items
     * @return Average of items
     */
    double Average(Collection<Integer> items);
}
