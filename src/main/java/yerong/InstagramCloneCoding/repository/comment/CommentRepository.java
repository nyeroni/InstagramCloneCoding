package yerong.InstagramCloneCoding.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import yerong.InstagramCloneCoding.domain.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
