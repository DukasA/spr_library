package com.example.librarysystem.service.rental;

import com.example.librarysystem.dto.rental.RentalCreateRequest;
import com.example.librarysystem.dto.rental.RentalCreateResponse;
import com.example.librarysystem.dto.rental.RentalListResponse;

public interface RentalService {
    RentalCreateResponse create(Long bookId);

    RentalListResponse list(boolean overdueOnly);
}
