package com.example.librarysystem.service.book;

import com.example.librarysystem.dto.book.*;
import com.example.librarysystem.enums.BookStatus;

public interface BookService {
    BookListResponse list(BookStatus status);
    BookCreateResponse create(BookCreateRequest request);

    BookGetResponse get(BookGetRequest request);


}
