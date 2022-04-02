package by.bsuir.app.entity;

import lombok.*;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Analyzes<T extends Identifiable> extends BaseEntity implements Analyzable<T>{

    private static final long serialVersionUID = 1L;

    @OneToOne
    protected User attendingDoctor;
}
