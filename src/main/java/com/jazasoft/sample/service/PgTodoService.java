package com.jazasoft.sample.service;

import com.jazasoft.sample.entity.Category;
import com.jazasoft.sample.entity.Todo;
import com.jazasoft.sample.repository.CategoryRepository;
import com.jazasoft.sample.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PgTodoService implements TodoService {

    private final TodoRepository todoRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public PgTodoService(TodoRepository todoRepository, CategoryRepository categoryRepository) {
        this.todoRepository = todoRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Collection<Todo> findAll() {
        List<Todo> todoList = todoRepository.findAll();
        todoList.forEach(todo -> todo.setCategoryId(todo.getCategory() != null ? todo.getCategory().getId() : null));
        return todoList;
        //return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> findOne(Long id) {
        Optional<Todo> mTodo = todoRepository.findById(id);
        //mTodo.ifPresent(todo -> todo.setCategoryId(todo.setCategory().getId()));
        return mTodo;
        //return todoRepository.findById(id).orElse(null);
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
            Category category = categoryRepository.getOne(todo.getCategoryId());
            mTodo.setCategory(todo.getCategory());
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
