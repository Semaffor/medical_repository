package by.bsuir.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class DoctorController {
    @GetMapping("/patients")
    private String showPatientsPage(Model model) {

    }

    @GetMapping("/analytics")
    private String showAnalyticsPage(Model model) {

    }
}
