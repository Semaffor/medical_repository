package by.bsuir.app.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class GeneralBloodTest extends Analyzes<GeneralBloodTest> {

    private static final long serialVersionUID = 1L;

    private double erythrocytes;
    private double leukocytes;
    private double hemoglobinValue;
}
