package yerong.InstagramCloneCoding.service;

import yerong.InstagramCloneCoding.web.dto.image.ImageUploadDto;

public interface ImageService {
    void imageUpload(Long userId, ImageUploadDto imageUploadDto);
}
