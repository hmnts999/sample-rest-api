package com.jazasoft.sample.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Todo {

  private Long id;

  private String name;

  public Todo(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
