import styled from 'styled-components';
import { ReactComponent as Logo } from '../../assets/logo.svg';
import KakaoLoginBtn from '../../components/KakaoLoginBtn';

function LoginPage() {
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
