import styled from 'styled-components';
import { Outlet, useNavigation } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';
import ScrollToTop from '../components/ScrollToTop';
import Loading from '../components/Loading';

function Root() {
  const navigation = useNavigation();

  return (
    <>
      <Header />
      <Container>
        {navigation.state === 'loading' && <Loading />}
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
