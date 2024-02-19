package yerong.InstagramCloneCoding.repository.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yerong.InstagramCloneCoding.domain.image.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
