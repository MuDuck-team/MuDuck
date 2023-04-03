import styled from 'styled-components';
import { createPortal } from 'react-dom';

function ModalPortal({ onClose, children }) {
  const el = document.getElementById('modal-root');
  return createPortal(
    <Overlay onClick={onClose}>
      <Content onClick={event => event.stopPropagation()}>{children}</Content>
    </Overlay>,
    el,
  );
}

export const Overlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const Content = styled.section`
  background-color: var(--main-002);
  border-radius: 5px;
  border-color: var(--color-borderbox-line);
  padding: 16px;
  width: 300px;
  height: 200px;
  max-width: 80%;
  max-height: 80%;
  overflow: auto;

  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;

  svg {
    align-self: end;
  }

  h3 {
    font-weight: bold;
    font-size: var(--font-size-lg);
  }

  p {
    font-size: var(--font-size-md);
  }

  button {
    outline: none;
    border: none;
    border-radius: 8px;
    width: 60px;
    height: 30px;
    font-weight: 600;
    color: var(--font-color);
  }

  button:first-child {
    background-color: var(--main-003);
  }

  button:last-child {
    background-color: var(--button-color);
  }

  button + button {
    margin-left: 32px;
  }
`;

export default ModalPortal;
