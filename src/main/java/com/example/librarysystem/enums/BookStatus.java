package com.example.librarysystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookStatus {
    AVAILABLE("Available"),
    RENTED("Rented");

    private final String title;
}
