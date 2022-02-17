package site.engineertest.qrcodeservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import site.engineertest.qrcodeservice.base.MainFunctionality;

@Service
public class MainService {

    private byte[] qrImage;

    public Object createNewQRCode(String code, Model model) {
        MainFunctionality mainFunctionality = new MainFunctionality();
        try {
            qrImage = mainFunctionality.getNewQRCode(code, 250, 250);
            model.addAttribute("portal", code);

            return qrImage;

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
