package site.engineertest.qrcodeservice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.engineertest.qrcodeservice.base.MainFunctionality;

@RestController
@RequestMapping("/api/v1")
public class MainController {
    private byte[] qrImage;

    @GetMapping("/create")
    public ResponseEntity<?> createNewQRCode(@RequestParam String code, Model model) {
        MainFunctionality mainFunctionality = new MainFunctionality();
        try {
            qrImage = mainFunctionality.getNewQRCode(code, 250, 250);
            model.addAttribute("portal", code);

            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
