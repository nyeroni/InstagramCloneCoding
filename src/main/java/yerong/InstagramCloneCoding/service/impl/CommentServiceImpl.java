package yerong.InstagramCloneCoding.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.InstagramCloneCoding.domain.comment.Comment;
import yerong.InstagramCloneCoding.domain.image.Image;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.handler.exception.CustomApiException;
import yerong.InstagramCloneCoding.handler.exception.CustomException;
import yerong.InstagramCloneCoding.repository.comment.CommentRepository;
import yerong.InstagramCloneCoding.repository.image.ImageRepository;
import yerong.InstagramCloneCoding.repository.user.UserRepository;
import yerong.InstagramCloneCoding.service.CommentService;
import yerong.InstagramCloneCoding.web.dto.comment.CommentReqDto;
import yerong.InstagramCloneCoding.web.dto.comment.CommentResDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Transactional
    @Override
    public CommentResDto commentWrite(CommentReqDto requestDto, Long principalid){
        User user = userRepository.findById(principalid).orElseThrow(() -> new CustomException("해당 User를 찾을 수 없습니다."));
        Image image = imageRepository.findById(requestDto.getImageId()).orElseThrow(() -> new CustomException("해당 이미지를 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .user(user)
                .image(image)
                .build();
        commentRepository.save(comment);

        return CommentResDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .username(comment.getUser().getUsername())
                .build();
    }
    @Transactional
    @Override
    public void commentDelete(Long commentId){
        try {

            Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException("해당 댓글을 찾을 수 없습니다."));
            log.info("=====comment : " + comment.toString() + "-======");

            commentRepository.delete(comment);

        }catch (Exception e){
            throw new CustomApiException(e.getMessage());
        }
    }
}

