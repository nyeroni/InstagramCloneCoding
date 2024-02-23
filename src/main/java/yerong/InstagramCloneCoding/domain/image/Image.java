package yerong.InstagramCloneCoding.domain.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import yerong.InstagramCloneCoding.domain.BaseTimeEntity;
import yerong.InstagramCloneCoding.domain.likes.Likes;
import yerong.InstagramCloneCoding.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
public class Image extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String caption;
    private String postImageUrl;

    @JsonIgnoreProperties({"images"}) //user에 있는 images는 무시
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //좋아요
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Likes> likes = new ArrayList<>();

    @Transient
    private int likeCount;

    public void setLikeCount(int count) {
        this.likeCount = count;
    }

    //댓글

}
