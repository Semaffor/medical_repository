package by.bsuir.app.entity;

import by.bsuir.app.entity.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user"}, callSuper = true)
@Entity
public class UserCard extends BaseEntity {

   private static final long serialVersionUID = 1L;

   private String name;
   private String surname;
   private String thirdName;
   private Date birthday;
   private Integer mobile;

   @Enumerated(EnumType.ORDINAL)
   private Gender gender;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_id")
   private User user;
}
