package com.example.librarysystem.service.user;

import com.example.librarysystem.dto.user.UserCreateRequest;
import com.example.librarysystem.dto.user.UserCreateResponse;
import com.example.librarysystem.entity.User;

public interface UserService {
    UserCreateResponse saveUser(UserCreateRequest request);
}
