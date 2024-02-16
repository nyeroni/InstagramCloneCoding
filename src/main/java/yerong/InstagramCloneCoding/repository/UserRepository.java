package yerong.InstagramCloneCoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yerong.InstagramCloneCoding.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
