package com.example.librarysystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "rented")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    @Column(name = "id")
    private Long id;

    @ToString.Include
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Include
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ToString.Include
    @Column(name = "rented_date")
    private LocalDate rentedDate;

    @ToString.Include
    @Column(name = "due_date")
    private LocalDate dueDate;
}