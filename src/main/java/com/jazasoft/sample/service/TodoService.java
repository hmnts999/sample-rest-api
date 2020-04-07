package com.jazasoft.sample.service;

import com.jazasoft.sample.entity.Todo;

import java.util.Collection;

public interface TodoService {

  Collection<Todo> findAll();

  Todo findOne(Long id);

  Todo save(Todo todo);

  Todo update(Todo todo);

  void delete(Long id);

  boolean exists(Long id);
}
