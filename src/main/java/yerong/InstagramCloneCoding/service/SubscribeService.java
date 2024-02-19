package yerong.InstagramCloneCoding.service;

public interface SubscribeService {
    void subscribe(Long fromUserId, Long toUserId);
    void unSubscribe(Long fromUserId, Long toUserId);
}
