package com.jazasoft.sample.service;

import com.jazasoft.sample.entity.Todo;
import com.jazasoft.sample.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class PgTodoService implements TodoService {

  private final TodoRepository todoRepository;

  @Autowired
  public PgTodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Override
  public Collection<Todo> findAll() {
    return todoRepository.findAll();
  }

  @Override
  public Todo findOne(Long id) {
    return todoRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public Todo save(Todo todo) {
    return todoRepository.save(todo);
  }

  @Override
  @Transactional
  public Todo update(Todo todo) {
    Todo mTodo = todoRepository.findById(todo.getId()).orElse(null);
    if (mTodo != null) {
      mTodo.setName(todo.getName());
    }
    return mTodo;
  }

  @Override
  @Transactional
  public void delete(Long id) {
    todoRepository.deleteById(id);
  }

  @Override
  public boolean exists(Long id) {
    return todoRepository.existsById(id);
  }
}
