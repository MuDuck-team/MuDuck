import { useState } from 'react';
import styled, { css } from 'styled-components';
import WriterInfo from '../../components/WriterInfo';
import { StyledInput } from '../../components/Input';
import Button from '../../components/Button';

function CommentList({ comment }) {
  const { head, body, comments } = comment;
  const [isReply, setIsReply] = useState(false);
  const [isShowReply, setIsShowReply] = useState(false);

  const toggleReplyComment = () => {
    setIsReply(!isReply);
  };

  const toggleShowReplyList = () => {
    setIsShowReply(!isShowReply);
  };

  return (
    <CommentContainer>
      <WriterInfo
        profileUrl={head.userProfile}
        nickname={head.nickname}
        createdAt={head.createdAt}
      />
      <CommentContent>{body}</CommentContent>
      <CommentAction>
        {comments && (
          <ReplyList onClick={toggleShowReplyList}>
            {isShowReply ? '댓글 접기' : `댓글 ${comments.length}개`}
          </ReplyList>
        )}
        <ReplyInput onClick={toggleReplyComment}>댓글 쓰기</ReplyInput>
      </CommentAction>
      {isReply && (
        <InputContainer>
          <StyledInput width="88%" placeholder="댓글을 입력해주세요" />
          <Button width="10%" height="40px" text="등록" />
        </InputContainer>
      )}
      {isShowReply &&
        comments.map(reply => (
          <ReplyContainer>
            <WriterInfo
              key={reply.id}
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

const InputContainer = styled.div`
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
