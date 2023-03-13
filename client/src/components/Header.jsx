import { useState } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { FaBars } from 'react-icons/fa';
import { AiOutlineClose } from 'react-icons/ai';
import { useRecoilValue } from 'recoil';
import userInfo from '../recoil/userAtom';
import { ReactComponent as Logo } from '../assets/logo.svg';

function Header() {
  const [isShow, setIsShow] = useState(false);

  //* 유저정보 가져오기
  const user = useRecoilValue(userInfo);

  const toggleHandler = () => {
    setIsShow(!isShow);
  };

  return (
    <GNB>
      <Logobox>
        <Link to="/">
          <MuduckLogo />
        </Link>
      </Logobox>
      <NavTab isShow={isShow}>
        <TabLink to="/plays">뮤지컬</TabLink>
        <TabLink to="/nearby">주변시설</TabLink>
        <TabLink to="/posts">커뮤니티</TabLink>
        <TabLink to="/notices">공지사항</TabLink>
      </NavTab>
      <SignInOrUser isShow={isShow}>
        {user ? (
          <ProfileArea>프로필</ProfileArea>
        ) : (
          <TabLink to="/login">SignIn</TabLink>
        )}
      </SignInOrUser>
      <MenuIcon onClick={toggleHandler}>
        {isShow ? <AiOutlineClose /> : <FaBars />}
      </MenuIcon>
    </GNB>
  );
}

const GNB = styled.nav`
  width: 100vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: sticky;
  padding-top: 8px;
  top: -8px;
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
  height: 30px;
  flex: 1;

  @media screen and (max-width: 768px) {
    width: 100%;
    padding-left: 1rem;
  }
`;

const MuduckLogo = styled(Logo)`
  width: 100px;
  height: 30px;
  margin-right: 32px;
`;

const NavTab = styled.ul`
  width: 300px;
  height: 30px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex: 2;

  @media screen and (max-width: 768px) {
    display: ${props => (props.isShow ? 'block' : 'none')};
    width: 100%;
    flex-direction: column;
    & a:hover {
      background-color: rgba(255, 255, 255, 0.1);
    }
  }
`;

const TabLink = styled(Link)`
  width: 100%;
  height: 30px;
  display: flex;
  justify-content: center;
  align-items: center;

  @media screen and (max-width: 768px) {
    width: 100%;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    padding: 8px 8px;
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

const ProfileArea = styled.div`
  width: 30px;
  height: 30px;
`;

const MenuIcon = styled.a`
  display: none;
  position: absolute;
  right: 32px;
  top: 16px;
  font-size: 16px;
  color: white;

  @media screen and (max-width: 768px) {
    display: block;
  }
`;

export default Header;
