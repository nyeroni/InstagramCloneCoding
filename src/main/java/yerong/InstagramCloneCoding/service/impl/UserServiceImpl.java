package yerong.InstagramCloneCoding.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.repository.UserRepository;
import yerong.InstagramCloneCoding.service.UserService;
import yerong.InstagramCloneCoding.web.dto.user.UserUpdateDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User update(Long id, UserUpdateDto userUpdateDto)
    {
        User user = userRepository.findById(id).orElseThrow();
        user.updateName(userUpdateDto.getName());
        String rawPassword = userUpdateDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.updatePassword(encPassword);
        user.updateBio(userUpdateDto.getBio());
        user.updateWebsite(userUpdateDto.getWebsite());
        user.updatePhone(userUpdateDto.getPhone());
        user.updateGender(userUpdateDto.getGender());
        log.info("userUpdateDto.gender" , userUpdateDto.getGender());

        return user;
    }
}
