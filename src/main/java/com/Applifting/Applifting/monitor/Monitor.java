package com.Applifting.Applifting.monitor;

import com.Applifting.Applifting.user.User;
import lombok.*;
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
@ToString
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

    private long dateOfLastCreation;

    private long  dateOfLastCheck;

    private String  payload;

}
