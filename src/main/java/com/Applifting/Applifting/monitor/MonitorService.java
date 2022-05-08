package com.Applifting.Applifting.monitor;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonitorService {

    public ModelMapper mapper;

    private final MonitorRepository monitorRepository;

    public MonitorService(MonitorRepository monitorRepository,ModelMapper mapper ) {
        this.monitorRepository = monitorRepository;
        this.mapper=mapper;
    }

    public ResponseEntity<MonitoringResult> get(long id){

        Optional<Monitor> found = this.monitorRepository.findById(id);

        if(found.isEmpty())
            return ResponseEntity.notFound().build();

        MonitoringResult result = this.mapper.map(found.get(), MonitoringResult.class);

        return ResponseEntity.of(Optional.of(result));

    }

    public ResponseEntity<List<MonitoringResult>> getAll(Pageable page){

        Page<Monitor> found = this.monitorRepository.findAll(page);

        if(found.isEmpty()) {

            return ResponseEntity.notFound().build();

        }

        List<MonitoringResult> result =  found.get().map(monitor -> this.mapper.map(monitor,MonitoringResult.class)).collect(Collectors.toList());

        return ResponseEntity.of(Optional.of(result));

    }

    public ResponseEntity<Void> post(MonitoringResult result){

        Monitor monitor = this.mapper.map(result,Monitor.class);

        this.monitorRepository.save(monitor);

        return ResponseEntity.ok().build();

    }

    public ResponseEntity<Void>  delete(long id){

        this.monitorRepository.deleteById(id);

        return ResponseEntity.ok().build();

    }

}
