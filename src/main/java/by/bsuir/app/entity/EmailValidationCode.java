package by.bsuir.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class EmailValidationCode extends BaseEntity {

    @NonNull
    private String validationCode;

    @OneToOne(orphanRemoval = true)
    private User user;
}
