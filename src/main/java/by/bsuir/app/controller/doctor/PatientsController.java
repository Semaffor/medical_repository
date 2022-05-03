package by.bsuir.app.controller.doctor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/doctor/patients")
public class PatientsController {


    @GetMapping("")
    public String showPatientsPage(Model model) {
        return "doctor/patients-analyzes";
    }

    @ResponseBody
    @PutMapping("comment/bioBlood/{username}")
    public ResponseEntity<?> setRecommendationBio(@PathVariable String username, @RequestBody Map<String, String> params) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("comment/generalBlood/{username}")
    public ResponseEntity<?> setRecommendationGeneral(@PathVariable String username, @RequestBody Map<String, String> params) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
