package com.example.calci.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponse {

    private String type;
    private String expression;
    private double result;
    private LocalDateTime timestamp;

}
