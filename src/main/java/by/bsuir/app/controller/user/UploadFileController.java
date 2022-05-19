package by.bsuir.app.controller.user;

import by.bsuir.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadFileController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${upload.path}")
    private String uploadPath;

    private final UserService userService;

    public UploadFileController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/user/personal/edit/photo/{username}")
    public ResponseEntity<?> uploadAvatar(@PathVariable("username") String username,
                                          @RequestParam("photo") MultipartFile file) {
        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String photoName = uuidFile + "." + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + photoName));
                userService.uploadAvatar(username, photoName);
                return new ResponseEntity<>(photoName, HttpStatus.OK);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
