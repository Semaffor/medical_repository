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
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class BiochemicalBloodTest extends Analyzes<BiochemicalBloodTest> {

    private static final long serialVersionUID = 1L;

    @Override
    public Optional<BiochemicalBloodTest> analize() {
        return Optional.empty();
    }
}
