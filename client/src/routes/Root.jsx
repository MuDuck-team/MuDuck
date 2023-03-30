import { useEffect } from 'react';
import styled from 'styled-components';
import { Outlet, useNavigation } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { ToastContainer } from 'react-toastify';

import { userState } from '../recoil/userAtom';
import Header from '../components/Header';
import Footer from '../components/Footer';
import ScrollToTop from '../components/ScrollToTop';
import Loading from '../components/Loading';

function Root() {
  const navigation = useNavigation();
  const [userLoginStatus, setUserLoginStatus] = useRecoilState(userState);

  useEffect(() => {
    const localToken = localStorage.getItem('localToken');
    if (!localToken) {
      setUserLoginStatus(false);
    } else {
      setUserLoginStatus(true);
    }
  }, [userLoginStatus]);

  return (
    <>
      <Header />
      <Container>
        {navigation.state === 'loading' && <Loading />}
        <Outlet />
      </Container>
      <Footer />
      <ScrollToTop />
      <StyledToastContainer
        position="top-right"
        autoClose={2000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="dark"
      />
    </>
  );
}

const Container = styled.div`
  width: 100%;
  max-width: 1120px;
  min-height: 75vh;
  margin: auto;
`;

const StyledToastContainer = styled(ToastContainer)`
  &&&.Toastify__toast-container {
    margin-top: 8rem;
  }

  .Toastify__toast-body {
    white-space: pre-line;
  }
`;

export default Root;
