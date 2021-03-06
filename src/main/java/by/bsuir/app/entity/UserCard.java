package by.bsuir.app.entity;

import by.bsuir.app.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user"}, callSuper = true)
@Entity
@JsonIgnoreProperties(value = {"user"})
@ToString(exclude = {})
public class UserCard extends BaseEntity {

   private static final long serialVersionUID = 1L;

   private String name;
   private String surname;
   private String thirdName;

   @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
   private Date birthday;
   private String mobile;

   private String photoName;

   @Enumerated(EnumType.ORDINAL)
   private Gender gender;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_id")
   private User user;
}
