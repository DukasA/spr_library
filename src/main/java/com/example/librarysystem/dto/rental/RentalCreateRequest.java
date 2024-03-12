package com.example.librarysystem.dto.rental;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalCreateRequest {
    private Long userId;
    private Long bookId;
}
