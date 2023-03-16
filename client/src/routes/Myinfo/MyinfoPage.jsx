import { useState } from 'react';
// import { useRecoilValue } from 'recoil';
import styled from 'styled-components';
// import userInfo from '../../recoil/userAtom';
import { ReactComponent as Logo } from '../../assets/logo.svg';
import { StyledInput } from '../../components/Input';
import ProfileImg from '../../components/ProfileImg';

function MyinfoPage() {
  // const user = useRecoilValue(userInfo);

  const [nickname, setNickname] = useState('');
  // const [Image, setImage] = useState('../../assets/DefaultProfile.png');

  const handleChangeInput = event => {
    setNickname(event.target.value);
  };

  return (
    <SettingPageConainer>
      <MuduckLogo />
      <TextParagraph>KAKAO 계정을 이용한 회원가입</TextParagraph>
      <SettingForm>
        <InputLabel htmlFor="nickname">닉네임</InputLabel>
        <FlexibleInput
          type="text"
          name="nickname"
          value={nickname}
          onChange={handleChangeInput}
          id="nickname"
          placeholder="  알파벳,한글,숫자 20자 이하로 입력해주세요"
          width="550px"
          height="30px"
        />

        <InputLabel htmlFor="nickname">프로필 이미지 선택</InputLabel>
        <ProfileWrapper>
          <ProfileSelectionCard>
            <ProfileImg
              margin="1.6rem"
              src="https://cphoto.asiae.co.kr/listimglink/1/2021050711371325414_1620355033.jpg"
            />
            kakao 프로필로 계속하기
          </ProfileSelectionCard>
          <ProfileSelectionCard>이미지 업로드</ProfileSelectionCard>
        </ProfileWrapper>
      </SettingForm>
    </SettingPageConainer>
  );
}

const SettingPageConainer = styled.div`
  height: 100%;
  margin-top: 40px;
  /* position: relative;
  top: 10vh; */
  display: flex;
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

const TextParagraph = styled.p`
  font-size: var(--font-size-xl);
  font-weight: 700;
  margin-top: 8px;
  margin-bottom: 2rem;
`;

const SettingForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: left;
  width: 800px;

  & label:nth-child(1) {
    position: relative;
    left: -30%;

    @media screen and (max-width: 768px) {
      left: 0;
    }
  }
`;

const InputLabel = styled.label`
  font-size: var(--font-size-lg);
  font-weight: 500;
  margin-top: 3rem;
  margin-bottom: 1rem;
  position: relative;
  left: -24%;

  @media screen and (max-width: 768px) {
    left: 0;
  }
`;

const FlexibleInput = styled(StyledInput)`
  ::placeholder {
    font-size: 90%;
  }

  @media screen and (max-width: 768px) {
    width: 50vw;
    ::placeholder {
      font-size: 80%;
    }
  }
`;

const ProfileWrapper = styled.div`
  width: 50vw;
  display: flex;
  justify-content: center;
  align-items: center;

  @media screen and (max-width: 768px) {
    width: 50%;
    flex-wrap: wrap;
  }
`;

const ProfileSelectionCard = styled.div`
  width: 260px;
  height: 180px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border-radius: 8px;
  margin: 1rem;
  background-color: rgba(255, 255, 255, 0.05);
  font-size: var(--font-size-sm);
  color: var(--border-color);
`;

export default MyinfoPage;
