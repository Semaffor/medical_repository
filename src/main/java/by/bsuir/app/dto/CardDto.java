package by.bsuir.app.dto;

import by.bsuir.app.entity.User;
import by.bsuir.app.entity.UserCard;
import by.bsuir.app.entity.enums.Gender;
import by.bsuir.app.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private final static int MIN_STRING_LENGTH = 3;
    private final static int MAX_STRING_LENGTH = 28;

    private Long id;

    @NotBlank(message = "{dto.field.empty}")
    @Size(min = MIN_STRING_LENGTH, max = MAX_STRING_LENGTH, message = "{dto.card.length}")
    private String username;

    @NotBlank(message = "{dto.field.empty}")
    @Size(min = MIN_STRING_LENGTH, max = MAX_STRING_LENGTH, message = "{dto.card.length}")
    private String name;

    @NotBlank(message = "{dto.field.empty}")
    @Size(min = MIN_STRING_LENGTH, max = MAX_STRING_LENGTH, message = "{dto.card.length}")
    private String surname;

    @NotBlank(message = "{dto.field.empty}")
    @Size(min = MIN_STRING_LENGTH, max = MAX_STRING_LENGTH, message = "{dto.card.length}")
    private String thirdName;

    @NotBlank(message = "{dto.field.empty}")
    @Email(regexp = "[\\S._%+-]+@[\\S.-]+\\.[\\S]{2,}", message = "{dto.card.email.pattern}")
    private String email;

    @NotNull(message = "{dto.field.empty}")
    @Pattern(regexp = "^(\\d{2})-(\\d{7})", message = "+375 - XX-XXXXXXX")
    private String mobile;

    @NotNull(message = "{dto.field.empty}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "{dto.card.date.past}")
    private Date birthday;

    @NotNull(message = "{dto.card.sex.empty}")
    private Gender gender;

    private String role;

    private String avatarFileName;

    public static CardDto fromUser(User user, String lang) {
        CardDto cardDto = CardDto.builder().build();

        cardDto.setId(user.getId());
        cardDto.setUsername(user.getUsername());
        cardDto.setEmail(user.getEmail());
        UserCard card = user.getCard();
        if (card != null) {
            cardDto.setName(card.getName());
            cardDto.setSurname(card.getSurname());
            cardDto.setThirdName(card.getThirdName());
            cardDto.setMobile(card.getMobile());
            cardDto.setBirthday(card.getBirthday());
            cardDto.setGender(card.getGender());
            Role firstRole = getFirstRole(user.getRoles());
            if (lang.equals("ru")) {
                cardDto.setRole(firstRole.getRu());
            } else {
                cardDto.setRole(firstRole.getRu());
            }
            cardDto.setAvatarFileName(user.getCard().getPhotoName());
        }
       return cardDto;
    }

    private static Role getFirstRole(Set<Role> roles) {
        ArrayList<Role> roleArrayList = new ArrayList<>(roles);
        return roleArrayList.get(0);
    }
}
