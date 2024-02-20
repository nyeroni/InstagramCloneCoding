package yerong.InstagramCloneCoding.web.dto.user;

import lombok.*;
import yerong.InstagramCloneCoding.domain.user.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {

    private boolean pageOwnerState;
    private int imageCount;
    private boolean subscribeState;
    private int subscribeCount;
    private User user;
}
