package site.engineertest.qrcodeservice.controller;

import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.engineertest.qrcodeservice.base.MainFunctionality;

import java.io.IOException;

@Controller
public class MainController {

    private static final String IMAGE_PATH = "./src/main/resources/static/example.png";

    @GetMapping("/create")
    public String createNewQRCode(@RequestParam String param, Model model) {
        try {
            MainFunctionality.createQACode(param, 250, 250, IMAGE_PATH);
            model.addAttribute("portal", param);
        } catch (WriterException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return "index";
    }
}
