package com.todo.rest.controller;

import com.todo.rest.entity.UserEntity;
import com.todo.rest.exception.UserAlreadyExistException;
import com.todo.rest.exception.UserNotFoundException;
import com.todo.rest.repository.UserRepository;
import com.todo.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public ResponseEntity getUsers(){
//        try{
//            return ResponseEntity.ok(userService.findAll());
//        } catch (Exception e){
//            return (ResponseEntity) ResponseEntity.badRequest().body("Fucking Bullshit");
//        }
//    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("user successfully saved");
        } catch (UserAlreadyExistException e) {
            return (ResponseEntity) ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PutMapping
//    public ResponseEntity updateUser(@RequestBody UserEntity user, @RequestParam Long user_id) {
//        try {
//            return ResponseEntity.ok(userService.update(user, user_id));
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
