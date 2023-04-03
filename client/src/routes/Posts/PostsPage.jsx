import { useState } from 'react';
import { useLoaderData, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { ArticleCard } from '../../components/Cards';
import Dropdown from '../../components/DropDown';
import Paging from '../../components/Pagination/Pagination';
import Button from '../../components/Button';
import customAxios from '../../api/customAxios';

export async function loader({ request }) {
  try {
    const params = new URL(request.url).searchParams;
    const sortBy = params.get('sortBy') || 'recent';
    const categoryName = params.get('categoryName') || '전체';
    const page = params.get('page') || '1';
    const obj = await customAxios.get(
      `/boards?page=${page}&sortBy=${sortBy}&categoryName=${categoryName}`,
    );
    const { data } = obj;

    return { data, sortBy, categoryName };
  } catch (e) {
    console.log(e);
    return undefined;
  }
}

const sortArr = [
  { id: 1, categoryName: '최신순', value: 'recent' },
  { id: 2, categoryName: '조회순', value: 'view' },
  { id: 3, categoryName: '좋아요순', value: 'like' },
];

function PostsPage() {
  const { data, sortBy, categoryName } = useLoaderData();
  const navigate = useNavigate();

  const { noticeBoards, boards, pageInfo, categoryList } = data;

  categoryList.unshift({ id: 0, categoryName: '전체', parentId: null });

  const [currentSortBy, setCurrentSortBy] = useState(sortArr[0]);
  const [currentCategoryName, setCurrentCategoryName] = useState(
    categoryList[0],
  );

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
    <PostsPageLayout>
      <StyledH2>커뮤니티</StyledH2>
      <FilterButtonContainer>
        <Button type="button" text="작성하기" onClick={goToAdd} />
        <FilterContainer>
          <DropDownContainer>
            <FilterName>카테고리</FilterName>
            <Dropdown
              options={categoryList}
              defaultValue={categoryList[0]}
              onClick={onClickCategoryName}
              height="37px"
              selectedValue={
                categoryList.filter(obj => obj.categoryName === categoryName)[0]
              }
            />
          </DropDownContainer>
          <DropDownContainer>
            <FilterName>정렬</FilterName>
            <Dropdown
              options={sortArr}
              defaultValue={currentSortBy}
              onClick={onClickSortBy}
              height="37px"
              selectedValue={sortArr.filter(obj => obj.value === sortBy)[0]}
            />
          </DropDownContainer>
        </FilterContainer>
      </FilterButtonContainer>
      {noticeBoards.map(notice => (
        <ArticleCard
          {...notice}
          userProfile="https://muduckbucket.s3.ap-northeast-2.amazonaws.com/assets/adminPic.JPG"
          width="100%"
          key={notice.id}
          type="notice"
          backgroundColor="var(--main-001)"
        />
      ))}
      {boards.map(board => (
        <ArticleCard
          {...board}
          width="100%"
          key={board.id}
          type="post"
          backgroundColor="var(--main-001)"
        />
      ))}
      <Paging
        setPage={setPage}
        activePage={pageInfo.page}
        itemsCount={pageInfo.size}
        totalItemCount={pageInfo.totalElements}
        pageRange={pageInfo.totalPages}
      />
    </PostsPageLayout>
  );
}

const PostsPageLayout = styled.main`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;
  @media screen and (max-width: 1024px) {
    width: 90%;
  }
`;

const DropDownContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  align-items: center;
`;

const StyledH2 = styled.h2`
  margin-top: 40px;
  font-size: var(--font-size-xxl);
  margin-bottom: 30px;
  font-weight: bold;
`;

const FilterButtonContainer = styled.section`
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 8px;
`;

const FilterContainer = styled.section`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  row-gap: 16px;
  p {
    margin: 0 10px;
    font-size: var(--font-size-sm);
  }
  @media screen and (max-width: 600px) {
    margin-top: 16px;
  }
`;

const FilterName = styled.span`
  font-size: var(--font-size-sm);
  margin: 0 8px;

  @media screen and (max-width: 600px) {
    width: 50px;
  }
`;

export default PostsPage;
