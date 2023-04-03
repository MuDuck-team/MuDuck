import { useLoaderData, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { ArticleCard } from '../../components/Cards';
import Paging from '../../components/Pagination/Pagination';

const dummyData = {
  notices: [
    {
      id: 1,
      lastCreatedAt: '약 9시간 전',
      title: '로그인 시 공지사항',
      view: 30,
    },
    {
      id: 2,
      lastCreatedAt: '약 9시간 전',
      title: '커뮤니티 이용 공지사항',
      view: 10,
    },
  ],
  pageInfo: {
    page: 1,
    size: 10,
    totalElements: 2,
    totalPages: 1,
  },
};

function getData({ page }) {
  return { dummyData, page };
}

export async function loader({ request }) {
  const params = new URL(request.url).searchParams;
  const page = params.get('page');
  const { dummyData: obj } = await getData({ page });
  return { obj };
}

function NoticesPage() {
  const { obj } = useLoaderData();
  const navigate = useNavigate();
  const { notices, pageInfo } = obj;

  const setPage = page => {
    navigate(`?page=${page}`);
  };

  return (
    <NoticesPageLayout>
      <StyledH2>공지사항</StyledH2>
      {notices.map(notice => (
        <ArticleCard
          {...notice}
          userProfile="https://muduckbucket.s3.ap-northeast-2.amazonaws.com/assets/adminPic.JPG"
          width="100%"
          key={notice.id}
          backgroundColor="var(--main-001)"
        />
      ))}
      <Paging
        setPage={setPage}
        activePage={pageInfo.page}
        itemsCount={15}
        totalItemCount={pageInfo.totalElements}
        pageRange={5}
      />
    </NoticesPageLayout>
  );
}

const NoticesPageLayout = styled.main`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;
  @media screen and (max-width: 1024px) {
    width: 90%;
  }
`;

const StyledH2 = styled.h2`
  margin-top: 40px;
  font-size: var(--font-size-xxl);
  margin-bottom: 30px;
  font-weight: bold;
`;

export default NoticesPage;
