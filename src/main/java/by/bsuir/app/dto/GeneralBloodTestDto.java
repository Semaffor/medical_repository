package by.bsuir.app.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class GeneralBloodTestDto {

    @DecimalMin(value = "20.0")
    @DecimalMax(value = "0.001")
    private double erythrocytes;

    @DecimalMin(value = "20.0")
    @DecimalMax(value = "0.001")
    private double leukocytes;

    @DecimalMin(value = "20.0")
    @DecimalMax(value = "0.001")
    private double hemoglobinValue;

    private String username;
}
