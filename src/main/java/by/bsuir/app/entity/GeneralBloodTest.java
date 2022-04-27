package by.bsuir.app.entity;

import by.bsuir.app.entity.enums.State;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GeneralBloodTest extends Analyzes<GeneralBloodTest> {

    private static final long serialVersionUID = 1L;

    private double erythrocytes;
    @Transient
    private String erythrocytesState;

    private double leukocytes;
    @Transient
    private String leukocytesState;

    private double hemoglobinValue;
    @Transient
    private String hemoglobinState;
}
