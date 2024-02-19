package yerong.InstagramCloneCoding.web.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import yerong.InstagramCloneCoding.config.auth.PrincipalDetails;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationApiException;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationException;
import yerong.InstagramCloneCoding.service.UserService;
import yerong.InstagramCloneCoding.service.impl.UserServiceImpl;
import yerong.InstagramCloneCoding.web.dto.CMRespDto;
import yerong.InstagramCloneCoding.web.dto.user.UserUpdateDto;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public ResponseEntity<CMRespDto<?>> update(
            @PathVariable("id")Long id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetails){
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패", errorMap);
        }
        else{
            User userEntity = userService.update(id, userUpdateDto);
            principalDetails.setUser(userEntity);
            return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(1, "회원 수정 완료", userEntity), HttpStatus.OK);
        }
    }
}
