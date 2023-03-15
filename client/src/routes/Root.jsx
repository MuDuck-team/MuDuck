import styled from 'styled-components';
import { Outlet } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';

function Root() {
  return (
    <>
      <Header />
      <Container>
        <Outlet />
      </Container>
      <Footer />
    </>
  );
}

const Container = styled.div`
  width: 100%;
  max-width: 1120px;
  min-height: 75vh;
  //min-height 추후변경예정
  margin: auto;
`;

export default Root;
