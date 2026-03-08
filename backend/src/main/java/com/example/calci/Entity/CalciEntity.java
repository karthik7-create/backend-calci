package com.example.calci.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "calculations")
public class CalciEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double num1;
    private Double num2;
    private String operation;
    private double result;
    private LocalDateTime timestamp;

    @Column(columnDefinition = "TEXT")
    private String expression;

    private String calculationType;

}
