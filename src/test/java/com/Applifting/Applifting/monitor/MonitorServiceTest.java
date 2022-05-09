package com.Applifting.Applifting.monitor;

import com.Applifting.Applifting.user.User;
import com.Applifting.Applifting.user.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MonitorServiceTest {

    MonitorRepository monitorRepository;

    MonitorService monitorService;

    UserRepository userRepository;

    @BeforeEach
    public void setup(){

        this.monitorRepository = mock(MonitorRepository.class);

        this.userRepository = mock(UserRepository.class);

        this.monitorService = new MonitorService(monitorRepository,userRepository);

    }

    @Test
    public void get() {

        User user = new User();

        user.setId(1);

        Monitor mon = Monitor.builder().httpStatusCode(HttpStatus.OK.value())
                .dateOfLastCheck(Instant.now().toEpochMilli())
                .interval(131231231l)
                .build();

        mon.setUser(user);

        when(monitorRepository.findById(any())).thenReturn(Optional.of(mon));

        ResponseEntity<MonitoringResult> responseEntity = monitorService.get(1l);

        assertThat(responseEntity).isNotNull();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public  void getAll() {

        User user = new User();

        user.setId(1);

        List<Monitor> data = new ArrayList<>();

        Monitor mon = Monitor.builder().httpStatusCode(HttpStatus.OK.value())
                .dateOfLastCheck(Instant.now().toEpochMilli())
                .interval(131231231l)
                .build();

        mon.setUser(user);

        data.add(mon);

        Page<Monitor> res = new PageImpl<>(data);

        when(monitorRepository.findAll(Pageable.ofSize(3))).thenReturn(res);

        ResponseEntity<List<MonitoringResult>> responseEntity = monitorService.getAll(Pageable.ofSize(3));

        assertThat(responseEntity).isNotNull();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void post() {

        MonitoringResult mon = MonitoringResult.builder()
                                            .dateOfCheck(Instant.now().toEpochMilli())
                                            .httpStatusCode(HttpStatus.OK.value())
                                            .build();

        Monitor mon2 = Monitor.builder()
                            .dateOfLastCheck(Instant.now().toEpochMilli())
                            .httpStatusCode(HttpStatus.OK.value())
                            .build();

        when(monitorRepository.save(any())).thenReturn(mon2);

        User user = new User();

        user.setId(1);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        ResponseEntity<Void> responseEntity = monitorService.post(mon);

        assertThat(responseEntity).isNotNull();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void delete() {

        ResponseEntity<Void> responseEntity = monitorService.delete(1l);

        assertThat(responseEntity).isNotNull();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}