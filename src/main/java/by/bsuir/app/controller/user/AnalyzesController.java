package by.bsuir.app.controller.user;

import by.bsuir.app.dto.BiochemicalBloodTestDto;
import by.bsuir.app.dto.GeneralBloodTestDto;
import by.bsuir.app.entity.BiochemicalBloodTest;
import by.bsuir.app.entity.GeneralBloodTest;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.BiochemicalBloodTestService;
import by.bsuir.app.service.GeneralBloodTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;

@Slf4j
@Controller
@RequestMapping("/user/analyzes")
public class AnalyzesController {

    private final BiochemicalBloodTestService biochemicalBloodTestService;
    private final GeneralBloodTestService generalBloodTestService;

    public AnalyzesController(BiochemicalBloodTestService biochemicalBloodTestService,
                              GeneralBloodTestService generalBloodTestService) {
        this.biochemicalBloodTestService = biochemicalBloodTestService;
        this.generalBloodTestService = generalBloodTestService;
    }

    @GetMapping("")
    private String showAnalyzesPage(Principal user, Model model) {
        model.addAttribute("username", user.getName());
        model.addAttribute("bio_blood", new BiochemicalBloodTestDto());
        model.addAttribute("general_blood", new GeneralBloodTestDto());
        return "user/analyzes";
    }

    @ResponseBody
    @PostMapping("/add/bioBlood/{username}")
    public ResponseEntity<BiochemicalBloodTest> addBioBloodTest(@RequestBody @Valid BiochemicalBloodTestDto dto) {
        try {
            return new ResponseEntity<>(biochemicalBloodTestService.save(dto), HttpStatus.CREATED);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping("/add/generalBlood/{username}")
    public ResponseEntity<GeneralBloodTest> addGeneralBloodTest(@RequestBody @Valid GeneralBloodTestDto dto) {
        try {
            return new ResponseEntity<>(generalBloodTestService.save(dto), HttpStatus.CREATED);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @ResponseBody
//    @PostMapping("/")
}
