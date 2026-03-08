package com.example.calci.Controller;

import com.example.calci.Dto.CalculationRequest;
import com.example.calci.Dto.ExpressionRequest;
import com.example.calci.Dto.ExpressionResponse;
import com.example.calci.Dto.HistoryResponse;
import com.example.calci.Service.CalculatorService;
import com.example.calci.Service.ExpressionService;
import com.example.calci.Repository.CalciRepo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private ExpressionService expressionService;

    @Autowired
    private CalciRepo calciRepo;

    // ─── Existing endpoint (unchanged contract) ─────────────────────────

    @PostMapping("/calculate")
    public double calculate(@RequestBody CalculationRequest request) {

        return calculatorService.calculate(request);

    }

    // ─── New endpoint: expression evaluation ────────────────────────────

    @PostMapping("/evaluate-expression")
    public ExpressionResponse evaluateExpression(@Valid @RequestBody ExpressionRequest request) {

        return expressionService.evaluateExpression(request);

    }

    // ─── Updated history: unified response for both types ───────────────

    @GetMapping("/history")
    public List<HistoryResponse> getHistory() {

        return calciRepo.findAll().stream()
                .map(entity -> {
                    String type = entity.getCalculationType() != null
                            ? entity.getCalculationType()
                            : "SIMPLE";

                    String expression;
                    if (entity.getExpression() != null) {
                        expression = entity.getExpression();
                    } else if (entity.getNum1() != null && entity.getNum2() != null) {
                        expression = entity.getNum1() + " " + entity.getOperation() + " " + entity.getNum2();
                    } else {
                        expression = "Unknown";
                    }

                    return new HistoryResponse(type, expression, entity.getResult(), entity.getTimestamp());
                })
                .collect(Collectors.toList());

    }
}