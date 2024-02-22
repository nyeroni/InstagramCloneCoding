package yerong.InstagramCloneCoding.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.InstagramCloneCoding.domain.image.Image;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.handler.exception.CustomException;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationApiException;
import yerong.InstagramCloneCoding.repository.image.ImageRepository;
import yerong.InstagramCloneCoding.repository.likes.LikesRepository;
import yerong.InstagramCloneCoding.repository.user.UserRepository;
import yerong.InstagramCloneCoding.service.LikeService;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikesRepository likesRepository;

    @Transactional
    @Override
    public void like(Long imageId, Long principalId){
        likesRepository.mLikes(imageId, principalId);
    }
    @Transactional
    @Override
    public void unlike(Long imageId, Long principalId){
        likesRepository.mUnLikes(imageId, principalId);
    }

}
