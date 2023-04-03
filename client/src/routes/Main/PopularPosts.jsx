import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { ArticleCard } from '../../components/Cards';

function PopularPosts({ dailyPosts, weeklyPosts }) {
  return (
    <PostContainer>
      <PostSection>
        <Category>Daily</Category>
        {dailyPosts.length === 0 ? (
          <InformationText>
            아직 오늘의 인기글이 없어요 😅
            <Link to="/posts">게시글 작성하러 가기</Link>
          </InformationText>
        ) : (
          dailyPosts.map(post => (
            <ArticleCard
              key={post.id}
              id={post.id}
              title={post.title}
              nickname={post.nickName}
              userProfile={post.userProfile}
              lastCreatedAt={post.createdAt}
              view={post.viewCount}
              commentCount={post.commentsCount}
              boardLike={post.likeCount}
              type="post"
              titlefontSize="var(--font-size-md)"
            />
          ))
        )}
      </PostSection>
      <PostSection>
        <Category>Weekly</Category>
        {weeklyPosts.map(post => (
          <ArticleCard
            key={post.id}
            id={post.id}
            title={post.title}
            nickname={post.nickName}
            userProfile={post.userProfile}
            lastCreatedAt={post.createdAt}
            view={post.viewCount}
            commentCount={post.commentsCount}
            boardLike={post.likeCount}
            type="post"
            titlefontSize="var(--font-size-md)"
          />
        ))}
      </PostSection>
    </PostContainer>
  );
}

const PostContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 1.6rem 2%;
  justify-content: space-between;
`;

const PostSection = styled.section`
  display: flex;
  flex-direction: column;
  width: 48%;

  @media screen and (max-width: 640px) {
    width: 100%;
  }
`;

const Category = styled.h3`
  font-size: var(--font-size-lg);
  font-weight: bold;
  margin-bottom: 1.6rem;
`;

const InformationText = styled.p`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  min-height: 160px;
  margin-bottom: 8px;
  border-radius: 8px;
  font-size: var(--font-size-lg);
  background-color: var(--main-002);

  & > a {
    margin-top: 2rem;
    color: var(--main-003);
    font-size: var(--font-size-md);
  }
`;

export default PopularPosts;
