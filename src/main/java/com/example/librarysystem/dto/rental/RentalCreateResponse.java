package com.example.librarysystem.dto.rental;

import com.example.librarysystem.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalCreateResponse {
    private Long id;
    private User user;
    private Book book;
    private String rentalDate;
    private String dueDate;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Book{
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private BookStatus status;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class User{
        private Long id;
        private String firstName;
        private String lastName;
    }
}
