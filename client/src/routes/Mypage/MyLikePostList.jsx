import styled from 'styled-components';
import { useEffect, useState } from 'react';
import { MyPageCard } from '../../components/Cards';
import Paging from '../../components/Pagination/Pagination';
import customAxios from '../../api/customAxios';

function MyLikePostList() {
  const localToken = localStorage.getItem('localToken');

  const [page, setPage] = useState(1);
  const [pageInfo, setPageInfo] = useState([]);
  const [currentItems, setCurrentItems] = useState([]);

  useEffect(() => {
    customAxios({
      method: 'get',
      url: `/my-page/liked-boards?page=${page}&size=5`,
      headers: {
        Authorization: localToken,
      },
    }).then(response => {
      setPageInfo(response.data.pageInfo);
      setCurrentItems(response.data.boards);
    });
  }, [page]);

  return currentItems.length === 0 ? (
    <InformationText>좋아요한 글이 없습니다</InformationText>
  ) : (
    <>
      {currentItems.map(post => (
        <MyPageCard key={post.id} {...post} width="100%" />
      ))}
      <Paging
        activePage={page}
        itemsCount={pageInfo.size}
        totalItemCount={pageInfo.totalElements}
        pageRange={pageInfo.totalPages}
        setPage={setPage}
      />
    </>
  );
}

const InformationText = styled.p`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 16rem;

  border-radius: 8px;
  font-size: var(--font-size-lg);
  background-color: var(--main-002);
`;

export default MyLikePostList;
