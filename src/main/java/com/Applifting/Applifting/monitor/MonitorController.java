package com.Applifting.Applifting.monitor;

import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonitorController {

    private final MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @GetMapping("/monitor/{id}")
    public ResponseEntity<MonitoringResult> get(@PathVariable("id") long id){

        return monitorService.get(id);

    }

    @GetMapping("/monitor")
    public ResponseEntity<List<MonitoringResult>> getAll(Pageable page){

        return monitorService.getAll(page);

    }

    @PostMapping("/monitor")
    public ResponseEntity<Void> post(MonitoringResult result){

        return monitorService.post(result);

    }

    @DeleteMapping("/monitor{id}")
    public ResponseEntity<Void>  delete(@PathVariable("id")long id){

        return monitorService.delete(id);

    }

}
