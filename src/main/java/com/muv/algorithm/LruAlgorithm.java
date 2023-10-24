package com.muv.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class LruAlgorithm {

    private final int MAX_CACHE_SIZE;
    private final Deque<Long> cache;

    public LruAlgorithm(int maxCacheSize) {
        MAX_CACHE_SIZE = maxCacheSize;
        cache = new ArrayDeque<>();
    }

    public int count(ArrayList<Long> idRequests) {
        int counter = 0;

        for (long element : idRequests) {
            if (!cache.contains(element)) {
                if (isCacheFull()) {
                    cache.removeFirst();
                }
                cache.addLast(element);
                counter++;
                continue;
            }
            cache.removeFirst();
            cache.addLast(element);
        }

        return counter;
    }

    private boolean isCacheFull() {
        return cache.size() >= MAX_CACHE_SIZE;
    }

}
