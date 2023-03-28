import { Link, useParams } from 'react-router-dom';
import styled from 'styled-components';
import { IoMdListBox } from 'react-icons/io';
import WriterInfo from '../../components/WriterInfo';

const dummyData = [
  {
    id: 1,
    profileUrl:
      'https://muduckbucket.s3.ap-northeast-2.amazonaws.com/assets/adminPic.JPG',
    nickname: '관리자',
    lastCreatedAt: '약 9시간 전',
    title: '로그인 시 공지사항',
    body: '프로필을 변경하고 싶을 경우 마이페이지를 이용해주세요!',
  },
  {
    id: 2,
    profileUrl:
      'https://muduckbucket.s3.ap-northeast-2.amazonaws.com/assets/adminPic.JPG',
    nickname: '관리자',
    lastCreatedAt: '약 9시간 전',
    title: '커뮤니티 이용 공지사항',
    body: 'Muduck은 뮤지컬 덕후들을 위한 커뮤니티 서비스입니다. 자유롭게 이용해주세요!',
  },
];

function NoticePage() {
  const params = useParams();
  const notice = dummyData[params.id - 1];

  return (
    <NoticePageLayout>
      <Category>공지사항</Category>
      <ContentContainer>
        <WriterInfo
          profileUrl={notice.profileUrl}
          nickname={notice.nickname}
          createdAt={notice.lastCreatedAt}
          type="postWriter"
        />
        <NoticeTitle>{notice.title}</NoticeTitle>
        <NoticeCotent>{notice.body}</NoticeCotent>
      </ContentContainer>
      <ListLink to="/notices">
        <ListIcon />
        <ListText>목록으로</ListText>
      </ListLink>
    </NoticePageLayout>
  );
}

const NoticePageLayout = styled.main`
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

const ContentContainer = styled.article`
  border-top: 1px solid var(--border-color);
`;

const NoticeTitle = styled.h3`
  margin-top: 3.2rem;
  margin-bottom: 2.4rem;
  font-size: var(--font-size-xl);
  font-weight: bold;
`;

const NoticeCotent = styled.p`
  min-height: 400px;
  font-size: var(--font-size-md);
  line-height: 2em;
`;

const ListLink = styled(Link)`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-top: 1.6rem;
  margin-bottom: 4rem;
`;

const ListIcon = styled(IoMdListBox)`
  width: 20px;
  height: 20px;
  cursor: pointer;
`;

const ListText = styled.span`
  margin-left: 4px;
  font-size: var(--font-size-sm);
`;

export default NoticePage;
