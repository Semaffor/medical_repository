package by.bsuir.app.controller;

import by.bsuir.app.dto.UserRegistrationDto;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.exception.UserAlreadyExistsException;
import by.bsuir.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/auth")
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService,
                                  PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute UserRegistrationDto user, BindingResult bindingResult, Model model, HttpServletRequest request) {
        model.addAttribute("user", user);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            userService.registerNewUserAccount(user, passwordEncoder);
            return "redirect:/auth/logIn";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("alreadyExists", "true");
        } catch (ServiceException e) {
            model.addAttribute("serviceEx", "true");
        }
        return "redirect:/auth/registration";
    }
}