import { useState } from 'react';
// import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';
// import axios from 'axios';
import { IoIosClose } from 'react-icons/io';
import styled from 'styled-components';
import ProfileImg from '../../components/ProfileImage/ProfileImg';
import Button from '../../components/Button';
import { ArticleCard } from '../../components/Cards';
import Mapbox from './Mapbox';

function PlayPage() {
  // const { musicalId } = useParams();
  // const [data, setData] = useState(null);
  const [showModal, setShowModal] = useState(false);

  // useEffect(() => {
  //   axios
  //     .all([
  //       axios.get(`/musicals/${musicalId}`),
  //       axios.get(`/musicals/${musicalId}/actors`),
  //       axios.get(`/musicals/${musicalId}/categorys/${musicalId}/board`),
  //     ])
  //     .then(
  //       axios
  //         .spread((res1, res2, res3) => {
  //           const musicalData = res1.data;
  //           const actorsData = res2.data;
  //           const postsData = res3.data;
  //           const EveryData = [...musicalData, ...actorsData, ...postsData];
  //           setData(EveryData);
  //         })
  //         .catch(err => console.log(err)),
  //     );
  // });

  // 더미데이터1
  const musicalData = {
    musicalKorName: '베토벤',
    musicalEngName: 'Beethoven Secret',
    genre: '뮤지컬 > 오리지널',
    place: '세종문화회관 대극장',
    openDate: '2023.03.01',
    closeDate: '2023.05.28',
    playtime: '120분 (인터미션 : 20분)',
    age: '8세이상 관람가능',
    musicalInfo: '베토벤의 원곡들에 기반하고 실화에서 영감받다.',
    poster:
      'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Play/Image/20221116431308101cf72e403b20a0959afce22eacee299a.jpg',
  };

  // 더미데이터2
  const actorsData = {
    actors: [
      {
        id: '1',
        actorName: '박효신',
        picture:
          'https://search.pstatic.net/common?type=b&size=216&expire=1&refresh=true&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201409%2F20140922203416508-3731873.jpg',
        role: '루드비히 반 베토벤',
      },
      {
        id: '2',
        actorName: '박은태',
        picture:
          'https://search.pstatic.net/common?type=b&size=216&expire=1&refresh=true&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201501%2F20150115133314557-8462978.jpg',
        role: '루드비히 반 베토벤',
      },
      {
        id: '3',
        actorName: '조정은',
        picture:
          'https://search.pstatic.net/common?type=b&size=216&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201208%2F20120823143009309-3921969.jpg',
        role: '안토니 브렌타노',
      },
      {
        id: '4',
        actorName: '옥주현',
        picture:
          'https://search.pstatic.net/common?type=b&size=144&expire=1&refresh=true&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2F89%2F202012111721595391.jpg',
        role: '안토니 브렌타노',
      },
      {
        id: '5',
        actorName: '이해준',
        picture:
          'https://search.pstatic.net/common?type=b&size=216&expire=1&refresh=true&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F202302%2F20230216145124709.jpg',
        role: '카스파 반 베토벤',
      },
      {
        id: '6',
        actorName: '윤소호',
        picture:
          'https://search.pstatic.net/common?type=b&size=216&expire=1&refresh=true&quality=100&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201909%2F20190919185107998.jpg',
        role: '카스파 반 베토벤',
      },
    ],
  };

  //  더미데이터3
  const postsData = {
    posts: [
      {
        nickname: '조이',
        title: '베토벤이 최고야',
        lastCreatedAt: '2023.02.02',
        url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Coco',
        view: '22',
        commentCount: '0',
        boardLike: '10',
      },
      {
        nickname: '해피',
        title: 'H열 19번 좌석 조아',
        lastCreatedAt: '2023.01.22',
        url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Molly',
        view: '21',
        commentCount: '2',
        boardLike: '1',
      },
      {
        nickname: '반달',
        title: '제작년이 더 좋았던듯?',
        lastCreatedAt: '2023.01.20',
        url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Abby',
        view: '23',
        commentCount: '3',
        boardLike: '10',
      },
      {
        nickname: '뮬리몰리',
        title: '박효신 연기력 뭐임?',
        lastCreatedAt: '2023.01.15',
        url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Lucy',
        view: '663',
        commentCount: '52',
        boardLike: '100',
      },
      {
        nickname: '배고픈반달곰',
        title: '이번달만 3번째임',
        lastCreatedAt: '2023.01.12',
        url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Angel',
        view: '133',
        commentCount: '2',
        boardLike: '6',
      },
      {
        nickname: '뮤지컬금단현상',
        title: '아니? 마지막 왜저럼? 베토벤!?',
        lastCreatedAt: '2023.01.11',
        url: 'https://api.dicebear.com/5.x/thumbs/svg?seed=Sassy',
        view: '121',
        commentCount: '22',
        boardLike: '10',
      },
    ],
  };

  const arr = [];
  actorsData.actors.forEach(role => {
    arr.push(role.role);
  });
  const playNameArr = [...new Set(arr)];

  const modalHandler = () => {
    setShowModal(!showModal);
  };

  return (
    <Container>
      <ContentSection>
        <PosterBox>
          <PosterImg
            src={musicalData.poster}
            alt={`${musicalData.musicalKorName} Musical Poster`}
          />
          {/* <PosterImg src={data.posterUrl} alt=`${data.musicalName} Musical Poster` /> */}
        </PosterBox>
        <DetailsBox>
          <Title>
            {musicalData.musicalKorName}
            <DescriptionText textColor="var(--main-003)">
              {musicalData.musicalEngName}
            </DescriptionText>
          </Title>
          <SubTitle>
            세부장르
            <DescriptionText>{musicalData.genre}</DescriptionText>
          </SubTitle>
          <SubTitle>
            공연장소
            <PlaceLink to="/nearby">{musicalData.place}</PlaceLink>
          </SubTitle>
          <SubTitle>
            공연기간
            <DescriptionText>
              `{musicalData.openDate}~{musicalData.closeDate}`
            </DescriptionText>
          </SubTitle>
          <SubTitle>
            공연시간
            <DescriptionText>{musicalData.playtime}</DescriptionText>
          </SubTitle>
          <SubTitle>
            관람연령
            <DescriptionText>{musicalData.age}</DescriptionText>
          </SubTitle>
          <SubTitle>
            줄거리
            <DescriptionText>{musicalData.musicalInfo}</DescriptionText>
          </SubTitle>
          <ActorsBox>
            <HeadingBox>
              <DescriptionText
                fontWeight="600"
                fontSize="var(--font-size-md)"
                marginLeft="0"
              >
                출연진
              </DescriptionText>
              <Button
                text="더보기 +"
                bgColor="transparent"
                textColor="var(--font-color)"
                hover="none"
                active="none"
                fontWeight="300"
                height="1.4rem"
                onClick={modalHandler}
              />
            </HeadingBox>

            {showModal ? (
              <ModalBackground>
                <ModalContainer>
                  <HeadingBox>
                    <Title>출연진</Title>
                    <IoIosClose
                      size="30"
                      color="#f2f2f2"
                      onClick={modalHandler}
                    />
                  </HeadingBox>

                  {playNameArr.map((playName, idx) => {
                    return (
                      <div key={idx}>
                        <SubTitle fontSize="1.8rem" marginTop="2rem">
                          {playName} 역
                        </SubTitle>
                        <ModalActorProfileBox>
                          {actorsData.actors.map(actor => {
                            return actor.role === playName ? (
                              <Profilelist key={actor.actorName}>
                                <ProfileImg
                                  type="actor"
                                  name={actor.actorName}
                                  src={actor.picture}
                                />
                              </Profilelist>
                            ) : null;
                          })}
                        </ModalActorProfileBox>
                      </div>
                    );
                  })}
                </ModalContainer>
              </ModalBackground>
            ) : null}

            <ActorProfileBox>
              {actorsData.actors.slice(0, 5).map((actor, idx) => {
                return (
                  <Profilelist key={idx}>
                    <ProfileImg
                      type="actor"
                      name={actor.actorName}
                      src={actor.picture}
                    />
                  </Profilelist>
                );
              })}
            </ActorProfileBox>
          </ActorsBox>
        </DetailsBox>
      </ContentSection>

      <SubTitle fontSize="1.8rem" fontWeight="700" marginTop="3rem">
        커뮤니티게시글
      </SubTitle>
      <ContentSection>
        <ColumnContentSection>
          {postsData.posts.slice(0, 3).map((post, idx) => {
            return (
              <ArticleCard
                key={idx}
                width="100%"
                height="30%"
                marginBottom="1.5rem"
                borderRadius="8px"
                nickname={post.nickname}
                lastCreatedAt={post.lastCreatedAt}
                title={post.title}
                titlefontSize="1.8rem"
                titleMarginBottom="1.5rem"
                view={post.view}
                url={post.url}
                commentCount={post.commentCount}
                boardLike={post.boardLike}
              />
            );
          })}
        </ColumnContentSection>
        <ColumnContentSection>
          {postsData.posts.slice(3).map((post, index) => {
            return (
              <ArticleCard
                key={index}
                width="100%"
                height="30%"
                marginBottom="1.5rem"
                borderRadius="8px"
                nickname={post.nickname}
                lastCreatedAt={post.lastCreatedAt}
                title={post.title}
                titlefontSize="1.8rem"
                titleMarginBottom="1.5rem"
                view={post.view}
                url={post.url}
                commentCount={post.commentCount}
                boardLike={post.boardLike}
              />
            );
          })}
        </ColumnContentSection>
      </ContentSection>
      <HeadingBox>
        <SubTitle fontSize="1.8rem" fontWeight="700" marginTop="3rem">
          주변시설정보
        </SubTitle>
        <SubTitle fontSize="1.4rem" fontWeight="500" marginTop="3rem">
          리뷰쓰러가기
        </SubTitle>
      </HeadingBox>
      <ContentSection>
        <Mapbox />
      </ContentSection>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  height: fit-content;
  display: flex;
  flex-direction: column;
  /* border: 1px solid red; */
  padding-top: 40px;
  overflow-y: auto;
  @media screen and (max-width: 768px) {
    width: 100%;
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
  padding: 1rem;
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

const DetailsBox = styled.div`
  width: 80%;
  height: auto;
  display: flex;
  flex-direction: column;
  padding: 1.6rem;
  margin: 1rem;
`;

const Title = styled.h2`
  font-weight: 700;
  font-size: var(--font-size-xl);
  margin-bottom: 2rem;
`;

const DescriptionText = styled.span`
  font-size: ${({ fontSize }) => fontSize || 'var(--font-size-sm)'};
  font-weight: ${({ fontWeight }) => fontWeight || 200};
  color: ${({ textColor }) => textColor || 'var(--font-color)'};
  margin-left: ${({ marginLeft }) => marginLeft || '1.2rem'};
`;

const PlaceLink = styled(Link)`
  margin-left: 1rem;
  font-weight: 600;
  color: var(--line-color);
`;

const SubTitle = styled.h3`
  font-size: ${({ fontSize }) => fontSize || 'var(--font-size-sm)'};
  font-weight: ${({ fontWeight }) => fontWeight || '400'};
  color: var(--font-color);
  margin-right: 2rem;
  margin-bottom: 0.8rem;
  margin-top: ${({ marginTop }) => marginTop || '0'};
`;

const ActorsBox = styled(DetailsBox)`
  width: 100%;
  margin-top: 3rem;
`;

const HeadingBox = styled.div`
  display: flex;
  justify-content: space-between;
`;

const ActorProfileBox = styled.ul`
  width: 100%;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  padding: 1rem;

  @media screen and (max-width: 768px) {
    width: 100%;
    flex-wrap: wrap;
  }
`;

const ModalActorProfileBox = styled(ActorProfileBox)`
  justify-content: flex-start;
`;

const Profilelist = styled.li`
  display: inline-flex;
  list-style: none;
`;

const ModalBackground = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.95);
  z-index: 0;
  z-index: 54;
`;

const ModalContainer = styled.div`
  position: fixed;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  height: 50%;
  width: 50%;
  padding: 30px;
  background: rgba(255, 255, 255, 0.2);
  border-radius: 8px;
  font-size: var(--font-size-sm);
  overflow-y: auto;
  z-index: 55;
`;

export default PlayPage;
