package by.bsuir.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class RegistrationController {

    @GetMapping("/signUp")
    public String getRegPage() {
        return "registration";
    }
}
