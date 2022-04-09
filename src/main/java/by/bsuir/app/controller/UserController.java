package by.bsuir.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/personal")
    private String showPersonalPage(Model model) {

    }

    @GetMapping("/analyzes")
    private String showAnalyzesPage(Model model) {

    }
    @GetMapping("/conclusions")
    private String showConclusionsPage(Model model) {

    }
    @GetMapping("/statistics")
    private String showStatisticsPage(Model model) {

    }
}
