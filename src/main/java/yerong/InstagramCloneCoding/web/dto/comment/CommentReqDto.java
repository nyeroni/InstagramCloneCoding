package yerong.InstagramCloneCoding.web.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentReqDto {
    private Long imageId;

    @NotBlank
    private String content;
}
