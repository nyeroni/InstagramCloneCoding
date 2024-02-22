package yerong.InstagramCloneCoding.repository.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yerong.InstagramCloneCoding.domain.image.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "SELECT * from image where user_id = :principalId OR user_id In (SELECT to_user_id FROM subscribe WHERE from_user_id = :principalId) ", nativeQuery = true)
    List<Image> mStory(Long principalId);

}
