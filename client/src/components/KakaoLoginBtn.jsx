import styled from 'styled-components';
import kakaoLarge from '../assets/kakaoLarge.png';

function KakaoLoginBtn() {
  const handleKaKaoLogin = () => {
    window.location.href = `${process.env.REACT_APP_SERVER_URL}/oauth2/authorization/kakao?redirect_url="http://muduckbucket.s3-website.ap-northeast-2.amazonaws.com/oauth/redirect"`;
  };

  return (
    <KakaoLoginButton onClick={handleKaKaoLogin}>
      <ButtonImage src={kakaoLarge} />
    </KakaoLoginButton>
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

export default KakaoLoginBtn;
