import { useEffect, useState } from 'react';
import { useNavigate, useLoaderData, Link } from 'react-router-dom';
import styled from 'styled-components';
import AboutMusical from './AboutMusical';
import { ArticleCard } from '../../components/Cards';
import {
  getMusicalDetail,
  getActorsDetail,
  getRelatedBoard,
  getTheaterInfo,
} from '../../api/muduckApi';
import customAxios from '../../api/customAxios';
import MapContainer from '../../components/Map/MapContainer';

export async function loader({ params }) {
  const [musicalData, actorsData, postsData] = await Promise.all([
    getMusicalDetail(params.id),
    getActorsDetail(params.id),
    getRelatedBoard(params.id),
  ]);

  const theaterInfo = await getTheaterInfo(musicalData.data.theater.theaterId);

  return { musicalData, actorsData, postsData, theaterInfo };
}

function PlayPage() {
  const navigate = useNavigate();
  const [nearbyData, setNearbyData] = useState({});
  const { musicalData, actorsData, postsData, theaterInfo } = useLoaderData();
  const { musical, theater } = musicalData.data;
  const { actors } = actorsData.data;
  const { boards } = postsData.data;
  const { restaurants = [], cafes = [], parkings = [] } = nearbyData;

  useEffect(() => {
    async function getNearbyTheaterData(theaterId) {
      await customAxios({
        method: 'get',
        url: `/maps/theater/${theaterId}`,
      }).then(res => {
        return setNearbyData(res);
      });
    }
    getNearbyTheaterData(theater.theaterId);
  }, []);

  return (
    <Container>
      <ContentSection>
        <PosterBox>
          <PosterImg
            src={musical.poster}
            alt={`${musical.musicalKorName} Musical Poster`}
          />
        </PosterBox>
        <AboutMusical musical={musical} actors={actors} theater={theater} />
      </ContentSection>
      <ColumnContentSection>
        <HeadingBox>
          <SubTitle fontSize="1.8rem" fontWeight="700" marginTop="3rem">
            커뮤니티게시글
          </SubTitle>
        </HeadingBox>
        <CommunityContentSection>
          {boards.length === 0 ? (
            <AlertBox>
              아직 관련된 이야기가 없어요 😅
              <Link to="/posts">커뮤니티 다른 글 보러가기</Link>
            </AlertBox>
          ) : (
            boards.map((post, idx) => {
              return (
                <ArticleCard
                  type="post"
                  key={idx}
                  minWidth="380px"
                  width="48%"
                  height="30%"
                  marginBottom="1.5rem"
                  marginRight="1%"
                  borderRadius="8px"
                  titlefontSize="1.6rem"
                  titleMarginBottom="1.5rem"
                  id={post.boardId}
                  userProfile={post.userProfile}
                  nickname={post.nickname}
                  lastCreatedAt={post.createdAt}
                  title={post.title}
                  view={post.view || '0'}
                  commentCount={post.totalComment || '0'}
                  boardLike={post.like || '0'}
                />
              );
            })
          )}
        </CommunityContentSection>
      </ColumnContentSection>
      <ColumnContentSection>
        <HeadingBox>
          <SubTitle fontSize="1.8rem" fontWeight="700" marginTop="3rem">
            주변시설정보
          </SubTitle>
          <SubTitle
            fontSize="1.4rem"
            fontWeight="700"
            marginTop="3rem"
            onClick={() => {
              navigate(`/nearby/${theater.theaterId}`);
            }}
          >
            리뷰쓰러가기
          </SubTitle>
        </HeadingBox>
        <ContentSection>
          <MapContainer
            currentTheater={theaterInfo.data}
            restaurants={restaurants}
            cafes={cafes}
            parkings={parkings}
            markerMode
          />
        </ContentSection>
      </ColumnContentSection>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  height: fit-content;
  display: flex;
  flex-direction: column;
  padding-top: 40px;
  overflow-y: auto;

  @media screen and (max-width: 768px) {
    width: 100%;
    padding: 2rem;
    flex-wrap: wrap;
  }
`;

const ContentSection = styled.section`
  width: 100%;
  height: auto;
  display: flex;
  justify-content: center;
  align-items: center;

  @media screen and (max-width: 768px) {
    width: 100%;
    flex-wrap: wrap;
  }
`;
const ColumnContentSection = styled(ContentSection)`
  flex-direction: column;
  width: 100%;
  justify-content: flex-start;
  align-items: flex-start;
  padding: 3rem;

  @media screen and (max-width: 768px) {
    width: 100%;
    padding: 1rem;
  }
`;

const CommunityContentSection = styled(ContentSection)`
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  gap: 2px 2%;
  margin: auto;
  flex-wrap: wrap;

  @media screen and (max-width: 768px) {
    flex-wrap: wrap;
    padding-left: 2%;
  }
`;

const PosterBox = styled.div`
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: center;

  @media screen and (max-width: 768px) {
    width: 100%;
    flex-wrap: wrap;
  }
`;

const PosterImg = styled.img`
  width: 340px;
  height: 480px;
`;

const SubTitle = styled.h3`
  font-size: ${({ fontSize }) => fontSize || 'var(--font-size-sm)'};
  font-weight: ${({ fontWeight }) => fontWeight || '400'};
  color: var(--font-color);
  margin-right: 2rem;
  margin-bottom: 0.8rem;
  margin-top: ${({ marginTop }) => marginTop || '0'};
`;

const HeadingBox = styled.div`
  display: flex;
  width: 100%;
  padding: 2rem;
  justify-content: space-between;
`;

const AlertBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: var(--font-size-xl);
  border-radius: 8px;
  background-color: rgba(224, 224, 224, 0.05);
  width: 100%;
  height: 32rem;

  > a {
    font-size: var(--font-size-sm);
    font-weight: 300;
    color: var(--main-003);
    margin-top: 2rem;
  }

  @media screen and (max-width: 768px) {
    width: 100%;
    padding: 1rem;
  }
`;

export default PlayPage;
