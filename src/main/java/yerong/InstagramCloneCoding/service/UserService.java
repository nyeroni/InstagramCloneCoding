package yerong.InstagramCloneCoding.service;

import org.springframework.web.multipart.MultipartFile;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.web.dto.user.UserProfileDto;
import yerong.InstagramCloneCoding.web.dto.user.UserUpdateDto;

public interface UserService {
    User findById(Long id);
    User update(Long id, UserUpdateDto userUpdateDto);
    UserProfileDto profile(Long pageUserId, Long pageOwnerId);
    User changeProfileImage(Long principalId, MultipartFile profileImageFile);
}
