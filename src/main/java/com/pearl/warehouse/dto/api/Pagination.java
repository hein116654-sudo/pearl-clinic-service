package com.pearl.warehouse.dto.api;
import lombok.*;
@Getter
@Setter
public class Pagination {

    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

    public Pagination(int page, int size, long totalElements, int totalPages, boolean hasNext, boolean hasPrevious) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }
}
