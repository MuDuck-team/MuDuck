import { useState } from 'react';
import { Link, useLoaderData } from 'react-router-dom';
import axios from 'axios';
import styled from 'styled-components';
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai';
import { IoMdListBox } from 'react-icons/io';
import WriterInfo from '../../components/WriterInfo';
import { StyledInput } from '../../components/Input';
import Button from '../../components/Button';
import CommentList from './CommentList';

export async function loader({ params }) {
  const response = await axios.get(
    `${process.env.REACT_APP_API_URL}/board/${params.id}`,
  );
  const responseData = response.data;
  return { responseData };
}

function PostPage() {
  const { responseData } = useLoaderData();

  const { boardContent, comments } = responseData;
  const { head, body, liked } = boardContent;

  const [isLike, setIsLike] = useState(liked);

  const toggleLike = () => {
    setIsLike(!isLike);
  };

  return (
    <PostPageLayout>
      <Category>커뮤니티</Category>
      <ContentContainer>
        <WriterInfo
          profileUrl={head.userProfile}
          nickname={head.nickname}
          createdAt={head.createdAt}
          viewCount={head.view}
          totalComment={head.totalComment}
          boardLike={head.boardLike}
          category={head.category}
          type="postWriter"
        />
        <PostTitle>{body.title}</PostTitle>
        <PostCotent>{body.content}</PostCotent>
      </ContentContainer>
      <LinkContainer>
        <PostLike onClick={toggleLike}>
          {isLike ? <FillHeartIcon /> : <OutlineHeartIcon />}
          <ListText>스크랩</ListText>
        </PostLike>
        <ListLink to="/posts">
          <ListIcon />
          <ListText>목록으로</ListText>
        </ListLink>
      </LinkContainer>
      <CommentCount>{head.totalComment}개의 댓글</CommentCount>
      <InputContainer>
        <StyledInput width="88%" placeholder="댓글을 입력해주세요" />
        <Button width="10%" height="40px" text="등록" />
      </InputContainer>
      {comments.map(comment => (
        <CommentList key={comment.id} comment={comment} />
      ))}
    </PostPageLayout>
  );
}

const PostPageLayout = styled.main`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;
  @media screen and (max-width: 1024px) {
    width: 90%;
  }
`;

const Category = styled.h2`
  margin-top: 4rem;
  margin-bottom: 2.4rem;
  font-size: var(--font-size-xxl);
  font-weight: bold;
`;

const ContentContainer = styled.article`
  border-top: 1px solid var(--border-color);
`;

const PostTitle = styled.h3`
  margin-top: 3.2rem;
  margin-bottom: 2.4rem;
  font-size: var(--font-size-xl);
  font-weight: bold;
`;

const PostCotent = styled.p`
  min-height: 400px;
  font-size: var(--font-size-md);
  line-height: 2em;
`;

const LinkContainer = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  gap: 2%;
  margin: 1.6rem 0;
`;

const PostLike = styled.button`
  display: flex;
  align-items: center;
  padding: 0;
  border: none;
  background: none;
  color: var(--font-color);
`;

const FillHeartIcon = styled(AiFillHeart)`
  width: 18px;
  height: 18px;
`;

const OutlineHeartIcon = styled(AiOutlineHeart)`
  width: 18px;
  height: 18px;
`;

const ListLink = styled(Link)`
  display: flex;
  align-items: center;
`;

const ListIcon = styled(IoMdListBox)`
  width: 20px;
  height: 20px;
`;

const ListText = styled.span`
  margin-left: 4px;
  font-size: var(--font-size-sm);
`;

const CommentCount = styled.span`
  font-size: var(--font-size-md);
`;

const InputContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 1.6rem 0;
`;

export default PostPage;
