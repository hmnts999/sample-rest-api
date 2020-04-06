package com.jazasoft.sample.restcontroller;

import com.jazasoft.sample.entity.Todo;
import com.jazasoft.sample.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoRestController {

  @Autowired
  private TodoService todoService;

  @GetMapping
  public ResponseEntity<?> findAll() {
    Collection<Todo> todos = todoService.findAll();
//    return new ResponseEntity<>(todos, HttpStatus.OK);
    return ResponseEntity.ok(todos);
  }

  @GetMapping("/{todoId}")
  public ResponseEntity<?> findOne(@PathVariable("todoId") Long id) {
    Todo todo = todoService.findOne(id);
    if (todo == null) {
//      return ResponseEntity.notFound().build();
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(todo);
  }

  @PostMapping
  public ResponseEntity<?> save(@RequestBody Todo todo) {
    todo = todoService.save(todo);
//    return ResponseEntity.created(URI).body(todo);
    return new ResponseEntity<>(todo, HttpStatus.CREATED);
  }

  @PutMapping("/{todoId}")
  public ResponseEntity<?> update(@PathVariable("todoId") Long id, @RequestBody Todo todo) {
    if (!todoService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    todo.setId(id);
    todo = todoService.update(todo);
    return ResponseEntity.ok(todo);
  }

  @DeleteMapping("/{todoId}")
  public ResponseEntity<?> delete(@PathVariable("todoId") Long id) {
    if (!todoService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    todoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
