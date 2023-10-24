package com.muv.algorithm;

import com.muv.model.CacheElement;

import java.util.*;

public class OptimalAlgorithm {

    private final Map<Long, TreeSet<Integer>> requestToIndexes;
    private final int MAX_CACHE_SIZE;
    private final int MAX_REQUEST_SIZE;

    public OptimalAlgorithm(final int MAX_CACHE_SIZE, final int MAX_REQUEST_SIZE, Map<Long, TreeSet<Integer>> requestToIndexes) {
        this.MAX_CACHE_SIZE = MAX_CACHE_SIZE;
        this.MAX_REQUEST_SIZE = MAX_REQUEST_SIZE;
        this.requestToIndexes = requestToIndexes;
    }

    public int count(ArrayList<Long> idRequests) {
        TreeSet<CacheElement> cache = new TreeSet<>(Comparator.comparingInt(CacheElement::getNextIndex));
        int counter = 0;
        for (int i = 0; i < MAX_REQUEST_SIZE; i++) {
            long request = idRequests.get(i);

            if (!isRequestInCache(cache, request)) {
                counter++;
                if (isCacheFull(cache)) {
                    cache.remove(cache.last());
                }
                cache.add(new CacheElement(request, assignIndex(request, i)));
            } else {
                updateCacheElementIndex(cache, request, i);
            }
        }
        return counter;
    }

    public void updateCacheElementIndex(TreeSet<CacheElement> cache, long request, int currentRequestIndex) {
        removeCacheElementByRequest(cache, request);
        cache.add(new CacheElement(request, assignIndex(request, currentRequestIndex)));
    }

    public void removeCacheElementByRequest(TreeSet<CacheElement> cache, long request) {
        for (CacheElement element : cache) {
            if (element.getRequest() == request) {
                cache.remove(element);
                break;
            }
        }
    }

    public int assignIndex(long request, int currentRequestIndex) {
        try {
            return findRelevantIndex(requestToIndexes.get(request), currentRequestIndex);
        } catch (NoSuchElementException e) {
            return MAX_REQUEST_SIZE + 1;
        }
    }

    public int findRelevantIndex(TreeSet<Integer> indexSet, int currentRequestIndex) {
        int index = indexSet.first();
        while (index <= currentRequestIndex) {
            indexSet.remove(index);
            index = indexSet.first();
        }
        return index;
    }

    private boolean isRequestInCache(TreeSet<CacheElement> cache, Long request) {
        return cache.stream().map(CacheElement::getRequest).anyMatch(request::equals);
    }

    private boolean isCacheFull(TreeSet<CacheElement> cache) {
        return cache.size() == MAX_CACHE_SIZE;
    }
}
