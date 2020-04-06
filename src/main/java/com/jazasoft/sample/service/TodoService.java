package com.jazasoft.sample.service;

import com.jazasoft.sample.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class TodoService {
  private Map<Long, Todo> todoCache = new HashMap<>();
  long counter = 0;

  public TodoService() {
    Todo todo1 = new Todo(1L, "Go to Shopping");
    Todo todo2 = new Todo(2L, "Watch Movie");

    todoCache.put(todo1.getId(), todo1);
    todoCache.put(todo2.getId(), todo2);

    counter = 3;
  }

  public Collection<Todo> findAll() {
    return todoCache.values();
  }

  public Todo findOne(Long id) {
    return todoCache.get(id);
  }

  public Todo save(Todo todo) {
    todo.setId(counter++);
    todoCache.put(todo.getId(), todo);
    return todo;
  }

  public Todo update(Todo todo) {
    Todo mTodo = todoCache.get(todo.getId());
    mTodo.setName(todo.getName());
    return mTodo;
  }

  public void delete(Long id) {
    todoCache.remove(id);
  }

  public boolean exists(Long id) {
    return todoCache.containsKey(id);
  }
}
