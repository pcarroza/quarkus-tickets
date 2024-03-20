package org.citadel.entities.DTOs;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

public record PaginatedResponse<T>(Long numberOfRegistry, int totalPages, int currentPage, List<T> data) {

    public PaginatedResponse(PanacheQuery<T> query) {
        this(query.count(), query.pageCount(), query.page().index + 1, query.list());
    }
}
