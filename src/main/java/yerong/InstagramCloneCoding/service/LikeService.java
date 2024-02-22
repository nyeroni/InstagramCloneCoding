package yerong.InstagramCloneCoding.service;

public interface LikeService {

     void like(Long imageId, Long principalId);
     void unlike(Long imageId, Long principalId);
}
