package by.bsuir.app.entity;

import by.bsuir.app.entity.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"logInfos", "card"}, callSuper = false)
//@ToString(exclude = {"generalBloodTests", "biochemicalBloodTests"})
@Entity
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "b'0'")
    private boolean isBlocked;
    @Column(nullable = false, columnDefinition = "b'0'")
    private boolean isMonitored;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Enumerated(EnumType.ORDINAL)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
                    CascadeType.REFRESH})
    private List<BiochemicalBloodTest> biochemicalBloodTests = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
                    CascadeType.REFRESH})
    private List<BiochemicalBloodTest> generalBloodTests = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_card_id", referencedColumnName = "id")
    private UserCard card = new UserCard();

    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    Set<LogInfo> logInfos = new HashSet<>();

    public void addLogInfo(LogInfo logInfo) {
        logInfos.add(logInfo);
        logInfo.setUser(this);
    }
}