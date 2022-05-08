package com.Applifting.Applifting.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserRepository userRepository;

    UserService userService;

    @BeforeEach
    public void setup(){
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void get() {

        User user = User.builder()
                        .email("email")
                        .accessToken(UUID.randomUUID().toString())
                        .username("username")
                        .id(1)
                        .build();

        when(userRepository.findById(1l)).thenReturn(Optional.of(user));

        ResponseEntity<UserResult> data = userService.get(1);

        assertThat(data).isNotNull();
        assertThat(data.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(data.getBody().getUsername()).isEqualTo(user.getUsername());
        assertThat(data.getBody().getAccessToken()).isEqualTo(user.getAccessToken());
        assertThat(data.getBody().getEmail()).isEqualTo(user.getEmail());

    }

    @Test
    void getAll() {

    }

    @Test
    void post() {

        User user = User.builder()
                        .email("email")
                        .accessToken(UUID.randomUUID().toString())
                        .username("username")
                        .id(1)
                        .build();

        UserResult result = UserResult.builder()
                                        .accessToken(user.getAccessToken())
                                        .username(user.getUsername())
                                        .email(user.getEmail())
                                        .build();

        when(userRepository.save(user)).thenReturn(user);

        ResponseEntity<Void> res = userService.post(result);

        assertThat(res).isNotNull();
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void delete() {

        ResponseEntity<Void> data = userService.delete(1l);
        assertThat(data).isNotNull();
        assertThat(data.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}