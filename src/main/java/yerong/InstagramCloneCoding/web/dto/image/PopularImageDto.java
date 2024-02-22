package yerong.InstagramCloneCoding.web.dto.image;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PopularImageDto {
    private Long id;
    private String caption;
    private String postImageUrl;
    private Long userId;
    private String username;
    private String profileImageUrl;

}
