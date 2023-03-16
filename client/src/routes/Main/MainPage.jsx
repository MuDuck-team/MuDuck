import styled from 'styled-components';
import Carousel from '../../components/Carousel';
import PopularPosts from './PopularPosts';
import PopularPlays from './PopularPlays';

function MainPage() {
  const banners = [
    {
      id: 1,
      url: 'https://via.placeholder.com/600/92c952',
      alt: '배너 1',
    },
    {
      id: 2,
      url: 'https://via.placeholder.com/600/771796',
      alt: '배너 2',
    },
    {
      id: 3,
      url: 'https://via.placeholder.com/600/24f355',
      alt: '배너 3',
    },
    {
      id: 4,
      url: 'https://via.placeholder.com/600/92c952',
      alt: '배너 4',
    },
    {
      id: 5,
      url: 'https://via.placeholder.com/600/771796',
      alt: '배너 5',
    },
  ];

  return (
    <Wrapper>
      <Carousel banners={banners} />
      <Container>
        <Category>인기글 추천</Category>
        <PopularPosts />
        <Category>추천 공연</Category>
        <PopularPlays />
      </Container>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding: 40px 0;

  @media screen and (max-width: 768px) {
    padding: 0;
  }
`;

const Container = styled.div`
  width: 100%;
  margin: 0 auto;

  @media screen and (max-width: 1024px) {
    width: 96%;
  }
`;

const Category = styled.h2`
  font-size: var(--font-size-xl);
  font-weight: bold;
  margin-top: 4rem;
  margin-bottom: 2.4rem;
`;

export default MainPage;
