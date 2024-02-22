package yerong.InstagramCloneCoding.repository.subs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yerong.InstagramCloneCoding.domain.subscribe.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    @Modifying
    @Query(value = "INSERT INTO subscribe(from_user_id, to_user_id, createdAt) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    int mSubscribe(Long fromUserId, Long toUserId);

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE from_user_id = :fromUserId and to_user_id = :toUserId", nativeQuery = true)
    int mUnSubscribe(Long fromUserId, Long toUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE from_user_id = :pageOwnerId AND to_user_id = :pageUserId", nativeQuery = true)
    int mSubscribeState(Long pageOwnerId, Long pageUserId);
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE from_user_id = :pageUserId", nativeQuery = true)
    int mSubscribeCount(Long pageUserId);

}
