package com.jazasoft.sample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.cert.Extension;

@NoArgsConstructor
@Data
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;

    @Transient
    private Long categoryId;

    public Todo(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}