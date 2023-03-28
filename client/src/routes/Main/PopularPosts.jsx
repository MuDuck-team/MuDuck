import styled from 'styled-components';
import { ArticleCard } from '../../components/Cards';

function PopularPosts({ dailyPosts, weeklyPosts }) {
  return (
    <PostContainer>
      <PostSection>
        <Category>Daily</Category>
        {dailyPosts.map(post => (
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
            width="100%"
            height="100%"
            type="post"
          />
        ))}
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
            width="100%"
            height="100%"
            type="post"
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

export default PopularPosts;
