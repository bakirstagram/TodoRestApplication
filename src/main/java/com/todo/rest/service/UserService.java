package com.todo.rest.service;

import com.todo.rest.entity.UserEntity;
import com.todo.rest.exception.UserAlreadyExistException;
import com.todo.rest.exception.UserNotFoundException;
import com.todo.rest.model.User;
import com.todo.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepository.findByName(user.getName()) != null)
            throw new UserAlreadyExistException("user already exists!");
        return userRepository.save(user);
    }

    public List<User> findAll() {
        List<User> userEntities = userRepository.findAll().stream().map(User::toModel).collect(Collectors.toList());
        return userEntities;
    }

    public User getOne(Long user_id) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findById(user_id);
        if (user.isEmpty())
            throw new UserNotFoundException("USER NOT FOUND");
        return User.toModel(user.get());
    }

    public UserEntity update(UserEntity user, Long user_id) throws UserNotFoundException {
        UserEntity entity = user;
        user.setId(user_id);
        if (!userRepository.findById(user_id).isPresent())
            throw new UserNotFoundException("USER NOT FOUND!");
        return userRepository.save(user);
    }

    public Long deleteUser(Long id) {
        userRepository.deleteById(id);
        return id;
    }


}
