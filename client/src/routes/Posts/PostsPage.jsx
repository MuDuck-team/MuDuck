import { useState } from 'react';
import { useLoaderData, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { ArticleCard } from '../../components/Cards';
import Dropdown from '../../components/DropDown';
import Paging from '../../components/Pagination/Pagination';
import Button from '../../components/Button';

const dummyData = {
  noticeBoards: [
    {
      id: 1,
      lastCreatedAt: '약 5시간 전',
      title: '[공지사항] 뮤지컬 관람 규칙',
    },
  ],
  boards: [
    {
      id: 1,
      memberId: 1,
      nickname: '닉네임',
      lastCreatedAt: '약 5시간 전',
      userProfile: '유저프로필사진주소',
      title: '[자유주제제] 정말 재미 있었어요!',
      view: 30,
      commentCount: 30,
      boardLike: 30,
    },
    {
      id: 2,
      memberId: 2,
      nickname: '닉네임',
      lastCreatedAt: '약 5시간 전',
      userProfile: '유저프로필사진주소',
      title: '[자유주제] 정말 재미 있었어요!',
      view: 30,
      commentCount: 30,
      boardLike: 30,
    },
  ],
  category: [
    { id: 1, categoryName: '자유주제' },
    { id: 2, categoryName: '공연정보/후기' },
    { id: 3, categoryName: '시설정보' },
  ],
  pageInfo: {
    page: 1,
    size: 10,
    totalElements: 30,
    totalPages: 1,
  },
};

function getData({ page, categoryName, sortBy }) {
  console.log('aaa', page, categoryName, sortBy);
  return dummyData;
  // https://axios-http.com/kr/docs/api_intro
}

export async function loader({ request }) {
  const params = new URL(request.url).searchParams;
  const sortBy = params.get('sortBy');
  const categoryName = params.get('categoryName');
  const page = params.get('page');
  // 더미 함수 서버가 나오면 고칠 계획입니다.
  const obj = await getData({ sortBy, categoryName, page });
  return { obj };
}

const sortArr = [
  { id: 1, categoryName: '최신순', value: 'recent' },
  { id: 2, categoryName: '조회순', value: 'view' },
  { id: 3, categoryName: '좋아요순', value: 'like' },
];

function PostsPage() {
  const { obj } = useLoaderData();
  const { noticeBoards, boards, pageInfo, category } = obj;
  const navigate = useNavigate();
  const [currentSortBy, setCurrentSortBy] = useState(sortArr[0]);
  const [currentCategoryName, setCurrentCategoryName] = useState(category[0]);

  console.log(currentSortBy, currentCategoryName);

  const onClickSortBy = sortObj => {
    setCurrentSortBy(sortObj);
    const sortByString = `&sortBy=${sortObj.value}`;
    const categoryNameString =
      currentCategoryName &&
      `&categoryName=${currentCategoryName.categoryName}`;
    navigate(`?page=1${sortByString}${categoryNameString}`);
  };

  const onClickCategoryName = categoryNameObj => {
    setCurrentCategoryName(categoryNameObj);
    setCurrentSortBy(sortArr[0]);
    const sortByString = `&sortBy=${sortArr[0].value}`;
    const categoryNameString = `&categoryName=${categoryNameObj.categoryName}`;
    navigate(`?page=1${sortByString}${categoryNameString}`);
  };

  const setPage = page => {
    const sortByString = currentSortBy && `&sortBy=${currentSortBy.value}`;
    const categoryNameString =
      currentCategoryName &&
      `&categoryName=${currentCategoryName.categoryName}`;
    navigate(`?page=${page}${sortByString}${categoryNameString}`);
  };

  const goToAdd = () => {
    navigate('/post/add');
  };

  return (
    <>
      <StyledH2>커뮤니티 목록</StyledH2>
      <FilterButtonContainer>
        <Button type="button" text="작성하기" onClick={goToAdd} />
        <FilterContainer>
          <p>카테고리</p>
          <Dropdown
            options={category}
            defaultValue={currentCategoryName}
            onClick={onClickCategoryName}
            height="37px"
            selectedValue={currentCategoryName}
          />
          <p>정렬</p>
          <Dropdown
            options={sortArr}
            defaultValue={currentSortBy}
            onClick={onClickSortBy}
            height="37px"
            selectedValue={currentSortBy}
          />
        </FilterContainer>
      </FilterButtonContainer>

      {noticeBoards.map(notice => (
        <ArticleCard
          {...notice}
          width="100%"
          key={notice.id}
          backgroundColor="var(--main-001)"
        />
      ))}
      {boards.map(board => (
        <ArticleCard
          {...board}
          width="100%"
          key={board.id}
          backgroundColor="var(--main-001)"
        />
      ))}
      {/* 페이지 네이션 테스트하기 위해 일단 임의의 값을 넣었습니다 */}
      <Paging
        setPage={setPage}
        activePage={pageInfo.page}
        itemsCount={15}
        totalItemCount={pageInfo.totalElements}
        pageRange={5}
      />
    </>
  );
}

const StyledH2 = styled.h2`
  margin-top: 40px;
  font-size: var(--font-size-xxl);
  margin-bottom: '8px';
  font-weight: bold;
`;

const FilterButtonContainer = styled.section`
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
`;

const FilterContainer = styled.section`
  display: flex;
  align-items: center;
  p {
    margin: 0 10px;
    font-size: var(--font-size-sm);
  }
`;

export default PostsPage;
