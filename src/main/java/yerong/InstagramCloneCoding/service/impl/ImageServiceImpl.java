package yerong.InstagramCloneCoding.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.InstagramCloneCoding.domain.image.Image;
import yerong.InstagramCloneCoding.domain.likes.Likes;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationApiException;
import yerong.InstagramCloneCoding.repository.image.ImageRepository;
import yerong.InstagramCloneCoding.repository.user.UserRepository;
import yerong.InstagramCloneCoding.service.ImageService;
import yerong.InstagramCloneCoding.web.dto.image.ImageDto;
import yerong.InstagramCloneCoding.web.dto.image.ImageUploadDto;
import yerong.InstagramCloneCoding.web.dto.image.PopularImageDto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public Page<ImageDto> imageStory(Long principalId, Pageable pageable){
        Page<Image> images = imageRepository.mStory(principalId, pageable);
        return images.map(image -> {
            boolean likeState = false;
            for(Likes like : image.getLikes()) {
                if(like.getUser().getId() == principalId) {
                    likeState = true;
                    break;
                }
            }

            return ImageDto.builder()
                    .id(image.getId())
                    .caption(image.getCaption())
                    .postImageUrl(image.getPostImageUrl())
                    .userId(image.getUser().getId())
                    .username(image.getUser().getUsername())
                    .profileImageUrl(image.getUser().getProfileImageUrl())
                    .likeState(likeState)
                    .likeCount(image.getLikes().size())
                    .build();
        });
    }
    @Value("${file.path}")
    private String uploadFolder;

    @Override
    @Transactional
    public void imageUpload(Long userId, ImageUploadDto imageUploadDto){


        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
        try{
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 Id 입니다."));
        Image image = imageUploadDto.toEntity(imageFileName, user);

        Image savedImage = imageRepository.save(image);
        user.addImage(savedImage);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PopularImageDto> popularImage(){
        List<Image> images = imageRepository.mPopular();
        return images.stream().map(image -> {
            return PopularImageDto.builder()
                    .id(image.getId())
                    .caption(image.getCaption())
                    .postImageUrl(image.getPostImageUrl())
                    .userId(image.getUser().getId())
                    .username(image.getUser().getUsername())
                    .profileImageUrl(image.getUser().getProfileImageUrl())
                    .build();
        }).collect(Collectors.toList());

    }
}
