package com.example.librarysystem.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
