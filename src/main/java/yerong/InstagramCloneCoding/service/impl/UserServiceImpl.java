package yerong.InstagramCloneCoding.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationApiException;
import yerong.InstagramCloneCoding.repository.user.UserRepository;
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
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() ->  new CustomValidationApiException("찾을 수 없는 Id 입니다."));
    }
    @Override
    @Transactional
    public User update(Long id, UserUpdateDto userUpdateDto)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 Id 입니다."));

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
