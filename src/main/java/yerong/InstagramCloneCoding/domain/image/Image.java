package yerong.InstagramCloneCoding.domain.image;

import jakarta.persistence.*;
import lombok.*;
import yerong.InstagramCloneCoding.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
public class Image {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String caption;
    private String postImageUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //좋아요

    //댓글

}
