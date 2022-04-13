package by.bsuir.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/management")
public class ManagementController {

    @GetMapping("/")
    private String showManagementPage(Model model) {
        throw new UnsupportedOperationException();
    }
}