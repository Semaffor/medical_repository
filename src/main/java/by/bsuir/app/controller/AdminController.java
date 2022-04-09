package by.bsuir.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/management")
    private String showManagementPage(Model model) {

    }

    @GetMapping("/reports")
    private String showReportsPage(Model model) {

    }

    @GetMapping("/messages")
    private String showMessagesPage(Model model) {

    }
}
