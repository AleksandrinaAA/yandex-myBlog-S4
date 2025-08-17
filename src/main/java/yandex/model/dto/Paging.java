package yandex.model.dto;

public record Paging(int pageNumber, int pageSize, boolean hasNext, boolean hasPrevious) {
    public static Paging of(int pageNumber, int pageSize, long totalItems) {
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        return new Paging(
                pageNumber,
                pageSize,
                pageNumber < totalPages,
                pageNumber > 1
        );
    }
}
