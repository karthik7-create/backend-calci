package com.example.calci.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private Long  id;

    @NotBlank(message = "this field must be filled")
    private double num1;
    @NotBlank(message = "this field must be filled")
    private double num2;
    private String operation;
    private double result;
    private LocalDateTime Timestamp;

}
