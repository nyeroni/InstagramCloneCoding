package yerong.InstagramCloneCoding.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.InstagramCloneCoding.domain.image.Image;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationApiException;
import yerong.InstagramCloneCoding.repository.image.ImageRepository;
import yerong.InstagramCloneCoding.repository.user.UserRepository;
import yerong.InstagramCloneCoding.service.ImageService;
import yerong.InstagramCloneCoding.web.dto.image.ImageUploadDto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

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
}