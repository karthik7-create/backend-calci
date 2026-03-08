package com.example.calci.Service;

import com.example.calci.Dto.ExpressionRequest;
import com.example.calci.Dto.ExpressionResponse;
import com.example.calci.Entity.CalciEntity;
import com.example.calci.Enums.CalculationType;
import com.example.calci.Repository.CalciRepo;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExpressionService {

    @Autowired
    private CalciRepo calciRepo;

    /**
     * Evaluates a mathematical expression safely using exp4j.
     * Supports +, -, *, /, and parentheses with proper operator precedence.
     */
    public ExpressionResponse evaluateExpression(ExpressionRequest request) {

        String expressionStr = request.getExpression().trim();

        // Validate: only allow digits, operators, parentheses, dots, and spaces
        if (!expressionStr.matches("[0-9+\\-*/().\\s]+")) {
            throw new IllegalArgumentException(
                "Invalid expression. Only numbers, +, -, *, /, (, ), and . are allowed."
            );
        }

        try {
            Expression expression = new ExpressionBuilder(expressionStr).build();
            double result = expression.evaluate();

            // Save to history
            CalciEntity entity = new CalciEntity();
            entity.setNum1(0.0);
            entity.setNum2(0.0);
            entity.setOperation("EXPRESSION");
            entity.setExpression(expressionStr);
            entity.setResult(result);
            entity.setCalculationType(CalculationType.EXPRESSION.name());
            entity.setTimestamp(LocalDateTime.now());

            calciRepo.save(entity);

            return new ExpressionResponse(expressionStr, result);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to evaluate expression: " + e.getMessage());
        }
    }

}
