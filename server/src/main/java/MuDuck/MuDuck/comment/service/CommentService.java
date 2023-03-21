package MuDuck.MuDuck.comment.service;

import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.comment.repository.CommentRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getCommentWithoutReply(List<Comment> comments){
        List<Comment> withoutReplyComments = new ArrayList<>();
        for(Comment comment : comments){
            if(comment.getParent() == null){
                withoutReplyComments.add(comment);
            }
        }
        return withoutReplyComments;
    }

}
