package by.bsuir.app.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class GeneralBloodTestDto {

    private final static String MIN = "0.01";
    private final static String MAX = "200.0";

    @DecimalMin(value = MIN, message = "{dto.test.value.min}")
    @DecimalMax(value = MAX, message = "{dto.test.value.max}")
    private double erythrocytes;

    @DecimalMin(value = MIN, message = "{dto.test.value.min}")
    @DecimalMax(value = MAX, message = "{dto.test.value.max}")
    private double leukocytes;

    @DecimalMin(value = MIN, message = "{dto.test.value.min}")
    @DecimalMax(value = MAX, message = "{dto.test.value.max}")
    private double hemoglobinValue;

    private String username;
}
