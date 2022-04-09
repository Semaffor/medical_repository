package by.bsuir.app.dto;

import by.bsuir.app.validation.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@PasswordMatches
public class UserDto {
    @NotEmpty(message = "1")
    @NotBlank(message = "1")
    private String username;

    @NotEmpty(message = "1")
    @NotBlank(message = "1")
    private String password;
    private String matchingPassword;

    @Email(regexp = ".+[@].+[\\.].+")
    @NotEmpty(message = "1")
    @NotBlank(message = "1")
    private String email;

    @AssertTrue(message = "1")
    boolean confirmation;
}
