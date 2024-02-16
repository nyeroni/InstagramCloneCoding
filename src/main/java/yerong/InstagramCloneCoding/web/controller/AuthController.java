package yerong.InstagramCloneCoding.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import yerong.InstagramCloneCoding.web.dto.SignupDto;

@Controller
@Slf4j
public class AuthController {

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "views/auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "views/auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto){

        return "views/auth/signin";
    }
}
