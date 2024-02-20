package yerong.InstagramCloneCoding.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import yerong.InstagramCloneCoding.config.auth.PrincipalDetails;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.service.UserService;
import yerong.InstagramCloneCoding.web.dto.user.UserProfileDto;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/user/{pageUserId}")
    public String profile(
            @PathVariable("pageUserId") Long pageUserId,
                          Model model,
            @AuthenticationPrincipal PrincipalDetails principalDetails){
        UserProfileDto userDto = userService.profile(pageUserId, principalDetails.getUser().getId());

        model.addAttribute("userDto", userDto);
        return "views/user/profile";
    }
    @GetMapping("/user/{id}/update")
    public String update(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        // principalDetails.getUser() 로 편리하게 세션 사용
        /**
         * 어려운 방법 (세션 정보 직접 찾기)
         * Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         * PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
         * principal.getUser();
         */
        model.addAttribute("principal", principalDetails.getUser());
        return "views/user/update";
    }

}
