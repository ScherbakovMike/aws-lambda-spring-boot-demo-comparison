package com.mikescherbakov.awslambdaspringbootdemocomparison.function;

import com.mikescherbakov.awslambdaspringbootdemocomparison.model.ApiResponse;
import com.mikescherbakov.awslambdaspringbootdemocomparison.model.User;
import com.mikescherbakov.awslambdaspringbootdemocomparison.service.UserService;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateUser implements Function<Mono<User>, Mono<ResponseEntity<ApiResponse>>> {

  private final UserService service;

  @Override
  public Mono<ResponseEntity<ApiResponse>> apply(Mono<User> user) {
      return service.create(user)
          .map(user1 -> ResponseEntity.status(HttpStatus.CREATED)
              .body(new ApiResponse(user1, "User successfully created")));
  }
}
