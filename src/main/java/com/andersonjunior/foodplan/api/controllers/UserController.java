package com.andersonjunior.foodplan.api.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andersonjunior.foodplan.domain.dtos.LoginDto;
import com.andersonjunior.foodplan.domain.models.User;
import com.andersonjunior.foodplan.service.services.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(
        @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
        @RequestParam(name = "size", required = true, defaultValue = "50") Integer size) {
            List<User> users = userService.findAll(page, size);
            return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/email")
    public ResponseEntity<User> findByEmail(
        @RequestParam(name = "value", required = true) String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/name")
    public ResponseEntity<List<User>> findByName(
        @RequestParam(name = "value", required = true) String name) {
        List<User> user = userService.findByName(name);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody User user) {
        userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody User user, @PathVariable Long id) {
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Boolean> login(@Valid @RequestBody LoginDto loginDto) {

        boolean valid = userService.login(loginDto.getEmail(), loginDto.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).build();

    }

}
