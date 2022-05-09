package com.Applifting.Applifting.monitor;

import com.Applifting.Applifting.user.User;
import com.Applifting.Applifting.user.UserResult;
import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MonitorControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Description("should return 404")
    void getpost() {

        UserResult ur = UserResult.builder()
                .username(UUID.randomUUID().toString())
                .email(UUID.randomUUID().toString())
                .accessToken("")
                .build();

        ResponseEntity<UserResult> query = this.restTemplate.postForEntity("http://localhost:" + port + "/", ur, null);

        assertThat(query.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @Description("should return Monitoring result")
    void getpost2() {

        String at = "A872AC42-3C07-6184-640A-B4ABB8B78C67";

        UserResult ur = UserResult.builder()
                        .username(UUID.randomUUID().toString())
                        .email(UUID.randomUUID().toString())
                        .accessToken(at)
                        .build();


        ResponseEntity<UserResult> query = this.restTemplate.postForEntity("http://localhost:" + port + "/", ur, null);

        assertThat(query.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}