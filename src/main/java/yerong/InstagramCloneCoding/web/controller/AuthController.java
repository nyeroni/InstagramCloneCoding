package yerong.InstagramCloneCoding.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.service.AuthService;
import yerong.InstagramCloneCoding.web.dto.SignupDto;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "views/auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "views/auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
                log.info(error.getDefaultMessage());
            }
        }
        authService.join(signupDto);
        return "views/auth/signin";
    }
}
