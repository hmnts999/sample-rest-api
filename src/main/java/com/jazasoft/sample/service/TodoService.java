package com.jazasoft.sample.service;

import com.jazasoft.sample.entity.Todo;

import java.util.Collection;
import java.util.Optional;

public interface TodoService {

  Collection<Todo> findAll();

  Optional<Todo> findOne(Long id);

  Todo save(Todo todo);

  Todo update(Todo todo);

  void delete(Long id);

  boolean exists(Long id);
}
