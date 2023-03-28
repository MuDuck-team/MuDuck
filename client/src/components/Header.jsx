import { useState, useEffect } from 'react';
import styled from 'styled-components';
import { Link, useNavigate } from 'react-router-dom';
import { FaBars } from 'react-icons/fa';
import { AiOutlineClose } from 'react-icons/ai';
import { useRecoilState } from 'recoil';
import { userInfo, userState } from '../recoil/userAtom';
import customAxios from '../api/customAxios';
import { ReactComponent as Logo } from '../assets/logo.svg';
import ProfileImg from './ProfileImage/ProfileImg';
import Button from './Button';

function Header() {
  const [isShow, setIsShow] = useState(false);
  const [user, setUser] = useRecoilState(userInfo);
  const [userLoginStatus, setUserLoginStatus] = useRecoilState(userState);

  const navigate = useNavigate();

  useEffect(() => {
    const localToken = localStorage.getItem('localToken');
    if (!localToken) {
      setUserLoginStatus(false);
    } else {
      setUserLoginStatus(true);
    }
  }, [userLoginStatus]);

  const toggleHandler = () => {
    setIsShow(!isShow);
  };

  const tapCloseHander = () => {
    setIsShow(false);
  };

  const LogoutHandler = () => {
    navigate('/');
    setUser(null);
    setUserLoginStatus(false);
    localStorage.removeItem('localToken');
    customAxios.post('/logout');
  };

  return (
    <GNB>
      <Logobox>
        <Link to="/">
          <MuduckLogo />
        </Link>
      </Logobox>
      <NavTab isShow={isShow}>
        <TabLink to="/plays" onClick={tapCloseHander}>
          뮤지컬
        </TabLink>
        <TabLink to="/nearby/1" onClick={tapCloseHander}>
          주변시설
        </TabLink>
        <TabLink to="/posts" onClick={tapCloseHander}>
          커뮤니티
        </TabLink>
        <TabLink to="/notices" onClick={tapCloseHander}>
          공지사항
        </TabLink>
      </NavTab>
      <SignInOrUser isShow={isShow}>
        {userLoginStatus ? (
          <>
            {isShow ? (
              <TabLink to="/mypage" onClick={tapCloseHander}>
                마이페이지
              </TabLink>
            ) : (
              <ProfileImg
                src={user.profileImageUrl}
                onClick={() => {
                  navigate('/mypage');
                }}
                width="30px"
                height="30px"
              />
            )}

            <LogoutBtn
              text="Logout"
              onClick={LogoutHandler}
              bgColor="transparent"
            />
          </>
        ) : (
          <TabLink to="/login" onClick={tapCloseHander}>
            SignIn
          </TabLink>
        )}
      </SignInOrUser>
      <MenuIcon onClick={toggleHandler}>
        {isShow ? <AiOutlineClose /> : <FaBars />}
      </MenuIcon>
    </GNB>
  );
}

const GNB = styled.header`
  width: 100vw;
  height: fit-content;
  background-color: var(--main-001);
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: sticky;
  padding-top: 16px;
  top: -16px;
  z-index: 999;
  padding-left: 20%;
  padding-right: 20%;

  @media screen and (max-width: 1023px) {
    padding-left: 10%;
    padding-right: 10%;
  }

  @media screen and (max-width: 768px) {
    flex-direction: column;
    align-items: flex-start;
    padding-left: 0%;
    padding-right: 0%;
  }
`;

const Logobox = styled.div`
  width: fit-content;
  height: 40px;
  flex: 1;

  @media screen and (max-width: 768px) {
    width: 100%;
    padding: 16px;
  }
`;

const MuduckLogo = styled(Logo)`
  width: 173px;
  height: 33px;
  margin-right: 32px;
`;

const NavTab = styled.nav`
  width: 300px;
  height: 40px;
  background-color: var(--main-001);
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex: 2;
  & a:hover {
    background-color: rgba(255, 255, 255, 0.1);
  }

  @media screen and (max-width: 768px) {
    display: ${props => (props.isShow ? 'block' : 'none')};
    width: 100%;
    flex-direction: column;
  }
`;

const TabLink = styled(Link)`
  width: 100%;
  height: 30px;
  font-size: var(--font-size-md);
  padding-top: 16px;
  padding-bottom: 16px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 8px;

  @media screen and (max-width: 768px) {
    width: 100%;
    height: 45px;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    padding: 16px;
    box-sizing: border-box;
  }
`;

const SignInOrUser = styled.div`
  width: 100%;
  height: 30px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  flex: 1;

  @media screen and (max-width: 768px) {
    display: ${props => (props.isShow ? 'block' : 'none')};
    width: 100%;
    justify-content: flex-start;
    & a:hover {
      background-color: rgba(255, 255, 255, 0.1);
    }
  }
`;

const MenuIcon = styled.a`
  display: none;
  position: absolute;
  right: 32px;
  top: 32px;
  font-size: 16px;
  color: white;

  @media screen and (max-width: 768px) {
    display: block;
    padding-top: 8px;
  }
`;

const LogoutBtn = styled(Button)`
  background-color: transparent;
  font-weight: 500;
  height: 40px;
  padding: 10px;
  &:hover {
    background-color: rgba(255, 255, 255, 0.1);
  }

  @media screen and (max-width: 768px) {
    width: 100%;
    height: 45px;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    padding: 16px;
  }
`;

export default Header;
