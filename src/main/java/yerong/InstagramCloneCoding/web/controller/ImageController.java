package yerong.InstagramCloneCoding.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import yerong.InstagramCloneCoding.config.auth.PrincipalDetails;
import yerong.InstagramCloneCoding.domain.image.Image;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationException;
import yerong.InstagramCloneCoding.service.ImageService;
import yerong.InstagramCloneCoding.web.dto.image.ImageUploadDto;
import yerong.InstagramCloneCoding.web.dto.image.PopularImageDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;
    @GetMapping({"/", "/image/story"})
    public String story(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        model.addAttribute("principal", principalDetails.getUser());
        return "views/image/story";
    }
    @GetMapping("/image/popular")
    public String popular(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            Model model
    ){
        List<PopularImageDto> popularImageDtos = imageService.popularImage();
        model.addAttribute("popularDtos", popularImageDtos);
        model.addAttribute("principal", principalDetails.getUser());

        return "views/image/popular";
    }
    @GetMapping("/image/upload")
    public String upload(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            Model model
    ){
        model.addAttribute("principal", principalDetails.getUser());
        return "views/image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(
            ImageUploadDto imageUploadDto,
            @AuthenticationPrincipal PrincipalDetails principalDetails
            ){

        if(imageUploadDto.getFile().isEmpty()){
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }
        imageService.imageUpload(principalDetails.getUser().getId(), imageUploadDto);
        return "redirect:/user/" + principalDetails.getUser().getId();
    }
}
