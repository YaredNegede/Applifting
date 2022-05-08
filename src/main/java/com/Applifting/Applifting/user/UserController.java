package com.Applifting.Applifting.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController  {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResult> get(@PathVariable("id") long id){

        log.debug(String.valueOf(id));

        return userService.get(id);

    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResult>> getAll(Pageable page){

        return userService.getAll(page);

    }

    @PostMapping("/user")
    public ResponseEntity<Void> post(UserResult result){

        return userService.post(result);

    }

    @DeleteMapping("/user{id}")
    public ResponseEntity<Void>  delete(@PathVariable("id")long id){
        log.debug(String.valueOf(id));

        return userService.delete(id);

    }
}
