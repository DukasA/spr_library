package com.example.librarysystem.entity;

import com.example.librarysystem.dto.rental.RentalCreateResponse;
import com.example.librarysystem.enums.BookStatus;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "books")
public class Book extends RentalCreateResponse.Book {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ToString.Include
    @Column(name = "title")
    private String title;

    @ToString.Include
    @Column(name = "author")
    private String author;

    @ToString.Include
    @Column(name = "isbn")
    private String isbn;

    @Enumerated(EnumType.STRING)
    @ToString.Include
    @Column(name = "status")
    private BookStatus status;

}

