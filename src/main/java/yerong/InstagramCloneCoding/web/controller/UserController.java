package yerong.InstagramCloneCoding.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yerong.InstagramCloneCoding.config.auth.PrincipalDetails;

@Controller
public class UserController {
    @GetMapping("/user/{id}")
    public String profile(@PathVariable("id") Long id){

        return "views/user/profile";
    }
    @GetMapping("/user/{id}/update")
    public String update(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        // principalDetails.getUser() 로 편리하게 세션 사용
        /**
         * 어려운 방법 (세션 정보 직접 찾기)
         * Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         * PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
         * principal.getUser();
         */

        return "views/user/update";
    }
}
