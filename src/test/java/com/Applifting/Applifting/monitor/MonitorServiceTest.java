package com.Applifting.Applifting.monitor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @BeforeEach
    public void setup(){

        this.monitorRepository = mock(MonitorRepository.class);

        this.monitorService = new MonitorService(monitorRepository,new ModelMapper());

    }

    @Test
    public void get() {

        Monitor mon = Monitor.builder().httpStatusCode(HttpStatus.OK.value())
                .dateOfLastCheck(LocalDateTime.MAX)
                .interval(131231231l)
                .build();

        when(monitorRepository.findById(any())).thenReturn(Optional.of(mon));

        ResponseEntity<MonitoringResult> responseEntity = monitorService.get(1l);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public  void getAll() {
        List<Monitor> data = new ArrayList<>();

        Monitor mon = Monitor.builder().httpStatusCode(HttpStatus.OK.value())
                .dateOfLastCheck(LocalDateTime.MAX)
                .interval(131231231l)
                .build();

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
                                .dateOfCheck(LocalDateTime.MIN)
                                .httpStatusCode(HttpStatus.OK.value())
                                .build();

        Monitor mon2 = Monitor.builder()
                .dateOfLastCheck(LocalDateTime.MIN)
                .httpStatusCode(HttpStatus.OK.value())
                .build();

        when(monitorRepository.save(any())).thenReturn(mon2);

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