import { useState } from 'react';
import styled from 'styled-components';
import { BsThreeDots, BsTrashFill } from 'react-icons/bs';
import { HiOutlinePencil } from 'react-icons/hi';
import Modal from './Modal/Modal';

function MeatballsMenu() {
  const [isEdit, setIsEdit] = useState(false);
  const [showModal, setShowModal] = useState(false);

  const onEditHandler = () => {
    setIsEdit(!isEdit);
  };

  const showModalHandler = () => {
    setShowModal(!showModal);
  };

  return (
    <MenuContainer>
      <MeatballsButton onClick={onEditHandler}>
        <MenuIcon />
      </MeatballsButton>
      {isEdit && (
        <MenuWrapper>
          <MenuButton>
            <EditIcon />
            수정하기
          </MenuButton>
          <MenuButton onClick={showModalHandler}>
            <DeleteIcon />
            삭제하기
          </MenuButton>
          <Modal
            showModal={showModal}
            handleCloseModal={showModalHandler}
            title="게시글 삭제"
            content="정말 게시글을 삭제하시겠습니까?"
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
  top: 24px;
  right: 0;
  align-items: center;
  width: 100px;
  height: 80px;
  background-color: var(--main-003);
  border-radius: 8px;
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
  margin-right: 4px;
`;

const DeleteIcon = styled(BsTrashFill)`
  width: 14px;
  height: 14px;
  margin-right: 4px;
`;

export default MeatballsMenu;
