import { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import { BsFillChatFill } from 'react-icons/bs';
import { AiOutlineHeart } from 'react-icons/ai';
import { IoMdEye } from 'react-icons/io';
import { useRecoilValue } from 'recoil';
import customAxios from '../api/customAxios';
import { userInfo } from '../recoil/userAtom';
import ProfileImg from './ProfileImage/ProfileImg';
import MeatballsMenu from './MeatballsMenu';
import Modal from './Modal/Modal';

function WriterInfo({
  commentId,
  commentStatus,
  memberId,
  profileUrl,
  nickname,
  createdAt,
  viewCount,
  totalComment,
  like,
  category,
  type,
}) {
  const user = useRecoilValue(userInfo);

  const params = useParams();
  const navigate = useNavigate();
  const localToken = localStorage.getItem('localToken');

  const [showModal, setShowModal] = useState(false);

  const handleShowModal = () => {
    setShowModal(!showModal);
  };

  const handleCommentDelete = () => {
    customAxios
      .delete(`/boards/${params.id}/comments/${commentId}`, {
        headers: {
          Authorization: localToken,
        },
      })
      .then(response => {
        if (response.status === 204) {
          navigate('.', { replace: true });
        }
      })
      .catch(error => {
        console.error('Error submitting comment:', error);
      });
  };

  return (
    <InformationContainer>
      <InformationDetail>
        <ProfileImg src={profileUrl} width="50px" height="50px" type="actor" />
        <InformationWrapper>
          <Nickname>{nickname}</Nickname>
          <PostInformation>
            <InfoText>{createdAt}</InfoText>
            {viewCount >= 0 && (
              <InfoBox>
                <ViewIcon />
                <InfoText>{viewCount}</InfoText>
              </InfoBox>
            )}
            {totalComment >= 0 && (
              <InfoBox>
                <ChatIcon />
                <InfoText>{totalComment}</InfoText>
              </InfoBox>
            )}
            {like >= 0 && (
              <InfoBox>
                <HeartIcon />
                <InfoText>{like}</InfoText>
              </InfoBox>
            )}
            {category && <InfoText>카테고리 | {category}</InfoText>}
          </PostInformation>
        </InformationWrapper>
      </InformationDetail>
      {user && type === 'postWriter' && user.id === memberId && (
        <MeatballsMenu />
      )}
      {user && user.id === memberId && commentStatus === '댓글게시' && (
        <>
          <Modal
            showModal={showModal}
            handleCloseModal={handleShowModal}
            title="댓글 삭제"
            content="정말 댓글을 삭제하시겠습니까?"
            yesCallback={handleCommentDelete}
          />
          <DeleteButton onClick={handleShowModal}>삭제</DeleteButton>
        </>
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
