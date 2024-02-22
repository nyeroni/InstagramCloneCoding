package yerong.InstagramCloneCoding.web.dto.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import yerong.InstagramCloneCoding.domain.user.User;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageDto {

    private Long id;
    private String caption;
    private String postImageUrl;
    private Long userId;
    private String username;
    private String profileImageUrl;
}
