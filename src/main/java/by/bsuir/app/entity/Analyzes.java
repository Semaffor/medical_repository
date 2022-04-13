package by.bsuir.app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Analyzes<T extends Identifiable> extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @OneToOne
    protected User attendingDoctor;

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    protected User user;

    protected Date createdOn;
    protected String result;
}
