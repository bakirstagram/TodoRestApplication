package com.todo.rest.model;

import com.todo.rest.entity.TodoEntity;

public class Todo {
    private Long id;
    private String title;
    private Boolean completed;

    public static Todo toModel(TodoEntity todoEntity) {
        Todo todo = new Todo();
        todo.setId(todoEntity.getId());
        todo.setTitle(todoEntity.getTitle());
        todo.setCompleted(todoEntity.getCompleted());

        return todo;
    }

    public Todo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
