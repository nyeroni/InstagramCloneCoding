package yerong.InstagramCloneCoding.service;

import yerong.InstagramCloneCoding.web.dto.subscribe.SubscribeDto;

import java.util.List;

public interface SubscribeService {
    void subscribe(Long fromUserId, Long toUserId);
    void unSubscribe(Long fromUserId, Long toUserId);
    List<SubscribeDto> getSubscribeList(Long pageUserId, Long pageOwnerId);
}
