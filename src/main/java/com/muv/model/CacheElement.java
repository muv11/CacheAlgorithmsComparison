package com.muv.model;

public class CacheElement {

    private final long request;
    private int nextIndex;

    public CacheElement(long request) {
        this.request = request;
    }

    public CacheElement(long request, int nextIndex) {
        this.request = request;
        this.nextIndex = nextIndex;
    }

    public long getRequest() {
        return request;
    }

    public int getNextIndex() {
        return nextIndex;
    }

}
