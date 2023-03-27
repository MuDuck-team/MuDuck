import styled from 'styled-components';
import { useEffect, useState } from 'react';
import Loading from '../../components/Loading';
import { MyPageCard } from '../../components/Cards';
import Paging from '../../components/Pagination/Pagination';
import customAxios from '../../api/customAxios';

function MyCommentList() {
  const [loading, setLoading] = useState(true);
  const localToken = localStorage.getItem('localToken');

  const [page, setPage] = useState(1);
  const [pageInfo, setPageInfo] = useState([]);
  const [currentItems, setCurrentItems] = useState([]);

  useEffect(() => {
    customAxios({
      method: 'get',
      url: `/my-page/comments?page=${page}&size=10`,
      headers: {
        Authorization: localToken,
      },
    }).then(response => {
      console.log(response);
      setPageInfo(response.data.pageInfo);
      setCurrentItems(response.data.comments);
      setLoading(false);
    });
  }, [page]);

  if (loading) {
    return <Loading />;
  }

  return currentItems.length === 0 ? (
    <InformationText>작성한 댓글이 없습니다</InformationText>
  ) : (
    <>
      {currentItems.map(comment => (
        <MyPageCard
          key={comment.id}
          id={comment.boardId}
          title={comment.body}
          createdAt={comment.createdAt}
          width="100%"
        />
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
  height: 36rem;
  border-radius: 8px;
  font-size: var(--font-size-lg);
  background-color: var(--main-002);
`;

export default MyCommentList;
