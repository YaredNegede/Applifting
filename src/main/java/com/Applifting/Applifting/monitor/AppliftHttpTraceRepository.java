package com.Applifting.Applifting.monitor;

import com.Applifting.Applifting.user.User;
import com.Applifting.Applifting.user.UserRepository;
import lombok.extern.java.Log;
import org.hibernate.criterion.Example;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log
@Component
public class AppliftHttpTraceRepository implements HttpTraceRepository {

    private final UserRepository userRepository;

    private final  MonitorRepository monitorRepository;

    public AppliftHttpTraceRepository(UserRepository userRepository, MonitorRepository monitorRepository) {
        this.userRepository = userRepository;
        this.monitorRepository = monitorRepository;
    }

    @Override
    public List<HttpTrace> findAll() {
        return null;
    }

    @Override
    public void add(HttpTrace trace) {

        Monitor monitor = null;

        try {

            User u = new User();

            u.setUsername(trace.getPrincipal().getName());

            org.springframework.data.domain.Example<User> eu = (org.springframework.data.domain.Example<User>) Example.create(u);

            Optional<User>  user = userRepository.findOne(eu);

            if(user.isPresent()) {

                monitor = Monitor.builder()
                        .httpStatusCode(trace.getResponse().getStatus())
                        .interval(trace.getTimeTaken())
                        .url(String.valueOf(trace.getRequest().getUri().toURL()))
                        .dateOfLastCheck(trace.getTimestamp().toEpochMilli())
//                        .payload(trace.getRequest().)
                        .name(trace.getPrincipal().getName())
                        .user(user.get())
                        .build();


                monitorRepository.save(monitor);


            }

            log.info(monitor.toString());

        } catch (Exception e) {

            log.info(e.getLocalizedMessage());

        }

    }
}
