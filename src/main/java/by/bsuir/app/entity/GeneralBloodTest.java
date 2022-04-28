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
@Entity
public class GeneralBloodTest extends Analyzes<GeneralBloodTest> {

    private static final long serialVersionUID = 1L;

    @NotNull
    private double erythrocytes;
    @Transient
    private String erythrocytesState;

    @NotNull
    private double leukocytes;
    @Transient
    private String leukocytesState;

    @NotNull
    private double hemoglobinValue;
    @Transient
    private String hemoglobinState;
}
