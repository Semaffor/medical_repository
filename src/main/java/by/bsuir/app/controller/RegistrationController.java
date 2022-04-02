package by.bsuir.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("/signUp")
    public String getRegPage() {
        return "registration";
    }
}
