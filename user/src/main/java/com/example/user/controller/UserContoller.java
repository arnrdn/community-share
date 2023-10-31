package com.example.user.controller;

import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import com.example.user.entity.User;
import com.example.user.exception.UserNotFoundException;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserContoller {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserRequest req) {
        userService.saveUser(req);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity<UserResponse> findUserWithPosts(@PathVariable("userId") Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserWithPosts(id));
    }

    @GetMapping("/username/{userId}")
    public ResponseEntity<String> findUsernameByUserId(@PathVariable("userId") Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUsernameByUserId(id));
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
