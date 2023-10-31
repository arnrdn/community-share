package com.example.user.service;

import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import com.example.user.entity.Post;
import com.example.user.entity.User;
import com.example.user.exception.UserNotFoundException;
import com.example.user.feign.PostClient;
import com.example.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PostClient postClient;

    public void saveUser(UserRequest userRequest) {
        User user = User.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .build();

        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse findUserWithPosts(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id: %s was not found", id)));

        List<Post> postList = postClient.findAllPostsByUser(id);
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .postList(postList)
                .build();

    }

    @Transactional
    public String findUsernameByUserId(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getUsername();
        } else {
            throw new UserNotFoundException(String.format("User with id: %s is not found", id));
        }
    }
}
