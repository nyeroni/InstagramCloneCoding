package yerong.InstagramCloneCoding.service;

import yerong.InstagramCloneCoding.web.dto.comment.CommentReqDto;
import yerong.InstagramCloneCoding.web.dto.comment.CommentResDto;

public interface CommentService {
    CommentResDto commentWrite(CommentReqDto requestDto, Long principalid);
    void commentDelete(Long commentId);
}
