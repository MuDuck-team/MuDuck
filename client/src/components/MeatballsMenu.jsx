import { useState } from 'react';
import styled from 'styled-components';
import { BsTrashFill } from 'react-icons/bs';
import { HiOutlinePencil } from 'react-icons/hi';
import Modal from './Modal/Modal';

function MeatballsMenu() {
  const [showModal, setShowModal] = useState(false);

  const showModalHandler = () => {
    setShowModal(!showModal);
  };

  return (
    <MenuContainer>
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
    </MenuContainer>
  );
}

const MenuContainer = styled.div`
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
