package com.example.calci.Service;

import com.example.calci.Dto.CalculationRequest;
import com.example.calci.Entity.CalciEntity;
import com.example.calci.Enums.OperationType;
import com.example.calci.Repository.CalciRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CalculatorService {

    @Autowired
    private CalciRepo calciRepo;

    public double calculate(CalculationRequest request) {

        double num1 = request.getNum1();
        double num2 = request.getNum2();
        OperationType operation = request.getOperation();

        double result = 0;

        switch (operation) {

            case ADD:
                result = num1 + num2;
                break;

            case SUBTRACT:
                result = num1 - num2;
                break;

            case MULTIPLY:
                result = num1 * num2;
                break;

            case DIVIDE:
                if (num2 == 0) {
                    throw new RuntimeException("Division by zero not allowed");
                }
                result = num1 / num2;
                break;
        }

        CalciEntity entity = new CalciEntity();

        entity.setNum1(num1);
        entity.setNum2(num2);
        entity.setOperation(operation.toString());
        entity.setResult(result);
        entity.setTimestamp(LocalDateTime.now());

        calciRepo.save(entity);

        return result;
    }
}