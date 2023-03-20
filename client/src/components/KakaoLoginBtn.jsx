import styled from 'styled-components';
import { Link } from 'react-router-dom';
import kakaoLarge from '../assets/kakaoLarge.png';
import kakaoStart from '../assets/kakaoStart.png';

function KakaoLoginBtn({ isTypeLogin }) {
  const handleKaKaoLogin = () => {
    window.location.href = `${process.env.REACT_APP_SERVER_URL}/oauth2/authorization/{kakao-id}?redirect_url="http://localhost:3000/oauth/redirect`;
    //  서버에 주소로 kakao oauth 로그인 요청을 보내는 창을 띄울 것인지?
    //  window.location.href = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}`;
  };

  return isTypeLogin ? (
    <>
      <KakaoLoginButton onClick={handleKaKaoLogin}>
        <ButtonImage src={kakaoLarge} />
      </KakaoLoginButton>
      <SingInLink to="/signup">카카오로 회원가입하기</SingInLink>
    </>
  ) : (
    <>
      <KakaoLoginButton onClick={handleKaKaoLogin}>
        <ButtonImage src={kakaoStart} />
      </KakaoLoginButton>
      <SingInLink to="/login">이미 계정이 있으신가요?</SingInLink>
    </>
  );
}

const KakaoLoginButton = styled.button`
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 185px;
  height: 45px;
  background-color: transparent;
  border: none;
  margin-top: 3.2rem;
  margin-bottom: 2rem;
  &:hover {
    filter: brightness(0.8);
  }
`;

const ButtonImage = styled.img`
  background-repeat: no-repeat;
  background-size: cover;
  margin: 10px auto;
  width: 185px;
  height: 45px;
`;

const SingInLink = styled(Link)`
  font-size: var(--font-size-xs);
  color: #858585;
`;

export default KakaoLoginBtn;
