package yerong.InstagramCloneCoding.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import yerong.InstagramCloneCoding.config.auth.PrincipalDetails;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.service.UserService;
import yerong.InstagramCloneCoding.service.impl.UserServiceImpl;
import yerong.InstagramCloneCoding.web.dto.CMRespDto;
import yerong.InstagramCloneCoding.web.dto.user.UserUpdateDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable("id")Long id, UserUpdateDto userUpdateDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User userEntity = userService.update(id, userUpdateDto);
        principalDetails.setUser(userEntity);
        return new CMRespDto<>(1, "회원 수정 완료", userEntity);
    }
}
