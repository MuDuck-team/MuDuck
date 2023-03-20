import { useEffect, useState } from 'react';
import styled from 'styled-components';
import PlayData from './PlayData';
import Dropdown from '../../components/DropDown';
import ImageCard from '../../components/ImageCard';
import Paging from '../../components/Pagination/Pagination';

function PlaysPage() {
  const pageSize = 20;
  const pageInfo = {
    page: 1,
    size: pageSize,
    totalElements: PlayData.length,
    totalPages: Math.ceil(PlayData.length / pageSize),
  };

  const [page, setPage] = useState(pageInfo.page);
  const [currentItems, setCurrentItems] = useState([]);
  const indexOfLastPost = page * pageInfo.size;
  const indexOfFirstPost = indexOfLastPost - pageInfo.size;

  useEffect(() => {
    setCurrentItems(PlayData.slice(indexOfFirstPost, indexOfLastPost));
  }, [page]);

  const [id, setId] = useState();
  console.log(id);

  const orderCategory = [
    { id: 1, categoryName: '최신순' },
    { id: 2, categoryName: '조회순' },
    { id: 3, categoryName: '이름순' },
  ];

  const orderGenre = [
    { id: 1, categoryName: '창작' },
    { id: 2, categoryName: '라이센스' },
    { id: 3, categoryName: '오리지널' },
  ];

  const orderState = [
    { id: 1, categoryName: '공연중' },
    { id: 2, categoryName: '개막 예정' },
    { id: 3, categoryName: '공연 종료' },
  ];

  return (
    <PlayPageLayout>
      <Category>공연</Category>
      <FilterContainer>
        <FilterName>정렬</FilterName>
        <Dropdown
          options={orderCategory}
          onClick={setId}
          width="100px"
          height="40px"
        />
        <FilterName>장르</FilterName>
        <Dropdown
          options={orderGenre}
          onClick={setId}
          width="100px"
          height="40px"
        />
        <FilterName>상태</FilterName>
        <Dropdown
          options={orderState}
          onClick={setId}
          width="100px"
          height="40px"
        />
      </FilterContainer>
      <CardContainer>
        {currentItems.map(play => (
          <ImageCard key={play.id} size="medium" alt={play.id} {...play} />
        ))}
      </CardContainer>
      <Paging
        activePage={page}
        itemsCount={pageInfo.size}
        totalItemCount={pageInfo.totalElements}
        pageRange={pageInfo.totalPages}
        setPage={setPage}
      />
    </PlayPageLayout>
  );
}

const PlayPageLayout = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;

  @media screen and (max-width: 1024px) {
    width: 90%;
  }
`;

const Category = styled.h2`
  margin-top: 4rem;
  margin-bottom: 2.4rem;
  font-size: var(--font-size-xxl);
  font-weight: bold;
`;

const FilterContainer = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 16px;
  gap: 1%;
`;

const FilterName = styled.span`
  font-size: var(--font-size-sm);
`;

const CardContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 1.6rem 2%;

  @media screen and (max-width: 640px) {
    gap: 2rem 4%;
  }
`;

export default PlaysPage;
