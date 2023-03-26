import { useNavigate } from 'react-router-dom';
import styled, { css } from 'styled-components';

function ImageCard({ id, size, src, alt, title, actors, ...props }) {
  const navigate = useNavigate();

  const onClick = () => {
    navigate(`/play/${id}`);
  };

  const actorsList = [];
  actors.map(actor => actorsList.push(actor.actorName));
  const actorList = actorsList.join(', ');

  return (
    <CardContainer size={size} onClick={onClick} {...props}>
      <ImageWrapper>
        <CardImage src={src} alt={alt} />
      </ImageWrapper>
      <PlayTitle>{title}</PlayTitle>
      <ActorList>{actorList}</ActorList>
    </CardContainer>
  );
}

const sizes = {
  medium: css`
    --web-width: 18.4%;
    --mobile-width: 48%;
    --title-size: var(--font-size-md);
    --actor-size: var(--font-size-xs);
  `,

  large: css`
    --web-width: 23.5%;
    --mobile-width: 48%;
    --title-size: var(--font-size-lg);
    --actor-size: var(--font-size-sm);
  `,
};

const CardContainer = styled.div`
  ${({ size }) => sizes[size]}

  display: flex;
  flex-direction: column;
  width: var(--web-width);

  @media screen and (max-width: 640px) {
    width: var(--mobile-width);
  }

  :hover {
    cursor: pointer;
    filter: brightness(0.8);
  }
`;

const ImageWrapper = styled.div`
  position: relative;
  width: 100%;
  height: 0;
  padding-top: 130%;
`;

const CardImage = styled.img`
  border-radius: 3px;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
`;

const PlayTitle = styled.span`
  margin-top: 8px;
  font-size: var(--title-size);
`;

const ActorList = styled.p`
  margin-top: 4px;
  font-size: var(--actor-size);
  filter: brightness(0.8);
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
`;

export default ImageCard;
