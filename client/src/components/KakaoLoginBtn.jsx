import styled from 'styled-components';
import { Link } from 'react-router-dom';
import kakaoLarge from '../assets/kakaoLarge.png';
import kakaoStart from '../assets/kakaoStart.png';

function KakaoLoginBtn({ props }) {
  const handleKaKaoLogin = () => {
    window.location.href = `${process.env.REACT_APP_SERVER_URL}/oauth2/authorization/kakao`;
    //  서버에 주소로 kakao oauth 로그인 요청을 보내는 창을 띄울 것인지?
    //  window.location.href = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}`;
    //  카카오 oauth 서버로 리다이렉트 url 에 우리 서버주소를 넣어서 보낼것인지?
    //! window.location 사용시 만약 서버에서 인가 및 인증을 다 끝내고, 우리 서비스 자체 Accees token과 Refresh Token 을 어떻게 받을 수 있을지 잘 가늠이 안된다.
    //! 리다이렉트 될 /login/callback 페이지를 하나 만들어서 서버에게 해당 페이지로 리다이렉트 시켜달라고 해서 거기서 useEffect 훅에 담아 유저정보를 긁어와야하는지?

    //  todo 검색해보니 클라이언트에서 인가코드를 받아서 서버로 넘겨주는 방식이 많던데, 그게맞는지?
    //  todo 어떤 방식으로 구현해야할지 잘 모르겠다.
  };

  return props ? (
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
      <SingInLink to="/login">이미 계정이 있으신가요? </SingInLink>
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
