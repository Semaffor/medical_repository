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
    public ResponseEntity<?> addBioBloodTest(@RequestBody @Valid BiochemicalBloodTestDto dto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new HashSet<>(bindingResult.getAllErrors()), HttpStatus.NOT_FOUND);
        } else {
            try {
                biochemicalBloodTestService.save(dto);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (ServiceException e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @ResponseBody
    @PostMapping("/add/generalBlood/{username}")
    public ResponseEntity<?> addGeneralBloodTest(@RequestBody @Valid GeneralBloodTestDto dto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new HashSet<>(bindingResult.getAllErrors()), HttpStatus.NOT_FOUND);
        } else {
            try {
                generalBloodTestService.save(dto);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (ServiceException e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
}
