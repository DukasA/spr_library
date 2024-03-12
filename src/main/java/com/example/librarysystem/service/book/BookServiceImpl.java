package com.example.librarysystem.service.book;

import com.example.librarysystem.dto.book.*;
import com.example.librarysystem.entity.Book;
import com.example.librarysystem.enums.BookStatus;
import com.example.librarysystem.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public BookGetResponse get(BookGetRequest request){
        Optional<Book> optionalBook = bookRepository.findById(request.getId());

        return mapToGetResponse(optionalBook.get());
    }

    @Override
    public BookListResponse list(BookStatus status) {
        List<BookListResponse.Book> list = bookRepository.findAll()
                .stream()
                .filter(book -> applyFilter(book,status))
                .map(this::mapToListResponse)
                .toList();
        return BookListResponse.builder()
                .data(list)
                .massage(list.isEmpty() ? "All books is rented": null)
                .build();
    }

    private boolean applyFilter(Book book, BookStatus status){
        if(status == null){
            return true;
        }else{
            return book.getStatus() == status;
        }
    }

    private BookListResponse.Book mapToListResponse(Book book){
        return BookListResponse.Book.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .status(book.getStatus().getTitle())
                .build();
    }

    @Override
    public BookCreateResponse create(BookCreateRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setStatus(BookStatus.AVAILABLE);

        book = bookRepository.save(book);

        return mapToCreateResponse(book);
    }
    private BookCreateResponse mapToCreateResponse(Book book){
        return BookCreateResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .status(book.getStatus().getTitle())
                .build();
    }

    private BookGetResponse mapToGetResponse(Book book){
        return BookGetResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .status(book.getStatus().getTitle())
                .build();
    }
}