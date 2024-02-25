package yerong.InstagramCloneCoding.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import yerong.InstagramCloneCoding.domain.BaseTimeEntity;
import yerong.InstagramCloneCoding.domain.image.Image;
import yerong.InstagramCloneCoding.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String content;

    @JsonIgnoreProperties("images")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;
}
