package by.bsuir.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);


    @GetMapping("/")
//    @PreAuthorize("hasRole('ADMIN')")
    public String mainPage(Model model) {
        return "home";
    }
}
