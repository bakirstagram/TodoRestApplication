package com.todo.rest.service;

import com.todo.rest.entity.TodoEntity;
import com.todo.rest.entity.UserEntity;
import com.todo.rest.exception.UserNotFoundException;
import com.todo.rest.model.Todo;
import com.todo.rest.repository.TodoRepository;
import com.todo.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public Todo createTodo(TodoEntity todoEntity, Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        todoEntity.setUser(user);
        return Todo.toModel(todoRepository.save(todoEntity));
    }

    public Todo completeTodo(Long id) {
        TodoEntity todo = todoRepository.findById(id).get();
        todo.setCompleted(!todo.getCompleted());
        return Todo.toModel(todoRepository.save(todo));

    }
}
