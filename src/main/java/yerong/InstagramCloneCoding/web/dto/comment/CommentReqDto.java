package yerong.InstagramCloneCoding.web.dto.comment;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentReqDto {
    private Long imageId;
    private String content;
}
