import { useLoaderData } from 'react-router-dom';
import styled from 'styled-components';
import {
  getPopularDailyPosts,
  getPopularWeeklyPosts,
  getPopularMusicals,
} from '../../api/muduckApi';
import Banners from './BannersData';
import Carousel from '../../components/Carousel';
import PopularPosts from './PopularPosts';
import PopularPlays from './PopularPlays';

export async function loader() {
  const [dailyPosts, weeklyPosts, musicals] = await Promise.all([
    getPopularDailyPosts(),
    getPopularWeeklyPosts(),
    getPopularMusicals(),
  ]);
  const dailyPostsData = dailyPosts.data;
  const weeklyPostsData = weeklyPosts.data;
  const musicalsData = musicals.data.data;
  return { dailyPostsData, weeklyPostsData, musicalsData };
}

function MainPage() {
  const { dailyPostsData, weeklyPostsData, musicalsData } = useLoaderData();

  return (
    <MainPageLayout>
      <Carousel banners={Banners} />
      <ContentContainer>
        <Category>인기 게시글</Category>
        <PopularPosts
          dailyPosts={dailyPostsData}
          weeklyPosts={weeklyPostsData}
        />
        <Category>추천 뮤지컬</Category>
        <PopularPlays musicals={musicalsData} />
      </ContentContainer>
    </MainPageLayout>
  );
}

const MainPageLayout = styled.div`
  display: flex;
  flex-direction: column;
  padding: 40px 0;

  @media screen and (max-width: 768px) {
    padding: 0;
  }
`;

const ContentContainer = styled.section`
  width: 100%;
  margin: 0 auto;

  @media screen and (max-width: 1024px) {
    width: 90%;
  }
`;

const Category = styled.h2`
  font-size: var(--font-size-xl);
  font-weight: bold;
  margin-top: 4rem;
  margin-bottom: 2.4rem;
`;

export default MainPage;
