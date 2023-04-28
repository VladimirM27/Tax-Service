package com.motrechko.taxservice.pagination;

public record Pagination(int currentPage, int recordsPerPage, int totalRecords) {
    public int getStartIndex() {
        return (currentPage - 1) * recordsPerPage;
    }

    public int getEndIndex() {
        int endIndex = currentPage * recordsPerPage - 1;
        return endIndex >= totalRecords ? totalRecords - 1 : endIndex;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalRecords / recordsPerPage);
    }
}
