package com.muv.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TwoQueuesAlgorithm {

    private final Deque<Long> queueIn;
    private final Deque<Long> queueOut;
    private final Deque<Long> lruQueue;
    private final double quarter = 0.25;
    private final int SIZE_IN;
    private final int SIZE_OUT;
    private final int SIZE_LRU;

    public TwoQueuesAlgorithm(int maxCacheSize) {
        SIZE_IN = (int) (maxCacheSize * quarter);
        SIZE_OUT = SIZE_IN * 2;
        SIZE_LRU = maxCacheSize - SIZE_IN - SIZE_OUT;

        queueIn = new ArrayDeque<>();
        queueOut = new ArrayDeque<>();
        lruQueue = new ArrayDeque<>();
    }

    public int count(ArrayList<Long> idRequests) {
        int counter = 0;

        for (long element : idRequests) {
            if (!isRequestInCache(element)) {
                counter++;
                if (isQueueInFull()) {
                    if (isQueueOutFull()) {
                        queueOut.removeFirst();
                    }
                    queueOut.addLast(element);
                    queueIn.removeFirst();
                }
                queueIn.addLast(element);
                continue;
            }

            if (queueOut.contains(element)) {
                queueOut.remove(element);
                if (isLruQueueFull()) {
                    lruQueue.removeFirst();
                }
                lruQueue.addLast(element);
                continue;
            }

            if (lruQueue.contains(element)) {
                lruQueue.remove(element);
                lruQueue.addLast(element);
            }

        }

        return counter;
    }

    private boolean isLruQueueFull() {
        return lruQueue.size() >= SIZE_LRU;
    }

    private boolean isQueueInFull() {
        return queueIn.size() >= SIZE_IN;
    }

    private boolean isQueueOutFull() {
        return queueOut.size() >= SIZE_OUT;
    }

    private boolean isRequestInCache(long request) {
        return queueIn.contains(request) || queueOut.contains(request) || lruQueue.contains(request);
    }
}
