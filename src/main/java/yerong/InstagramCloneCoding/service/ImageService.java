package yerong.InstagramCloneCoding.service;

import yerong.InstagramCloneCoding.web.dto.image.ImageDto;
import yerong.InstagramCloneCoding.web.dto.image.ImageUploadDto;

import java.util.List;

public interface ImageService {
    List<ImageDto> imageStory(Long principalId);
    void imageUpload(Long userId, ImageUploadDto imageUploadDto);
}
