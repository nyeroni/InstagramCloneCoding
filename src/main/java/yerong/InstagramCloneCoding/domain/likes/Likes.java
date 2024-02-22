package yerong.InstagramCloneCoding.domain.likes;

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
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk",
                        columnNames = {"image_id", "user_id"}
                )
        }
)
public class Likes extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    //User를 가져오면 안에 있는 image도 가져오게 돼서 image의 정보는 가져오지 않게 해야 함
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;



}
