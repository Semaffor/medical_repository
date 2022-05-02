package by.bsuir.app.controller;

import by.bsuir.app.dto.UserRegistrationDto;
import by.bsuir.app.exception.UserAlreadyExistsException;
import by.bsuir.app.service.CaptchaService;
import by.bsuir.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/auth")
public class RegistrationController {

    private final MessageSource messageSource;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CaptchaService captchaService;

    public RegistrationController(MessageSource messageSource, UserService userService,
                                  PasswordEncoder passwordEncoder, CaptchaService captchaService) {
        this.messageSource = messageSource;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.captchaService = captchaService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @ResponseBody
    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto user, HttpServletRequest request) {

        captchaService.verifyCaptcha(user.getGRecaptchaResponse());
        userService.registerNewUserAccount(user, passwordEncoder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/reset/password")
    private ResponseEntity<Map<String, String>> passwordRecovery(String email) {


    }
    @GetMapping("/activation/{code}")
    public String activateUser(Model model, @PathVariable String code) {
        boolean isUserActivated = userService.activateUser(code);

        if (isUserActivated) {
            model.addAttribute("activated", getInternationalizationMessage("user.activation.success"));
        } else {
            model.addAttribute("failCode", getInternationalizationMessage("user.activation.fail"));
        }

        return "login";
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> userAlreadyExistsHandler(UserAlreadyExistsException ex) {

        Map<String, String> map = new HashMap<>();
        if (ex.getMessage().contains("email")) {
            map.put("email", getInternationalizationMessage("reg.account.email.exists"));
        }
        if (ex.getMessage().contains("login")) {
            map.put("username", getInternationalizationMessage("reg.account.username.exists"));
        }

        return new ResponseEntity<>(map, HttpStatus.CONFLICT);
    }

    //TODO
    private String getInternationalizationMessage(String key) {
        return messageSource.getMessage(key, new Object[0], LocaleContextHolder.getLocale());
    }
}