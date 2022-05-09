package com.Applifting.Applifting.common;

import com.Applifting.Applifting.monitor.MonitorRepository;
import com.Applifting.Applifting.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
@Order(1)
public class MonitorFilter implements HandlerInterceptor {

    @Autowired
    MonitorRepository repository;

    @Autowired
    UserRepository userRepository;

    ObjectMapper mapper = new ObjectMapper();


    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object object,
                                Exception arg3) throws IOException {

        log.info("************Request is complete************");


        log.info(object);

        log.info("************Handler execution is complete************");

    }

}
