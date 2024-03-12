package com.example.librarysystem.dto.rental;

import com.example.librarysystem.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalListResponse {


    private List<Rental> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Rental{
        private Long id;
        private User user;
        private Book books;
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
            private String status;
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
}
