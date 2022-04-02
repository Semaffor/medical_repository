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
@EqualsAndHashCode(callSuper = true)
@Entity
public class GeneralBloodTest extends Analyzes<GeneralBloodTest> {

    private static final long serialVersionUID = 1L;

    @Override
    public Optional<GeneralBloodTest> analize() {
        return Optional.empty();
    }
}
