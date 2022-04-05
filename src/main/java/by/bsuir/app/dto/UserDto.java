package by.bsuir.app.dto;

import by.bsuir.app.validation.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@PasswordMatches
public class UserDto {
    @NotEmpty
    @NotBlank
    private String username;

    @NotEmpty
    @NotBlank
    private String password;
    private String matchingPassword;

    @NotEmpty
    @NotBlank
    private String email;

}
