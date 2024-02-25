package yerong.InstagramCloneCoding.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.handler.exception.CustomException;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationApiException;
import yerong.InstagramCloneCoding.repository.subs.SubscribeRepository;
import yerong.InstagramCloneCoding.repository.user.UserRepository;
import yerong.InstagramCloneCoding.service.UserService;
import yerong.InstagramCloneCoding.web.dto.user.UserProfileDto;
import yerong.InstagramCloneCoding.web.dto.user.UserUpdateDto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
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
    @Override
    @Transactional
    public UserProfileDto profile(Long pageUserId, Long pageOwnerId){

        UserProfileDto dto = new UserProfileDto();
        User pageUser = userRepository.findById(pageUserId).orElseThrow(() -> new CustomException("해당 프로필 페이지는 없는 페이지입니다."));

        dto.setUser(pageUser);
        dto.setImageCount(pageUser.getImages().size());
        dto.setPageOwnerState(pageOwnerId.equals(pageUserId));
        int state = subscribeRepository.mSubscribeState(pageOwnerId, pageUserId);
        int count = subscribeRepository.mSubscribeCount(pageUserId);

        dto.setSubscribeState(state==1);
        dto.setSubscribeCount(count);
        pageUser.getImages().forEach((image)->{
            image.setLikeCount(image.getLikes().size());
        });
        return dto;
    }

    @Value("${file.path}")
    private String uploadFolder;

    @Override
    @Transactional
    public User changeProfileImage(Long principalId, MultipartFile profileImageFile){
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
        try{
            Files.write(imageFilePath, profileImageFile.getBytes());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        User user = userRepository.findById(principalId).orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 Id 입니다."));
        user.setProfileImageUrl(imageFileName);

        return user;
    }


}
