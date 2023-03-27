import { useEffect, useState } from 'react';
import styled from 'styled-components';
import PostData, { CommentData } from './ContentData';
import { MyPageCard } from '../../components/Cards';
import Paging from '../../components/Pagination/Pagination';
import Myinfo from './Myinfo';

function MyPage() {
  const pageSize = 5;
  const pageInfo = {
    page: 1,
    size: pageSize,
    totalElements: PostData.length,
    totalPages: Math.ceil(PostData.length / pageSize),
  };

  const [page, setPage] = useState(pageInfo.page);
  const [currentItems, setCurrentItems] = useState([]);
  const indexOfLastPost = page * pageInfo.size;
  const indexOfFirstPost = indexOfLastPost - pageInfo.size;

  useEffect(() => {
    setCurrentItems(PostData.slice(indexOfFirstPost, indexOfLastPost));
  }, [page, indexOfLastPost, indexOfFirstPost]);

  return (
    <MyPageLayout>
      <Category>마이 페이지</Category>
      <Myinfo />
      <ContentContainer>
        <PostContainer>
          <Title>내가 작성한 글</Title>
          {currentItems.map(post => (
            <MyPageCard key={post.id} width="100%" {...post} />
          ))}
          <Paging
            activePage={page}
            itemsCount={pageInfo.size}
            totalItemCount={pageInfo.totalElements}
            pageRange={pageInfo.totalPages}
            setPage={setPage}
          />
          <Title>내가 스크랩한 글</Title>
          {currentItems.map(post => (
            <MyPageCard key={post.id} width="100%" {...post} />
          ))}
          <Paging
            activePage={page}
            itemsCount={pageInfo.size}
            totalItemCount={pageInfo.totalElements}
            pageRange={pageInfo.totalPages}
            setPage={setPage}
          />
        </PostContainer>
        <CommentContainer>
          <Title>내가 작성한 댓글</Title>
          {CommentData.map(post => (
            <MyPageCard key={post.id} width="100%" {...post} />
          ))}
          <Paging
            activePage={page}
            itemsCount={pageInfo.size}
            totalItemCount={pageInfo.totalElements}
            pageRange={pageInfo.totalPages}
            setPage={setPage}
          />
        </CommentContainer>
      </ContentContainer>
    </MyPageLayout>
  );
}

const MyPageLayout = styled.main`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;

  @media screen and (max-width: 1024px) {
    width: 90%;
  }
`;

const Category = styled.h2`
  font-size: var(--font-size-xxl);
  font-weight: bold;
  margin-top: 4rem;
  margin-bottom: 2.4rem;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 1.6rem 2%;
  width: 100%;
  height: 100%;
`;

const PostContainer = styled.section`
  width: 49%;

  @media screen and (max-width: 768px) {
    width: 100%;
  }
`;

const CommentContainer = styled.section`
  width: 49%;
  height: 100%;

  @media screen and (max-width: 768px) {
    width: 100%;
  }
`;

const Title = styled.h3`
  font-size: var(--font-size-md);
  margin-bottom: 8px;
`;

export default MyPage;
