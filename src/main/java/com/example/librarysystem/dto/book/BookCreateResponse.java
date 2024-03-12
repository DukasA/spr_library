package com.example.librarysystem.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCreateResponse {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private String status;
}
