package com.Applifting.Applifting.monitor;

import com.Applifting.Applifting.user.User;
import com.Applifting.Applifting.user.UserRepository;
import lombok.extern.java.Log;
import org.hibernate.criterion.Example;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Log
@Component
public class AppliftHttpTraceRepository implements HttpTraceRepository {

    public static String header = "accessToken";
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

            String hdr = String.valueOf(trace.getRequest().getHeaders().get(header));

            Optional<User>  user = userRepository.findByAccessToken(hdr);

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


                log.info(monitor.toString());

                monitorRepository.save(monitor);

            }

        } catch (Exception e) {

            log.info(e.getLocalizedMessage());

        }

    }
}
