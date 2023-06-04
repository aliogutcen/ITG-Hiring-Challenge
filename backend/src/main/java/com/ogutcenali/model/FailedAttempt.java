package com.ogutcenali.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tbl_failattempt")
public class FailedAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Integer userId;
    private int failedAttempts;
    private LocalDateTime lastFailedAttempt;



}
