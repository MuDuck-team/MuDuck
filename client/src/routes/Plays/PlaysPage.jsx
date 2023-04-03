import { useEffect, useState } from 'react';
import styled from 'styled-components';
import { useLoaderData, useNavigate } from 'react-router-dom';
import customAxios from '../../api/customAxios';
import Dropdown from '../../components/DropDown';
import ImageCard from '../../components/ImageCard';
import Paging from '../../components/Pagination/Pagination';

export async function loader({ request }) {
  const params = new URL(request.url).searchParams;
  const page = params.get('page') || '1';
  const sort = params.get('sort');
  const state = params.get('state');
  const genre = params.get('genre');

  if (sort) {
    const response = await customAxios.get(
      `/musicals?page=${page}&size=20&sort=${sort}`,
    );
    const responseData = response.data;
    const filter = 'sort';
    return { responseData, filter };
  }

  if (state) {
    const response = await customAxios.get(
      `/musicals?page=${page}&size=20&state=${state}`,
    );
    const responseData = response.data;
    const filter = 'state';
    return { responseData, filter };
  }

  if (genre) {
    const response = await customAxios.get(
      `/musicals?page=${page}&size=20&genre=${genre}`,
    );
    const responseData = response.data;
    const filter = 'genre';
    return { responseData, filter };
  }

  const response = await customAxios.get(`/musicals?page=${page}&size=20`);
  const responseData = response.data;
  const filter = 'none';
  return { responseData, filter };
}

function PlaysPage() {
  const navigate = useNavigate();
  const { responseData, filter } = useLoaderData();
  const { musicals, pageInfo } = responseData;

  const orderSort = [
    { id: 1, categoryName: '최신순', queryValue: 'openDate' },
    { id: 2, categoryName: '인기순', queryValue: 'views' },
    { id: 3, categoryName: '이름순', queryValue: 'musicalKorName' },
  ];

  const orderState = [
    { id: 1, categoryName: '공연중', queryValue: 'MUSICAL_ONAIR' },
    { id: 2, categoryName: '개막 예정', queryValue: 'MUSICAL_YET' },
    { id: 3, categoryName: '공연 종료', queryValue: 'MUSICAL_FINISH' },
  ];

  const orderGenre = [
    { id: 1, categoryName: '창작', queryValue: 'GENRE_CREATED' },
    { id: 2, categoryName: '라이센스', queryValue: 'GENRE_LICENSED' },
    { id: 3, categoryName: '오리지널', queryValue: 'GENRE_ORIGINAL' },
  ];

  const defaultFilter = { categoryName: '카테고리 선택' };

  const [sortFilter, setSortFilter] = useState(orderSort[0]);
  const [stateFilter, setStateFilter] = useState(defaultFilter);
  const [genreFilter, setGenreFilter] = useState(defaultFilter);

  useEffect(() => {
    if (filter === 'none') {
      setSortFilter(orderSort[0]);
      setStateFilter(defaultFilter);
      setGenreFilter(defaultFilter);
    }
  }, [filter]);

  const handleSortFilter = sort => {
    setSortFilter(sort);
    setStateFilter(defaultFilter);
    setGenreFilter(defaultFilter);
    navigate(`?page=1&size=20&sort=${sort.queryValue}`);
  };

  const handleStateFilter = state => {
    setSortFilter(orderSort[0]);
    setStateFilter(state);
    setGenreFilter(defaultFilter);
    navigate(`?page=1&size=20&state=${state.queryValue}`);
  };

  const handleGenreFilter = genre => {
    setSortFilter(orderSort[0]);
    setStateFilter(defaultFilter);
    setGenreFilter(genre);
    navigate(`?page=1&size=20&genre=${genre.queryValue}`);
  };

  const handlePagination = page => {
    switch (filter) {
      case 'sort':
        navigate(`?page=${page}&size=20&sort=${sortFilter.queryValue}`);
        break;
      case 'state':
        navigate(`?page=${page}&size=20&state=${stateFilter.queryValue}`);
        break;
      case 'genre':
        navigate(`?page=${page}&size=20&genre=${genreFilter.queryValue}`);
        break;
      default:
        navigate(`?page=${page}&size=20`);
    }
  };

  return (
    <PlayPageLayout>
      <Category>뮤지컬</Category>
      <FilterContainer>
        <FilterName>정렬</FilterName>
        <Dropdown
          options={orderSort}
          defaultValue={sortFilter}
          onClick={handleSortFilter}
          width="100px"
          height="40px"
          selectedValue={sortFilter}
        />
        <FilterName>상태</FilterName>
        <Dropdown
          options={orderState}
          defaultValue={stateFilter}
          onClick={handleStateFilter}
          width="100px"
          height="40px"
          selectedValue={stateFilter}
        />
        <FilterName>장르</FilterName>
        <Dropdown
          options={orderGenre}
          defaultValue={genreFilter}
          onClick={handleGenreFilter}
          width="100px"
          height="40px"
          selectedValue={genreFilter}
        />
      </FilterContainer>
      {musicals.length === 0 ? (
        <InformationText>
          조건에 해당하는 뮤지컬이 존재하지 않습니다
        </InformationText>
      ) : (
        <>
          <CardContainer>
            {musicals.map(musical => (
              <ImageCard
                key={musical.id}
                id={musical.id}
                size="medium"
                src={musical.poster}
                alt={musical.musicalKorName}
                title={musical.musicalKorName}
                // actors={musical.actors}
              />
            ))}
          </CardContainer>
          <Paging
            activePage={pageInfo.page}
            itemsCount={pageInfo.size}
            totalItemCount={pageInfo.totalElements}
            pageRange={pageInfo.totalPages}
            setPage={handlePagination}
          />
        </>
      )}
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

const InformationText = styled.p`
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  height: 400px;
  font-size: var(--font-size-xl);
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
