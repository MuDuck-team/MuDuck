import { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import { BsThreeDots, BsTrashFill } from 'react-icons/bs';
import { HiOutlinePencil } from 'react-icons/hi';
import customAxios from '../api/customAxios';
import Modal from './Modal/Modal';

function MeatballsMenu() {
  const params = useParams();
  const navigate = useNavigate();
  const localToken = localStorage.getItem('localToken');

  const [isEdit, setIsEdit] = useState(false);
  const [showModal, setShowModal] = useState(false);

  const onEditHandler = () => {
    setIsEdit(!isEdit);
  };

  const showModalHandler = () => {
    setShowModal(!showModal);
  };

  const handlePostEdit = () => {
    navigate(`/post/edit/${params.id}`);
  };

  const handlePostDelete = () => {
    customAxios
      .delete(`/boards/${params.id}`, {
        headers: {
          Authorization: localToken,
        },
      })
      .then(response => {
        if (response.status === 204) navigate('/posts');
      })
      .catch(error => {
        console.error('Error submitting comment:', error);
      });
  };

  return (
    <MenuContainer>
      <MeatballsButton onClick={onEditHandler}>
        <MenuIcon />
      </MeatballsButton>
      {isEdit && (
        <MenuWrapper>
          <MenuButton onClick={handlePostEdit}>
            <EditIcon />
            수정
          </MenuButton>
          <MenuButton onClick={showModalHandler}>
            <DeleteIcon />
            삭제
          </MenuButton>
          <Modal
            showModal={showModal}
            handleCloseModal={showModalHandler}
            title="게시글 삭제"
            content="정말 게시글을 삭제하시겠습니까?"
            yesCallback={handlePostDelete}
          />
        </MenuWrapper>
      )}
    </MenuContainer>
  );
}

const MenuContainer = styled.div`
  display: flex;
  position: relative;
`;

const MeatballsButton = styled.button`
  padding: 0;
  border: none;
  background: none;
`;

const MenuIcon = styled(BsThreeDots)`
  width: 20px;
  height: 20px;
  color: var(--font-color);
  cursor: pointer;
`;

const MenuWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  position: absolute;
  top: 32px;
  right: 0;
  align-items: center;
  width: 100px;
  height: 80px;
  border-radius: 8px;
  background-color: #2e2e2e;
`;

const MenuButton = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 80px;
  height: 30px;
  padding: 0;
  background: none;
  border: none;
  color: var(--font-color);
  font-size: var(--font-size-md);
  &:first-child {
    border-bottom: 1px solid var(--line-color);
  }
`;

const EditIcon = styled(HiOutlinePencil)`
  width: 14px;
  height: 14px;
  margin-right: 8px;
`;

const DeleteIcon = styled(BsTrashFill)`
  width: 14px;
  height: 14px;
  margin-right: 8px;
`;

export default MeatballsMenu;
