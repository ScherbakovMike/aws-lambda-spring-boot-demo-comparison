package com.mikescherbakov.awslambdaspringbootdemocomparison.service;

import com.mikescherbakov.awslambdaspringbootdemocomparison.model.User;
import com.mikescherbakov.awslambdaspringbootdemocomparison.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;


@Service
public class UserService {

  private final UserRepository repository;

  @Autowired
  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public Mono<List<User>> getAllUsers() {
    return repository.findAll()
        .collectList();
  }

  public Mono<Integer> userCount() {
    return repository.count();
  }

  public Mono<User> getByUserId(@PathVariable String id) {
    return repository.findById(id);
  }

  public Mono<User> create(@RequestBody Mono<User> user) {
    return user.flatMap(repository::save);
  }

  public Mono<User> update(@PathVariable String id, @RequestBody Mono<User> user) {
    return user.map(userToUpdate -> {
          userToUpdate.setId(id);
          return userToUpdate;
        })
        .flatMap(repository::update);
  }

  public Mono<User> delete(@PathVariable String id) {
    return repository.delete(id);
  }
}
