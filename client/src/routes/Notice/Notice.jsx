import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { IoMdEye, IoMdListBox } from 'react-icons/io';
import ProfileImg from '../../components/ProfileImg';
import MeatballsMenu from '../../components/MeatballsMenu';

function NoticePage() {
  const initImage = 'https://cataas.com/cat/pbrosoqOlUUtR5XJ';

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
        <InformationContainer>
          <InformationDetail>
            <ProfileImg
              src={initImage}
              width="50px"
              height="50px"
              type="actor"
            />
            <InformationWrapper>
              <RoleText>관리자</RoleText>
              <NoticeInformation>
                <DateText>{notice.lastCreatedAt}</DateText>
                <ViewCount>
                  <ViewIcon />
                  <ViewText>{notice.view}</ViewText>
                </ViewCount>
              </NoticeInformation>
            </InformationWrapper>
          </InformationDetail>
          <MeatballsMenu />
        </InformationContainer>
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

const InformationContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const InformationWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;

const InformationDetail = styled.div`
  display: flex;
  align-items: center;
`;

const RoleText = styled.div`
  margin-bottom: 8px;
  font-size: var(--font-size-sm);
  font-weight: bold;
`;

const NoticeInformation = styled.div`
  display: flex;
`;

const DateText = styled.span`
  display: flex;
  align-items: center;
  margin-right: 12px;
  font-size: var(--font-size-xs);
`;

const ViewCount = styled.div`
  display: flex;
  align-items: center;
`;

const ViewText = styled.span`
  margin-left: 4px;
  font-size: var(--font-size-xs);
`;

const ViewIcon = styled(IoMdEye)`
  width: 14px;
  height: 14px;
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
