package yerong.InstagramCloneCoding.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.InstagramCloneCoding.domain.subs.Subscribe;
import yerong.InstagramCloneCoding.handler.exception.CustomApiException;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationApiException;
import yerong.InstagramCloneCoding.repository.subs.SubscribeRepository;
import yerong.InstagramCloneCoding.service.SubscribeService;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscribeRepository subscribeRepository;
    @Override
    @Transactional
    public void subscribe(Long fromUserId, Long toUSerId){
        try {
            subscribeRepository.mSubscribe(fromUserId, toUSerId);
        }catch (Exception e){
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }
    @Override
    @Transactional
    public void unSubscribe(Long fromUserId, Long toUserId){
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
