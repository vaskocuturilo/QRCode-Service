package site.engineertest.qrcodeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.engineertest.qrcodeservice.service.MainService;

@RestController
@RequestMapping("/api/v1")
public class MainController {
    MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/create")
    public ResponseEntity<?> create(@RequestParam String code, Model model) {
        try {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(mainService.createNewQRCode(code, model));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
