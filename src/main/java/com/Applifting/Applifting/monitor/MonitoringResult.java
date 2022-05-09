package com.Applifting.Applifting.monitor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitoringResult {

    private long id;

    private long dateOfCheck;

    private int httpStatusCode;

    private String payload;

    private String monitoredEndPointIdl;

    private long dateOfLastCreation;

    private long user;


}
