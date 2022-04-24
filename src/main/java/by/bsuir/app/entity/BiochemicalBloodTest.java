package by.bsuir.app.entity;

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
    private double glucose;
    private double cholesterol;
}
