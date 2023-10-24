package com.muv.algorithm;

import java.util.*;
import java.util.stream.Collectors;

public class LfuAlgorithm {

    private LinkedHashMap<Long, Integer> cacheRequestToFrequency;
    private final int MAX_CACHE_SIZE;

    public LfuAlgorithm(int maxCacheSize) {
        MAX_CACHE_SIZE = maxCacheSize;
        cacheRequestToFrequency = new LinkedHashMap<>();
    }

    public int count(ArrayList<Long> idRequests) {
        int counter = 0;

        for (long element : idRequests) {
            if (cacheRequestToFrequency.containsKey(element)) {
                cacheRequestToFrequency.replace(element, cacheRequestToFrequency.get(element) + 1);
                continue;
            }

            if (isCacheFull()) {
                cacheRequestToFrequency = sortMapByValues();
                long lastKeyElement = (long) cacheRequestToFrequency.keySet().toArray()[MAX_CACHE_SIZE - 1];
                cacheRequestToFrequency.remove(lastKeyElement);
            }

            cacheRequestToFrequency.put(element, 1);
            counter++;
        }

        return counter;
    }

    private LinkedHashMap<Long, Integer> sortMapByValues() {
        return cacheRequestToFrequency.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    private boolean isCacheFull() {
        return cacheRequestToFrequency.size() >= MAX_CACHE_SIZE;
    }

}
