import { AiOutlineEye, AiOutlineHeart } from 'react-icons/ai';
import { BsChatSquareDots } from 'react-icons/bs';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import StarRating from './StarRating';

export function ArticleCard({
  userProfile,
  nickname,
  title,
  view,
  commentCount,
  boardLike,
  lastCreatedAt,
  id,
  minWidth,
  width,
  height,
  backgroundColor,
  borderRadius,
  marginBottom,
  marginRight,
  titlefontSize,
  titleMarginBottom,
  type,
}) {
  const navigate = useNavigate();

  const onClick = () => {
    if (type === 'post') {
      navigate(`/post/${id}`);
    } else {
      navigate(`/notice/${id}`);
    }
  };

  return (
    <CardContainer
      minWidth={minWidth}
      width={width}
      height={height}
      onClick={onClick}
      backgroundColor={backgroundColor}
      borderRadius={borderRadius}
      marginBottom={marginBottom}
      marginRight={marginRight}
    >
      <ContentWrapper>
        <TopWrapper titleMarginBottom={titleMarginBottom}>
          <img alt="profile" src={userProfile} />
          <span>{nickname}</span> {lastCreatedAt}
        </TopWrapper>
        <BottomWrapper>
          <TitleWrapper titlefontSize={titlefontSize}>{title}</TitleWrapper>
          {type === 'post' && (
            <IconsWrapper>
              <Icon>
                <AiOutlineEye /> {view}
              </Icon>
              <Icon>
                <BsChatSquareDots className="sizeDown" />
                {commentCount}
              </Icon>
              <Icon>
                <AiOutlineHeart /> {boardLike}
              </Icon>
            </IconsWrapper>
          )}
        </BottomWrapper>
      </ContentWrapper>
    </CardContainer>
  );
}

export function RatingCard({
  width,
  height,
  title,
  address,
  value,
  reviewsNum,
}) {
  return (
    <RatingCardContainer width={width} height={height} borderRadius="8px">
      <ContentWrapper>
        <TopContainer>
          <div>{title}</div>
          <RatingContainer>
            평점 {value}
            <StarRating defaultValue={value} size="16px" readonly />
          </RatingContainer>
        </TopContainer>
        <MiddleContainer>
          <div>{address}</div>
          <div>리뷰 수 {reviewsNum} 건</div>
        </MiddleContainer>
      </ContentWrapper>
    </RatingCardContainer>
  );
}

export function MyPageCard({ id, width, height, title, createdAt }) {
  const navigate = useNavigate();

  const onClick = () => {
    navigate(`/post/${id}`);
  };

  return (
    <CardContainer width={width} height={height} onClick={onClick}>
      <ContentWrapper>
        <BottomWrapper>
          <TitleWrapper>{title}</TitleWrapper>
          {createdAt}
        </BottomWrapper>
      </ContentWrapper>
    </CardContainer>
  );
}

const Flex = styled.section`
  display: flex;
  justify-content: space-between;
`;

const TopContainer = styled(Flex)`
  align-items: center;
`;

const MiddleContainer = styled(Flex)`
  font-size: var(--font-size-xxs);
  margin-bottom: 8px;
`;
const RatingContainer = styled.section`
  display: flex;
  align-items: center;
`;

const CardContainer = styled.article`
  min-width: ${props => props.minWidth};
  width: ${props => props.width};
  height: ${props => props.height};
  margin-bottom: ${props => props.marginBottom || '0.8rem'};
  margin-right: ${props => props.marginRight};
  background-color: ${props => props.backgroundColor || 'var(--main-002)'};
  font-size: var(--font-size-md);
  color: var(--font-color);
  border-bottom: 0.1px solid var(--border-color);
  border-radius: ${props => props.borderRadius || '8px'};
  border-bottom: 1px solid var(--border-color);

  &:hover {
    opacity: 0.8;
    cursor: pointer;
  }
`;

const RatingCardContainer = styled(CardContainer)`
  &:hover {
    opacity: 1;
    cursor: default;
  }
`;

const ContentWrapper = styled.div`
  padding: 12px;
`;

const TopWrapper = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: ${props => props.titleMarginBottom || '8px'};

  font-size: var(--font-size-xs);

  img {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    margin-right: 8px;
  }

  span {
    font-size: var(--font-size-sm);
    font-weight: 400;
    margin-left: 0.3rem;
    margin-right: 1rem;
  }
`;

const BottomWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const TitleWrapper = styled.div`
  word-break: break-all;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: ${props => props.titlefontSize || 'var(--font-size-lg)'};
`;

const IconsWrapper = styled.div`
  display: flex;
  align-items: center;
  font-size: 1.4rem;

  div + div {
    margin-left: 4px;
  }
`;

const Icon = styled.div`
  display: flex;
  align-items: center;
  svg {
    margin-right: 2px;
  }
  .sizeDown {
    transform: scale(0.85);
  }
`;
