import { useState } from 'react';
import Modal from './Modal';

export default function ModalExample() {
  // default
  const [showModal, setShowModal] = useState(false);

  // default
  const handleShowModal = () => {
    setShowModal(true);
  };

  // default
  const handleCloseModal = () => {
    setShowModal(false);
  };

  function yesCallback() {
    console.log('yes');
  }

  return (
    <>
      {/* default */}
      <button type="button" onClick={handleShowModal}>
        모달 보이기
      </button>
      <Modal
        showModal={showModal}
        handleCloseModal={handleCloseModal}
        title="로그인 하시겠습니까?"
        content="로그인 하시면 조금 더 편리하게 문의를 남기실 수 있습니다"
        yesCallback={() => yesCallback()}
      />
    </>
  );
}
