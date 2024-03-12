package com.example.librarysystem.repository;

import com.example.librarysystem.entity.Book;
import com.example.librarysystem.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
