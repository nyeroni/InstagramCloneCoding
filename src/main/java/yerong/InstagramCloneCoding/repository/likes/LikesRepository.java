package yerong.InstagramCloneCoding.repository.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import yerong.InstagramCloneCoding.domain.likes.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query(value = "INSERT INTO likes(image_id, user_id, createdAt) VALUES (:imageId, :principalId, now())", nativeQuery = true)
    int mLikes(Long imageId, Long principalId);

    @Modifying
    @Query(value = "DELETE FROM likes WHERE image_id = :imageId AND user_id = :principalId", nativeQuery = true)
    int mUnLikes(Long imageId, Long principalId);
}
