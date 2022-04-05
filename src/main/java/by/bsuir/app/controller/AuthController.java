package by.bsuir.app.controller;

import by.bsuir.app.dto.UserDto;
import by.bsuir.app.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/logIn")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @GetMapping("/registration")
    public String getSuccessPage() {
        return "registration";
    }

    @PostMapping("/")
    public String authenticate(@Valid @ModelAttribute("user") User user,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "error";
        }
//        model.addAttribute("name", employee.getName());
//        model.addAttribute("contactNumber", employee.getContactNumber());
//        model.addAttribute("id", employee.getId());
        return "employeeView";
    }
}
