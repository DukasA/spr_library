package com.example.librarysystem.service.user;

import com.example.librarysystem.dto.user.UserCreateRequest;
import com.example.librarysystem.dto.user.UserCreateResponse;
import com.example.librarysystem.entity.User;
import com.example.librarysystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserCreateResponse saveUser(UserCreateRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return mapToCreateResponse(user);
    }

    private UserCreateResponse mapToCreateResponse(User user){
        return UserCreateResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
