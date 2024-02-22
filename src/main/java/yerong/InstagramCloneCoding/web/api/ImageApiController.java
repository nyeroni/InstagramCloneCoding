package yerong.InstagramCloneCoding.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yerong.InstagramCloneCoding.config.auth.PrincipalDetails;
import yerong.InstagramCloneCoding.service.ImageService;
import yerong.InstagramCloneCoding.web.dto.CMRespDto;
import yerong.InstagramCloneCoding.web.dto.image.ImageDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;
    @GetMapping("/api/image")
    public ResponseEntity<CMRespDto<?>> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<ImageDto> imageDtos = imageService.imageStory(principalDetails.getUser().getId());

        return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(1, "이미지 정보 가져오기 성공", imageDtos), HttpStatus.OK);
    }
}
