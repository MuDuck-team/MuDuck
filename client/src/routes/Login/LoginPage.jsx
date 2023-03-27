import { useEffect } from 'react';
import { useSetRecoilState } from 'recoil';
import styled from 'styled-components';
import { userInfo } from '../../recoil/userAtom';
import { ReactComponent as Logo } from '../../assets/logo.svg';
import KakaoLoginBtn from '../../components/KakaoLoginBtn';

function LoginPage() {
  const accessToken =
    'Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IlJPTEVfVVNFUiIsInVzZXJuYW1lIjoiam95eUBrYWthby5jb20iLCJzdWIiOiJqb3l5QGtha2FvLmNvbSIsImlhdCI6MTY3OTkyNTY3NSwiZXhwIjoxNjgwMDEyMDc1fQ.5U0xfXe3KfrDnTM92gffIcirNjmaKQIpskIzsnEgPQA';
  localStorage.setItem('localToken', accessToken);

  const setUser = useSetRecoilState(userInfo);
  const userData = {
    id: 5,
    nickname: '이승연',
    profileImageUrl:
      'https://muduckbucket.s3.ap-northeast-2.amazonaws.com/profile/164161ac-ad0f-4a17-9de3-550134f977bf',
    role: 'ROLE_USER',
  };

  useEffect(() => {
    setUser(user => ({ ...user, ...userData }));
  }, []);

  return (
    <LoginPageConainer>
      <MuduckLogo />
      <TextArea>
        <TextParagraph>SIGN IN</TextParagraph>
        <TextParagraph>안녕하세요</TextParagraph>
        <TextParagraph>뮤지컬 커뮤니티</TextParagraph>
        <PointText>MuDcuk</PointText>
        <PointText>입니다.</PointText>
      </TextArea>
      <KakaoLoginBtn isTypeLogin="login" />
    </LoginPageConainer>
  );
}

const LoginPageConainer = styled.div`
  display: flex;
  height: 100%;
  position: relative;
  top: 20vh;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  @media screen and (max-width: 768px) {
    width: 100%;
    padding: 16px;
  }
`;

const MuduckLogo = styled(Logo)`
  width: 173px;
  height: 33px;
  margin-bottom: 32px;
`;

const TextArea = styled.div`
  height: fit-content;

  & p:nth-child(1) {
    font-size: var(--font-size-lg);
    margin-bottom: 64px;
    text-align: center;
  }

  & :last-child {
    color: var(--font-color);
  }
`;

const TextParagraph = styled.p`
  font-size: var(--font-size-xl);
  font-weight: 700;
  margin: 8px;
`;

const PointText = styled.span`
  font-size: var(--font-size-xl);
  font-weight: 700;
  color: var(--button-color);
  margin: 8px 2px 0px 8px;
`;

export default LoginPage;
