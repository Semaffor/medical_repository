package by.bsuir.app.dto;

import by.bsuir.app.entity.User;
import by.bsuir.app.entity.UserCard;
import by.bsuir.app.entity.enums.Gender;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class CardDto {

    private final static int MIN_STRING_LENGTH = 3;
    private final static int MAX_STRING_LENGTH = 28;
    private Long id;

    @NotBlank()
//    @Size(min = MIN_STRING_LENGTH, max = MAX_STRING_LENGTH, message = "Min length")
    private String username;

    @NotBlank()
    @Size(min = MIN_STRING_LENGTH, max = MAX_STRING_LENGTH)
    private String name;

    @NotBlank
    @Size(min = MIN_STRING_LENGTH, max = MAX_STRING_LENGTH)
    private String surname;

    @NotBlank
    @Size(min = MIN_STRING_LENGTH, max = MAX_STRING_LENGTH)
    private String thirdName;

    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "^(\\d{9})", message = "+375 - XXXXXXXXX")
    private String mobile;

    //TODO DateValidator
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull
    private Gender gender;

    public static CardDto fromUser(User user) {
        CardDto cardDto = new CardDto();

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
        }
       return cardDto;
    }
}
