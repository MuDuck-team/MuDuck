import styled from 'styled-components';
import { BsFillChatFill } from 'react-icons/bs';
import { FiHeart } from 'react-icons/fi';
import { IoMdEye } from 'react-icons/io';
import ProfileImg from './ProfileImage/ProfileImg';
import MeatballsMenu from './MeatballsMenu';

function PostInfo({
  profileUrl,
  nickname,
  createdAt,
  viewCount,
  chatCount,
  heartCount,
  category,
}) {
  return (
    <InformationContainer>
      <InformationDetail>
        <ProfileImg src={profileUrl} width="50px" height="50px" type="actor" />
        <InformationWrapper>
          <Nickname>{nickname}</Nickname>
          <PostInformation>
            <InfoText>{createdAt}</InfoText>
            {viewCount && (
              <InfoBox>
                <ViewIcon />
                <InfoText>{viewCount}</InfoText>
              </InfoBox>
            )}
            {chatCount && (
              <InfoBox>
                <ChatIcon />
                <InfoText>{chatCount}</InfoText>
              </InfoBox>
            )}
            {heartCount && (
              <InfoBox>
                <HeartIcon />
                <InfoText>{heartCount}</InfoText>
              </InfoBox>
            )}
            {category && <InfoText>카테고리 | {category}</InfoText>}
          </PostInformation>
        </InformationWrapper>
      </InformationDetail>
      <MeatballsMenu />
    </InformationContainer>
  );
}

const InformationContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const InformationDetail = styled.div`
  display: flex;
  align-items: center;
`;

const InformationWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;

const Nickname = styled.div`
  margin-bottom: 8px;
  font-size: var(--font-size-sm);
  font-weight: bold;
`;

const PostInformation = styled.div`
  display: flex;
`;

const InfoBox = styled.div`
  display: flex;
  align-items: center;
`;

const InfoText = styled.span`
  display: flex;
  align-items: center;
  margin-right: 4px;
  font-size: var(--font-size-xs);
`;

const ViewIcon = styled(IoMdEye)`
  width: 14px;
  height: 14px;
  margin-right: 2px;
`;

const ChatIcon = styled(BsFillChatFill)`
  width: 12px;
  height: 12px;
  margin-right: 2px;
`;

const HeartIcon = styled(FiHeart)`
  width: 12px;
  height: 12px;
  margin-right: 2px;
`;

export default PostInfo;
