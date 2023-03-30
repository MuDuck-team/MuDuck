import { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styled, { css } from 'styled-components';
import { toast } from 'react-toastify';
import customAxios from '../../api/customAxios';
import WriterInfo from '../../components/WriterInfo';
import { StyledInput } from '../../components/Input';
import Button from '../../components/Button';

function CommentList({ comment }) {
  const { id, head, body, commentStatus, comments } = comment;

  const params = useParams();
  const navigate = useNavigate();
  const localToken = localStorage.getItem('localToken');

  const [isReply, setIsReply] = useState(false);
  const [isShowReply, setIsShowReply] = useState(false);

  const toggleReplyComment = () => {
    setIsReply(!isReply);
  };

  const toggleShowReplyList = () => {
    setIsShowReply(!isShowReply);
  };

  const [commentValue, setCommentValue] = useState('');

  const handleCommentChange = event => {
    setCommentValue(event.target.value);
  };

  const handleCommentSubmit = event => {
    event.preventDefault();

    if (commentValue === '') {
      toast.error('내용을 최소 1자 이상 입력해주세요');
      return;
    }

    customAxios
      .post(
        `/boards/${params.id}/comments/${id}`,
        {
          body: commentValue,
        },
        {
          headers: {
            Authorization: localToken,
          },
        },
      )
      .then(response => {
        setCommentValue('');
        setIsReply(!isReply);
        if (response.status === 201) {
          navigate('.', { replace: true });
        }
      })
      .catch(error => {
        console.error('Error submitting comment:', error);
      });
  };

  return (
    <CommentContainer>
      <WriterInfo
        commentId={id}
        commentStatus={commentStatus}
        memberId={head.memberId}
        profileUrl={head.userProfile}
        nickname={head.nickname}
        createdAt={head.createdAt}
      />
      <CommentContent>{body}</CommentContent>
      <CommentAction>
        {comments.length > 0 && (
          <ReplyList onClick={toggleShowReplyList}>
            {isShowReply ? '댓글 접기' : `댓글 ${comments.length}개`}
          </ReplyList>
        )}
        {commentStatus === '댓글게시' && (
          <ReplyInput onClick={toggleReplyComment}>댓글 쓰기</ReplyInput>
        )}
      </CommentAction>
      {isReply &&
        (localToken ? (
          <CommentForm>
            <StyledInput
              width="88%"
              placeholder="댓글을 작성해주세요"
              value={commentValue}
              onChange={handleCommentChange}
            />
            <Button
              width="10%"
              height="40px"
              text="등록"
              onClick={handleCommentSubmit}
            />
          </CommentForm>
        ) : (
          <CommentForm>
            <StyledInput
              width="88%"
              placeholder="로그인 후 이용 가능합니다"
              disabled
            />
            <Button width="10%" height="40px" text="등록" disabled />
          </CommentForm>
        ))}
      {isShowReply &&
        comments.map(reply => (
          <ReplyContainer key={reply.id}>
            <WriterInfo
              commentId={reply.id}
              commentStatus={reply.commentStatus}
              memberId={reply.head.memberId}
              profileUrl={reply.head.userProfile}
              nickname={reply.head.nickname}
              createdAt={reply.head.createdAt}
            />
            <CommentContent>{reply.body}</CommentContent>
          </ReplyContainer>
        ))}
    </CommentContainer>
  );
}

const CommentContainer = styled.div`
  border-bottom: 1px solid var(--border-color);
`;

const CommentContent = styled.div`
  margin: 0 var(--font-size-xs);
  font-size: var(--font-size-sm);
`;

const CommentAction = styled.div`
  display: flex;
  gap: 2%;
  margin: var(--font-size-xs);
`;

const button = css`
  padding: 0;
  border: none;
  background: none;
  font-size: var(--font-size-xs);
  &:hover {
    opacity: 0.8;
  }
`;

const ReplyList = styled.button`
  ${button}
  color: var(--button-color);
`;

const ReplyInput = styled.button`
  ${button}
  color: var(--font-color);
`;

const CommentForm = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 1.6rem 0;
`;

const ReplyContainer = styled.div`
  margin-left: 2%;
  padding-bottom: var(--font-size-xs);
  border-bottom: 1px dotted var(--border-color);
  &:last-child {
    border: none;
  }
`;

export default CommentList;
