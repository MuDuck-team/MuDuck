import styled from 'styled-components';
import MyInformation from './MyInformation';
import MyPostList from './MyPostList';
import MyLikePostList from './MyLikePostList';
import MyCommentList from './MyCommentList';

function MyPage() {
  return (
    <MyPageLayout>
      <Category>마이 페이지</Category>
      <MyInformation />
      <ContentContainer>
        <PostContainer>
          <Title>내가 작성한 글</Title>
          <MyPostList />
          <Title>내가 좋아요 글</Title>
          <MyLikePostList />
        </PostContainer>
        <CommentContainer>
          <Title>내가 작성한 댓글</Title>
          <MyCommentList />
        </CommentContainer>
      </ContentContainer>
    </MyPageLayout>
  );
}

const MyPageLayout = styled.main`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;

  @media screen and (max-width: 1024px) {
    width: 90%;
  }
`;

const Category = styled.h2`
  font-size: var(--font-size-xxl);
  font-weight: bold;
  margin-top: 4rem;
  margin-bottom: 2.4rem;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 1.6rem 2%;
  width: 100%;
  height: 100%;
`;

const PostContainer = styled.section`
  width: 49%;

  @media screen and (max-width: 768px) {
    width: 100%;
  }
`;

const CommentContainer = styled.section`
  width: 49%;
  height: 100%;

  @media screen and (max-width: 768px) {
    width: 100%;
  }
`;

const Title = styled.h3`
  font-size: var(--font-size-md);
  margin-bottom: 8px;
`;

export default MyPage;
