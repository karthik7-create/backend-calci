package com.example.calci.Dto;

import com.example.calci.Enums.OperationType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class CalculationRequest {

    private double num1;
    private double num2;
    private OperationType operation;

}