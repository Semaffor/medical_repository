package by.bsuir.app.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Data
public class BiochemicalBloodTestDto {

    @DecimalMin(value = "0.01", message = "{smth}")
    @DecimalMax(value = "20.0")
    private double protein;
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "20.0")
    private double glucose;
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "20.0")
    private double cholesterol;

    private String username;
}
