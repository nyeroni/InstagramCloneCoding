package yerong.InstagramCloneCoding.web.dto.comment;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResDto {
    private Long id;
    private String content;
    private String username;
    private Long userId;
}
