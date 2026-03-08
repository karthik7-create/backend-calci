package com.example.calci.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ExpressionRequest {

    @NotBlank(message = "Expression must not be blank")
    private String expression;

}
