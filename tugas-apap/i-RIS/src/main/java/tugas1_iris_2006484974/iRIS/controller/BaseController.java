package tugas1_iris_2006484974.iRIS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
    private String LandingPage() {
        return "landing-page";
    }
}

