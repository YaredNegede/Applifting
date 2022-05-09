package com.Applifting.Applifting.monitor;

import com.Applifting.Applifting.user.User;
import com.Applifting.Applifting.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonitorService {

    private final MonitorRepository monitorRepository;

    private final UserRepository userRepository;

    public MonitorService(MonitorRepository monitorRepository,UserRepository userRepository) {
        this.monitorRepository = monitorRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<MonitoringResult> get(long id){

        Optional<Monitor> found = this.monitorRepository.findById(id);

        if(found.isEmpty())
            return ResponseEntity.notFound().build();

        found.ifPresent(monitor -> {




        });

        Monitor monitor = found.get();

        MonitoringResult result = map(monitor);

        return ResponseEntity.of(Optional.of(result));


    }

    private MonitoringResult map(Monitor monitor) {

        MonitoringResult result = MonitoringResult.builder().httpStatusCode(monitor.getHttpStatusCode())
                .dateOfCheck(monitor.getDateOfLastCheck())
                .dateOfLastCreation(monitor.getDateOfLastCreation())
                .id(monitor.getId())
                .user(monitor.getUser().getId())
                .build();
        return result;
    }

    private Monitor map(MonitoringResult result){

        Optional<User> u = userRepository.findById(result.getUser());

        return Monitor.builder()
                .id(result.getId())
                .httpStatusCode(result.getHttpStatusCode())
                .dateOfLastCreation(result.getDateOfLastCreation())
                .user(u.get())
                .payload(result.getPayload())
                .build();
    }

    public ResponseEntity<List<MonitoringResult>> getAll(Pageable page){

        Page<Monitor> found = this.monitorRepository.findAll(page);

        if(found.isEmpty()) {

            return ResponseEntity.notFound().build();

        }

        List<MonitoringResult> result =  found.get().map(this::map).collect(Collectors.toList()

        );

        return ResponseEntity.of(Optional.of(result));

    }

    public ResponseEntity<Void> post(MonitoringResult result){

        Monitor monitor = this.map(result);

        this.monitorRepository.save(monitor);

        return ResponseEntity.ok().build();

    }

    public ResponseEntity<Void>  delete(long id){

        this.monitorRepository.deleteById(id);

        return ResponseEntity.ok().build();

    }

}
