package com.mikescherbakov.awslambdaspringbootdemocomparison.controller;

import com.mikescherbakov.awslambdaspringbootdemocomparison.model.ApiResponse;
import com.mikescherbakov.awslambdaspringbootdemocomparison.model.User;
import com.mikescherbakov.awslambdaspringbootdemocomparison.service.UserService;
import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping
  public Mono<ApiResponse> getAllUsers() {
    return service.getAllUsers()
        .map(users -> new ApiResponse(users,
            MessageFormat.format("{0} result found", users.size())));
  }

  @GetMapping("/count")
  public Mono<ApiResponse> userCount() {
    return service.userCount()
        .map(count -> new ApiResponse(count, MessageFormat.format("Count of Users: {0}", count)));
  }

  @GetMapping("/{id}")
  public Mono<ApiResponse> getByUserId(@PathVariable String id) {
    return service.getByUserId(id)
        .map(user -> new ApiResponse(user, MessageFormat.format("Result found: {0}", user)))
        .defaultIfEmpty(new ApiResponse(null, "User not found"));
  }

  @PostMapping
  public Mono<ResponseEntity<ApiResponse>> create(@RequestBody Mono<User> user) {
    return service.create(user)
        .map(user1 -> ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse(user1, "User successfully created")));
  }

  @PutMapping("/{id}")
  public Mono<ApiResponse> update(@PathVariable String id, @RequestBody Mono<User> user) {
    return service.update(id, user)
        .map(userUpdated -> new ApiResponse(userUpdated, "User successfully updated"));
  }

  @DeleteMapping("/{id}")
  public Mono<ApiResponse> delete(@PathVariable String id) {
    return service.delete(id)
        .map(userDeleted -> new ApiResponse(userDeleted, "User successfully deleted"));
  }
}
