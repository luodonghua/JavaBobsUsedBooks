package com.bobsusedbooks.utils;

import lombok.Getter;

import java.util.List;

@Getter
public class PaginatedList<T> {
    
    private final List<T> items;
    private final long totalCount;
    private final int pageIndex;
    private final int pageSize;
    private final int totalPages;
    private final boolean hasPreviousPage;
    private final boolean hasNextPage;
    
    public PaginatedList(List<T> items, long totalCount, int pageIndex, int pageSize) {
        this.items = items;
        this.totalCount = totalCount;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
        this.hasPreviousPage = pageIndex > 1;
        this.hasNextPage = pageIndex < totalPages;
    }
}
