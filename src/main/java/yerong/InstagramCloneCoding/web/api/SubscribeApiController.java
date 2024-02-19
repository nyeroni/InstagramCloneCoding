package yerong.InstagramCloneCoding.web.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import yerong.InstagramCloneCoding.config.auth.PrincipalDetails;
import yerong.InstagramCloneCoding.service.SubscribeService;
import yerong.InstagramCloneCoding.web.dto.CMRespDto;

@RestController
@RequiredArgsConstructor
public class SubscribeApiController {

    private final SubscribeService subscribeService;
    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<CMRespDto<?>> subscribe(
            @PathVariable("toUserId") Long toUserId,
            @AuthenticationPrincipal PrincipalDetails principalDetails){

        subscribeService.subscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(1, "구독 성공", null), HttpStatus.OK);
    }
    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<CMRespDto<?>> unSubscribe(@PathVariable("toUserId") Long toUserId,
                                                   @AuthenticationPrincipal PrincipalDetails principalDetails){
        subscribeService.unSubscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(1, "구독 취소 성공", null), HttpStatus.OK);
    }
}
