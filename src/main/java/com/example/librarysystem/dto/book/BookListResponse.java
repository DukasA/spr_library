package com.example.librarysystem.dto.book;

import com.example.librarysystem.enums.BookStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookListResponse {
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<Book> data;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String massage;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Book{
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private String status;
    }
}
