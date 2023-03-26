package MuDuck.MuDuck.comment.service;

import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.comment.entity.Comment.CommentStatus;
import MuDuck.MuDuck.comment.repository.CommentRepository;
import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import MuDuck.MuDuck.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment findComment(long commentId) {
        return findVerifiedComment(commentId);
    }

    public List<Comment> getCommentWithoutReply(List<Comment> comments) {
        List<Comment> withoutReplyComments = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getParent() == null) {
                withoutReplyComments.add(comment);
            }
        }
        return withoutReplyComments;
    }

    public Comment createComment(Comment comment) {
        Comment createdComment = commentRepository.save(comment);
        return createdComment;
    }

    public void deleteComment(long commentId, Member member){
        Comment comment = findVerifiedComment(commentId);
        if(comment.getMember().getMemberId() != member.getMemberId()){ // 댓글 삭제 요청자가 댓글 작성자가 아니라면
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER_STATUS);
        }else{ // 댓글 삭제 요청자가 댓글 작성자라면
            if (comment.getCommentStatus() == CommentStatus.COMMENT_DELETE){ // 이미 삭제된 상태라면
                throw new BusinessLogicException(ExceptionCode.COMMENT_REMOVED);
            }else{
                comment.setCommentStatus(CommentStatus.COMMENT_DELETE);
            }
        }
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    private Comment findVerifiedComment(long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        return optionalComment.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }

}
