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
@EqualsAndHashCode(exclude = {"user"}, callSuper = true)
@Entity
public class BiochemicalBloodTest extends Analyzes<BiochemicalBloodTest> {

    private static final long serialVersionUID = 1L;

    private double protein;
    @Transient
    private State proteinState;

    private double glucose;
    @Transient
    private State glucoseState;

    private double cholesterol;
    @Transient
    private State cholesterolState;
}
