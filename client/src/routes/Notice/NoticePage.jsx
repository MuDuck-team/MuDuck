import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { IoMdListBox } from 'react-icons/io';
import WriterInfo from '../../components/WriterInfo';

function NoticePage() {
  // const profileUrl = 'https://cataas.com/cat/pbrosoqOlUUtR5XJ';
  const profileUrl =
    'https://muduckbucket.s3.ap-northeast-2.amazonaws.com/assets/adminPic.JPG';
  const nickname = '관리자';
  const notice = {
    id: 1,
    lastCreatedAt: '2023.03.13',
    title: '오둥이 관람 규칙',
    body: '오둥이는 귀엽습니다.',
    view: 30,
  };

  return (
    <NoticePageLayout>
      <Category>공지사항</Category>
      <ContentContainer>
        <WriterInfo
          profileUrl={profileUrl}
          nickname={nickname}
          createdAt={notice.lastCreatedAt}
          viewCount={notice.view}
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
