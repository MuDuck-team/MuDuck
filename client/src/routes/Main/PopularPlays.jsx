import styled from 'styled-components';
import ImageCard from '../../components/ImageCard';

function PopularPlays({ musicals }) {
  return (
    <PlaySection>
      {musicals.map(musical => (
        <ImageCard
          key={musical.id}
          id={musical.id}
          size="large"
          src={musical.poster}
          alt={musical.musicalKorName}
          title={musical.musicalKorName}
          // actors={musical.actors}
        />
      ))}
    </PlaySection>
  );
}

const PlaySection = styled.section`
  display: flex;
  flex-wrap: wrap;
  gap: 1.6rem 2%;
  max-width: 1120px;

  @media screen and (max-width: 640px) {
    gap: 2rem 4%;
  }
`;

export default PopularPlays;
