package by.bsuir.app.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/analyzes")
public class AnalyzesController {
    @GetMapping("/")
    private String showAnalyzesPage(Model model) {

    }
}
