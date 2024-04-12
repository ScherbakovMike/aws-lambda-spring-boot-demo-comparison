package com.mikescherbakov.awslambdaspringbootdemocomparison.function;

import com.mikescherbakov.awslambdaspringbootdemocomparison.model.User;
import com.mikescherbakov.awslambdaspringbootdemocomparison.service.UserService;
import java.util.List;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetAllUsers implements Supplier<Mono<List<User>>> {

  private final UserService service;

  @Override
  public Mono<List<User>> get() {
    return service.getAllUsers();
  }
}
