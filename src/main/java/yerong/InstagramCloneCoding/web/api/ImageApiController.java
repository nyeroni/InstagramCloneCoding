package yerong.InstagramCloneCoding.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yerong.InstagramCloneCoding.config.auth.PrincipalDetails;
import yerong.InstagramCloneCoding.service.ImageService;
import yerong.InstagramCloneCoding.service.LikeService;
import yerong.InstagramCloneCoding.web.dto.CMRespDto;
import yerong.InstagramCloneCoding.web.dto.image.ImageDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;
    private final LikeService likeService;
    @GetMapping("/api/image")
    public ResponseEntity<CMRespDto<?>> imageStory(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PageableDefault(size = 3)Pageable pageable
            ){
        Page<ImageDto> imageDtos = imageService.imageStory(principalDetails.getUser().getId(), pageable);

        return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(1, "이미지 정보 가져오기 성공", imageDtos), HttpStatus.OK);
    }

    @PostMapping("/api/image/{imageId}/likes")
    public ResponseEntity<CMRespDto<?>> imageLikes(
            @PathVariable Long imageId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        likeService.like(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(1, "좋아요 성공", null), HttpStatus.CREATED);
    }
    @DeleteMapping("/api/image/{imageId}/likes")
    public ResponseEntity<CMRespDto<?>> imageUnLikes(
            @PathVariable Long imageId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        likeService.unlike(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(1, "좋아요 취소 성공", null), HttpStatus.OK);
    }
}
