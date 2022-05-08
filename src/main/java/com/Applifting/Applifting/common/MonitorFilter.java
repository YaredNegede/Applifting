package com.Applifting.Applifting.common;

import com.Applifting.Applifting.monitor.MonitorRepository;
import com.Applifting.Applifting.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

@Log4j2
@Component
@Order(1)
public class MonitorFilter implements HandlerInterceptor {

    @Autowired
    MonitorRepository repository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object object,
                                Exception arg3) throws IOException {

        log.info("Request is complete");


        HttpServletResponseCopier responseCopier = new HttpServletResponseCopier(response);

        log.info(String.valueOf(responseCopier.getCopy()));

        log.info("Handler execution is complete");

    }

}
