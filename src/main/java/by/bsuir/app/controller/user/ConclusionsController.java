package by.bsuir.app.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/conclusions")
public class ConclusionsController {
    @GetMapping("/")
    private String showConclusionsPage(Model model) {
        throw new UnsupportedOperationException();

    }
}
