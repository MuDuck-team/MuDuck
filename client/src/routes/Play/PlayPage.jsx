import { useNavigate, useLoaderData, Link } from 'react-router-dom';
import styled from 'styled-components';
import AboutMusical from './AboutMusical';
import { ArticleCard } from '../../components/Cards';
import Mapbox from './Mapbox';
import {
  getMusicalDetail,
  getActorsDetail,
  getRelatedBoard,
} from '../../api/muduckApi';

export async function loader({ params }) {
  const [musicalData, actorsData, postsData] = await Promise.all([
    getMusicalDetail(params.id),
    getActorsDetail(params.id),
    getRelatedBoard(params.id),
  ]);
  return { musicalData, actorsData, postsData };
}

function PlayPage() {
  const navigate = useNavigate();
  const { musicalData, actorsData, postsData } = useLoaderData();
  const { musical, theater } = musicalData.data;
  const actors = JSON.parse(JSON.stringify(actorsData.data));
  const { boards } = postsData.data;

  console.log(musicalData);
  console.log(actorsData);
  console.log(postsData);

  // 더미데이터1
  // const musicalData = {
  //   musicalKorName: '베토벤',
  //   musicalEngName: 'Beethoven Secret',
  //   genre: '뮤지컬 > 오리지널',
  //   place: '세종문화회관 대극장',
  //   openDate: '2023.03.01',
  //   closeDate: '2023.05.28',
  //   playtime: '120분 (인터미션 : 20분)',
  //   age: '8세이상 관람가능',
  //   musicalInfo: '베토벤의 원곡들에 기반하고 실화에서 영감받다.',
  //   poster:
  //     'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Play/Image/20221116431308101cf72e403b20a0959afce22eacee299a.jpg',
  // };

  // 더미데이터2
  // const actorsData = {
  //   actors: [
  //     {
  //       id: '1',
  //       actorName: '박효신',
  //       picture:
  //         'https://search.pstatic.net/common?type=b&size=216&expire=1&refresh=true&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201409%2F20140922203416508-3731873.jpg',
  //       role: '루드비히 반 베토벤',
  //     },
  //     {
  //       id: '2',
  //       actorName: '박은태',
  //       picture:
  //         'https://search.pstatic.net/common?type=b&size=216&expire=1&refresh=true&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201501%2F20150115133314557-8462978.jpg',
  //       role: '루드비히 반 베토벤',
  //     },
  //     {
  //       id: '3',
  //       actorName: '조정은',
  //       picture:
  //         'https://search.pstatic.net/common?type=b&size=216&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201208%2F20120823143009309-3921969.jpg',
  //       role: '안토니 브렌타노',
  //     },
  //     {
  //       id: '4',
  //       actorName: '옥주현',
  //       picture:
  //         'https://search.pstatic.net/common?type=b&size=144&expire=1&refresh=true&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2F89%2F202012111721595391.jpg',
  //       role: '안토니 브렌타노',
  //     },
  //     {
  //       id: '5',
  //       actorName: '이해준',
  //       picture:
  //         'https://search.pstatic.net/common?type=b&size=216&expire=1&refresh=true&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F202302%2F20230216145124709.jpg',
  //       role: '카스파 반 베토벤',
  //     },
  //     {
  //       id: '6',
  //       actorName: '윤소호',
  //       picture:
  //         'https://search.pstatic.net/common?type=b&size=216&expire=1&refresh=true&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201909%2F20190919185107998.jpg',
  //       role: '카스파 반 베토벤',
  //     },
  //   ],
  // };

  // 더미데이터3;
  // const fakepostsData = {
  //   posts: [
  //     {
  //       nickname: '조이',
  //       title: '베토벤이 최고야',
  //       lastCreatedAt: '2023.02.02',
  //       url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Coco',
  //       view: '22',
  //       commentCount: '0',
  //       boardLike: '10',
  //     },
  // {
  //   nickname: '해피',
  //   title: 'H열 19번 좌석 조아',
  //   lastCreatedAt: '2023.01.22',
  //   url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Molly',
  //   view: '21',
  //   commentCount: '2',
  //   boardLike: '1',
  // },
  // {
  //   nickname: '반달',
  //   title: '제작년이 더 좋았던듯?',
  //   lastCreatedAt: '2023.01.20',
  //   url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Abby',
  //   view: '23',
  //   commentCount: '3',
  //   boardLike: '10',
  // },
  // {
  //   nickname: '뮬리몰리',
  //   title: '박효신 연기력 뭐임?',
  //   lastCreatedAt: '2023.01.15',
  //   url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Lucy',
  //   view: '663',
  //   commentCount: '52',
  //   boardLike: '100',
  // },
  // {
  //   nickname: '배고픈반달곰',
  //   title: '이번달만 3번째임',
  //   lastCreatedAt: '2023.01.12',
  //   url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Angel',
  //   view: '133',
  //   commentCount: '2',
  //   boardLike: '6',
  // },
  // {
  //   nickname: '뮤지컬금단현상',
  //   title: '아니? 마지막 왜저럼? 베토벤!?',
  //   lastCreatedAt: '2023.01.11',
  //   url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Sassy',
  //   view: '121',
  //   commentCount: '22',
  //   boardLike: '10',
  // },
  //   ],
  // };

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
          {/* {fakepostsData.posts.map((post, idx) => {
            return (
              <ArticleCard
                // id={post.}
                type="post"
                key={idx}
                minWidth="380px"
                width="48%"
                height="30%"
                marginBottom="1.5rem"
                borderRadius="8px"
                nickname={post.nickname}
                title={post.title}
                titlefontSize="1.6rem"
                titleMarginBottom="1.5rem"
                lastCreatedAt={post.lastCreatedAt}
                view={post.view}
                userProfile={post.url}
                commentCount={post.commentCount}
                boardLike={post.boardLike}
              />
            );
          })} */}
          {boards.length === 0 ? (
            <AlertBox>
              아직 관련된 이야기가 없어요 😅
              <Link to="/posts">커뮤니티 다른 글 보러가기</Link>
            </AlertBox>
          ) : (
            boards.map((post, idx) => {
              return (
                <ArticleCard
                  // id={post.}
                  type="post"
                  key={idx}
                  minWidth="380px"
                  width="48%"
                  height="30%"
                  marginBottom="1.5rem"
                  marginRight="1%"
                  borderRadius="8px"
                  nickname={post.nickname}
                  title={post.title}
                  titlefontSize="1.6rem"
                  titleMarginBottom="1.5rem"
                  lastCreatedAt={post.createdAt.split(' ')[0]}
                  view={post.views}
                  userProfile={post.profileImgUrl}
                  commentCount={post.commentCount}
                  boardLike={post.likes}
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
          <Mapbox />
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
