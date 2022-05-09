package com.Applifting.Applifting.monitor;

import com.Applifting.Applifting.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Monitor {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 2, max = 14)
    private String name;

    @NotNull
    @Size(min = 2, max = 14)
    private String url;

    @NotNull
    @Size(min = 2, max = 14)
    private long interval;

    @NotNull
    @Size(min = 2, max = 14)
    private int httpStatusCode;

    @NotNull
    @ManyToOne
    private User user;

    @CreationTimestamp
    private LocalDateTime dateOfLastCreation;

    @CreationTimestamp
    private LocalDateTime  dateOfLastCheck;

    private String  payload;

}
