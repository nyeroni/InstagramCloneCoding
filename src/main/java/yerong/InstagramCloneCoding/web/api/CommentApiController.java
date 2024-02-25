package yerong.InstagramCloneCoding.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yerong.InstagramCloneCoding.config.auth.PrincipalDetails;
import yerong.InstagramCloneCoding.service.CommentService;
import yerong.InstagramCloneCoding.web.dto.CMRespDto;
import yerong.InstagramCloneCoding.web.dto.comment.CommentReqDto;
import yerong.InstagramCloneCoding.web.dto.comment.CommentResDto;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<CMRespDto<?>> commentWrite(
            @RequestBody CommentReqDto commentDto,
            @AuthenticationPrincipal PrincipalDetails principalDetails){
        CommentResDto commentResDto = commentService.commentWrite(commentDto, principalDetails.getUser().getId());

        return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(1, "댓글 작성 성공", commentResDto), HttpStatus.CREATED);

    }
    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<CMRespDto<?>> commentDelete(
            @PathVariable Long id
    ){
        log.info("=====id : " + id + "-======");
        commentService.commentDelete(id);
        return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(1, "댓글 삭제 성공", null), HttpStatus.OK);

    }
}
