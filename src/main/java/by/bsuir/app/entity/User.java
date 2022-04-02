package by.bsuir.app.entity;

import by.bsuir.app.entity.BaseEntity;
import by.bsuir.app.entity.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String email;

    @Column(nullable = false, columnDefinition = "b'0'")
    private boolean isBlocked;
    private boolean isMonitored;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Enumerated(EnumType.ORDINAL)
    private Set<Role> roles = new HashSet<>();

}
