import { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { IoIosClose } from 'react-icons/io';
import ProfileImg from '../../components/ProfileImage/ProfileImg';
import Button from '../../components/Button';

function AboutMusical({ musical, actors, theater }) {
  const [showModal, setShowModal] = useState(false);

  const arr = [];
  actors.forEach(casting => {
    arr.push(casting.role);
  });
  const playNameArr = [...new Set(arr)];

  const modalHandler = () => {
    setShowModal(!showModal);
  };

  return (
    <DetailsBox>
      <Title>
        {musical.musicalKorName}
        <DescriptionText textColor="#999999" fontWeight="600">
          {musical.musicalEngName}
        </DescriptionText>
      </Title>
      <SubTitle>
        세부장르
        <DescriptionText>{musical.genre}</DescriptionText>
      </SubTitle>
      <SubTitle>
        공연장소
        <PlaceLink to={`/nearby/${theater.theaterId}`}>
          {theater.theaterName}
        </PlaceLink>
      </SubTitle>
      <SubTitle>
        공연기간
        <DescriptionText>
          {musical.openDate}~{musical.closeDate}
        </DescriptionText>
      </SubTitle>
      <SubTitle>
        공연시간
        <DescriptionText>
          {musical.runningTime} (인터미션 : {musical.intermission}분)
        </DescriptionText>
      </SubTitle>
      <SubTitle>
        관람연령
        <DescriptionText>{musical.age}</DescriptionText>
      </SubTitle>
      <PlotBox>
        <SubTitle
          letterSpacing="0.65rem"
          marginRight="0.65rem"
          marginBottom="0"
        >
          줄거리
        </SubTitle>
        <DescriptionText marginLeft="0" lineHeight="1.6rem">
          {musical.musicalInfo}
        </DescriptionText>
      </PlotBox>
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
          <ModalBackground onClick={modalHandler}>
            <ModalContainer>
              <HeadingBox>
                <Title>출연진</Title>
                <IoIosClose
                  size="30"
                  color="#f2f2f2"
                  onClick={modalHandler}
                  cursor="pointer"
                />
              </HeadingBox>

              {playNameArr.map((playName, idx) => {
                return (
                  <div key={idx}>
                    <SubTitle fontSize="1.8rem" marginTop="2rem">
                      {playName} 역
                    </SubTitle>
                    <ModalActorProfileBox>
                      {actors.map(actor => {
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
          {actors.slice(0, 5).map((actor, idx) => {
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
  );
}

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
  line-height: ${({ lineHeight }) => lineHeight || 'inherit'};
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
  margin-right: ${({ marginRight }) => marginRight || '2rem'};
  margin-bottom: ${({ marginBottom }) => marginBottom || '0.8rem'};
  margin-top: ${({ marginTop }) => marginTop || '0'};
  white-space: nowrap;
  letter-spacing: ${({ letterSpacing }) => letterSpacing || '0'};
`;

const PlotBox = styled.div`
  width: 100%;
  display: flex;
  align-items: flex-start;
`;

const ActorsBox = styled(DetailsBox)`
  width: 100%;
  /* margin-top: 3rem; */
  padding: 0;
  margin: 3rem 0 0 0;
`;

const HeadingBox = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 1.2rem;
`;

const ActorProfileBox = styled.ul`
  width: 100%;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  /* padding: 1rem; */

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
export default AboutMusical;
