package by.bsuir.app.controller.doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor/patients")
public class PatientsController {
    @GetMapping("/")
    private String showPatientsPage(Model model) {
        throw new UnsupportedOperationException();

    }
}
