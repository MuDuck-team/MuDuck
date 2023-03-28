import styled from 'styled-components';
import { Outlet } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';
import ScrollToTop from '../components/ScrollToTop';

function Root() {
  return (
    <>
      <Header />
      <Container>
        <Outlet />
      </Container>
      <Footer />
      <ScrollToTop />
    </>
  );
}

const Container = styled.div`
  width: 100%;
  max-width: 1120px;
  min-height: 75vh;
  margin: auto;
`;

export default Root;
