package com.todo.rest.model;

import com.todo.rest.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class User {
    private Long id;
    private String name;
    private List<Todo> todos;

    // converter
    public static User toModel(UserEntity userEntity) {
        User model = new User();
        model.setId(userEntity.getId());
        model.setName(userEntity.getName());
        model.setTodos(userEntity.getTodos().stream().map(Todo::toModel).collect(Collectors.toList()));
        return model;
    }

    public User() {
    }

    public List<Todo> getTodos() {
        return this.todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
