package by.bsuir.app.entity;

import by.bsuir.app.entity.enums.State;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user"}, callSuper = true)
@Entity
public class BiochemicalBloodTest extends Analyzes<BiochemicalBloodTest> {

    private static final long serialVersionUID = 1L;

    @NotNull
    private double protein;
    @Transient
    private String proteinState;

    @NotNull
    private double glucose;
    @Transient
    private String glucoseState;

    @NotNull
    private double cholesterol;
    @Transient
    private String cholesterolState;
}
