package com.Applifting.Applifting.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    @Autowired
    UserService userService;


    @BeforeEach
    public void setUpUserControllerTest(){
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
        userController = new UserController(userService);
    }


    @Test
    @Description("test get")
    void get() {

        User user = User.builder()
                        .accessToken(UUID.randomUUID().toString())
                        .username("un")
                        .email("email")
                        .build();

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        ResponseEntity<UserResult> response = userController.get(user.getId());

        assertThat(response).isNotNull();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @Description("test getall")
    void getAll() {

        List<User> d = new ArrayList<>();
        User user = User.builder()
                .accessToken(UUID.randomUUID().toString())
                .username("un")
                .email("email")
                .build();
        d.add(user);

        Page<User> r = new PageImpl<>(d);

        when(userRepository.findAll(Pageable.ofSize(5))).thenReturn(r);

        ResponseEntity<List<UserResult>> response = userController.getAll(Pageable.ofSize(5));

        assertThat(response).isNotNull();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response).isNotNull();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @Description("test post")
    void post() {

        UserResult result = UserResult.builder()
                .username("")
                .accessToken(UUID.randomUUID().toString())
                .email("email@email.com")
                .build();

        ResponseEntity<Void> rs = userController.post(result);

        assertThat(rs).isNotNull();
        assertThat(rs.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

    @Test
    @Description("test delete")
    void delete() {

        ResponseEntity<Void> rs = userController.delete(1);

        assertThat(rs).isNotNull();

        assertThat(rs.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}