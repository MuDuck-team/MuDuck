import ModalPortal from './ModalPortal';

function Modal({ title, content, showModal, yesCallback, handleCloseModal }) {
  function onClickYesButton() {
    if (yesCallback) {
      yesCallback();
    }
    handleCloseModal();
  }

  return (
    <div>
      {showModal && (
        <ModalPortal onClose={handleCloseModal}>
          <h3>{title}</h3>
          <p>{content}</p>
          <div>
            <button type="button" name="yes" onClick={onClickYesButton}>
              확인
            </button>
            <button type="button" name="no" onClick={handleCloseModal}>
              취소
            </button>
          </div>
        </ModalPortal>
      )}
    </div>
  );
}

export default Modal;
