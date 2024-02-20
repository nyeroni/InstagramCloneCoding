package yerong.InstagramCloneCoding.web.dto.subscribe;

import lombok.*;

@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
public class SubscribeDto {

    private Long id;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState;
    private Integer equalState; //나 자신인지

}
