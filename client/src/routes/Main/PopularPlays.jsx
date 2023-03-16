import styled from 'styled-components';
import ImageCard from '../../components/ImageCard';

function PopularPlays() {
  const list = [
    {
      id: 1,
      src: 'https://cdn.aitimes.com/news/photo/202204/143854_149286_5624.png',
      title: '최고다 강아지 1',
      actor: '김코딩, 박해커, 이자바, 한리액트',
    },
    {
      id: 2,
      src: 'https://cdn.aitimes.com/news/photo/202204/143854_149286_5624.png',
      title: '최고다 강아지 2',
      actor: '김코딩, 박해커, 이자바, 최리액트',
    },
    {
      id: 3,
      src: 'https://cdn.aitimes.com/news/photo/202204/143854_149286_5624.png',
      title: '최고다 강아지 3',
      actor: '김코딩, 박해커, 이자바, 주리액트',
    },
    {
      id: 4,
      src: 'https://cdn.aitimes.com/news/photo/202204/143854_149286_5624.png',
      title: '최고다 강아지 4',
      actor: '김코딩, 박해커, 이자바, 연리액트',
    },
  ];

  return (
    <PlaySection>
      {list.map(cat => (
        <ImageCard
          key={cat.id}
          id={cat.id}
          size="large"
          src={cat.src}
          alt={cat.title}
          title={cat.title}
          actor={cat.actor}
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
