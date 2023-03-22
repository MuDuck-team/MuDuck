import styled from 'styled-components';
import { BsFillChatFill } from 'react-icons/bs';
import { AiOutlineHeart } from 'react-icons/ai';
import { IoMdEye } from 'react-icons/io';
import ProfileImg from './ProfileImage/ProfileImg';
import MeatballsMenu from './MeatballsMenu';

function WriterInfo({
  profileUrl,
  nickname,
  createdAt,
  viewCount,
  totalComment,
  boardLike,
  category,
  type,
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
            {totalComment && (
              <InfoBox>
                <ChatIcon />
                <InfoText>{totalComment}</InfoText>
              </InfoBox>
            )}
            {boardLike && (
              <InfoBox>
                <HeartIcon />
                <InfoText>{boardLike}</InfoText>
              </InfoBox>
            )}
            {category && <InfoText>카테고리 | {category}</InfoText>}
          </PostInformation>
        </InformationWrapper>
      </InformationDetail>
      {type === 'postWriter' ? (
        <MeatballsMenu />
      ) : (
        <DeleteButton>삭제</DeleteButton>
      )}
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

const HeartIcon = styled(AiOutlineHeart)`
  width: 14px;
  height: 14px;
  margin-right: 2px;
`;

const DeleteButton = styled.button`
  padding: 0;
  border: none;
  background: none;
  font-size: var(--font-size-xs);
  color: var(--font-color);
  &:hover {
    opacity: 0.8;
  }
`;

export default WriterInfo;