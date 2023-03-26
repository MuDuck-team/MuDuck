import styled from 'styled-components';
import { FaRegSadTear } from 'react-icons/fa';
import { Link } from 'react-router-dom';

function PlayNotFound() {
  return (
    <Container>
      <FaRegSadTear size="100" />
      <h1>404 Page Not Found</h1>
      <h1>요청하신 작품을 찾을 수 없습니다.</h1>
      <Link to="/plays">다른 작품 찾으러가기</Link>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  height: 80vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  > h1 {
    font-size: var(--font-size-xl);
    margin-top: 2rem;
  }

  > a {
    margin-top: 2rem;
    font-size: var(--font-size-sm);
    color: var(--border-color);
  }
`;

export default PlayNotFound;
