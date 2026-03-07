package com.example.calci.Controller;

import com.example.calci.Dto.CalculationRequest;
import com.example.calci.Entity.CalciEntity;
import com.example.calci.Service.CalculatorService;
import com.example.calci.Repository.CalciRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private CalciRepo calciRepo;

    @PostMapping("/calculate")
    public double calculate(@RequestBody CalculationRequest request) {

        return calculatorService.calculate(request);

    }

    @GetMapping("/history")
    public List<CalciEntity> getHistory() {

        return calciRepo.findAll();

    }
}