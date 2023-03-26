import { AiOutlineEye, AiOutlineHeart } from 'react-icons/ai';
import { BsChatSquareDots } from 'react-icons/bs';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import StarRating from './StarRating';

export function ArticleCard({
  url,
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
}) {
  const navigate = useNavigate();

  const onClick = () => {
    if (commentCount) {
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
          <img alt="profile" src={url} />
          {nickname} {lastCreatedAt}
        </TopWrapper>
        <BottomWrapper>
          <TitleWrapper titlefontSize={titlefontSize}>{title}</TitleWrapper>
          {commentCount && (
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
    <CardContainer width={width} height={height} borderRadius="8px">
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
        <BottomContainer>한줄평 더보기</BottomContainer>
      </ContentWrapper>
    </CardContainer>
  );
}

export function MyPageCard({ width, height, title, lastCreatedAt, id }) {
  const navigate = useNavigate();

  const onClick = () => {
    navigate(`/post/${id}`);
  };

  return (
    <CardContainer width={width} height={height} onClick={onClick}>
      <ContentWrapper>
        <BottomWrapper>
          <TitleWrapper>{title}</TitleWrapper>
          {lastCreatedAt}
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

const BottomContainer = styled.div`
  font-size: var(--font-size-xs);
`;

const RatingContainer = styled.section`
  display: flex;
  align-items: center;
`;

const CardContainer = styled.article`
  min-width: ${props => props.minWidth};
  width: ${props => props.width};
  height: ${props => props.height};
  margin-bottom: ${props => props.marginBottom};
  margin-right: ${props => props.marginRight};
  background-color: ${props => props.backgroundColor || 'var(--main-002)'};
  font-size: var(--font-size-md);
  color: var(--font-color);
  border-bottom: 0.1px solid var(--border-color);
  border-radius: ${props => props.borderRadius};
  border-bottom: 1px solid var(--border-color);

  &:hover {
    opacity: 0.8;
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
`;

const BottomWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const TitleWrapper = styled.div`
  font-size: ${props => props.titlefontSize || 'var(--font-size-lg)'};
`;

const IconsWrapper = styled.div`
  display: flex;
  align-items: center;

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
