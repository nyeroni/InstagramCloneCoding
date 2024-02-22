package yerong.InstagramCloneCoding.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import yerong.InstagramCloneCoding.web.dto.image.ImageDto;
import yerong.InstagramCloneCoding.web.dto.image.ImageUploadDto;

import java.util.List;

public interface ImageService {
    Page<ImageDto> imageStory(Long principalId, Pageable pageable);
    void imageUpload(Long userId, ImageUploadDto imageUploadDto);
}
