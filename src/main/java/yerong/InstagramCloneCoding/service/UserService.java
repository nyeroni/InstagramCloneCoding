package yerong.InstagramCloneCoding.service;

import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.web.dto.user.UserUpdateDto;

public interface UserService {
    User findById(Long id);
    User update(Long id, UserUpdateDto userUpdateDto);
    User profile(Long userId);
}
