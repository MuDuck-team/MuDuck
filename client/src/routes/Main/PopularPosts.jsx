import styled from 'styled-components';
import { ArticleCard } from '../../components/Cards';

function PopularPosts() {
  const board = [
    {
      id: 1,
      userProfile:
        'https://cdn.aitimes.com/news/photo/202204/143854_149286_5624.png',
      nickname: '뮤지컬광기',
      title: '오늘도 우리 뮤지컬은 최고입니다',
      actor: '김코딩, 박해커, 이자바, 한리액트',
      view: 30,
      commentCount: 30,
      boardLike: 30,
      createdAt: '2023.01.02 21:23',
    },
    {
      id: 2,
      userProfile:
        'https://cdn.aitimes.com/news/photo/202204/143854_149286_5624.png',
      nickname: '뮤지컬광인',
      title: '오늘도 김코딩 외모는 최고입니다',
      actor: '김코딩, 박해커, 이자바, 한리액트',
      view: 30,
      commentCount: 30,
      boardLike: 30,
      createdAt: '2023.01.02 21:23',
    },
    {
      id: 3,
      userProfile:
        'https://cdn.aitimes.com/news/photo/202204/143854_149286_5624.png',
      nickname: '뮤지컬광인',
      title: '오늘도 박해커 외모는 최고입니다',
      actor: '김코딩, 박해커, 이자바, 한리액트',
      view: 30,
      commentCount: 30,
      boardLike: 30,
      createdAt: '2023.01.02 21:23',
    },
    {
      id: 4,
      userProfile:
        'https://cdn.aitimes.com/news/photo/202204/143854_149286_5624.png',
      nickname: '뮤지컬광인',
      title: '오늘도 이자바 외모는 최고입니다',
      actor: '김코딩, 박해커, 이자바, 한리액트',
      view: 30,
      commentCount: 30,
      boardLike: 30,
      createdAt: '2023.01.02 21:23',
    },
    {
      id: 5,
      userProfile:
        'https://cdn.aitimes.com/news/photo/202204/143854_149286_5624.png',
      nickname: '뮤지컬광인',
      title: '오늘도 한리액트 외모는 최고입니다',
      actor: '김코딩, 박해커, 이자바, 한리액트',
      view: 30,
      commentCount: 30,
      boardLike: 30,
      createdAt: '2023.01.02 21:23',
    },
  ];

  return (
    <PostContainer>
      <PostSection>
        <Category>Daily</Category>
        {board.map(post => (
          <ArticleCard
            key={post.id}
            {...post}
            url={post.userProfile}
            width="100%"
            height="100%"
            borderRadius="8px"
          />
        ))}
      </PostSection>
      <PostSection>
        <Category>Weekly</Category>
        {board.map(post => (
          <ArticleCard
            key={post.id}
            {...post}
            url={post.userProfile}
            width="100%"
            height="100%"
            borderRadius="8px"
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
