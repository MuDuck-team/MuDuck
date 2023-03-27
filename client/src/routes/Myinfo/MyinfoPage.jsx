import { useState } from 'react';
import { useRecoilState } from 'recoil';
import styled, { css } from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { BsFillExclamationCircleFill } from 'react-icons/bs';
import customAxios from '../../api/customAxios';
import { userInfo } from '../../recoil/userAtom';
import { ReactComponent as Logo } from '../../assets/logo.svg';
import { StyledInput } from '../../components/Input';
import Button from '../../components/Button';
import ProfileImg from '../../components/ProfileImage/ProfileImg';
import ProfileImgSetter from '../../components/ProfileImage/ProfileImgSetter';
import uploadS3 from '../../components/ProfileImage/ProfileUploader';

function MyinfoPage() {
  const [nickname, setNickname] = useState('');
  const [uploadSrc, setUploadSrc] = useState(null);
  const [user, setUserInfo] = useRecoilState(userInfo);
  const navigate = useNavigate();
  let updatedUserData = { nickname: '', profileImageUrl: '' };

  const handleChangeNickname = event => {
    setNickname(event.target.value);
  };

  const handleClickKakaoProfile = () => {
    setUploadSrc(null);
  };

  const handleValidateNickname = () => {
    const nickRegEx = /^[가-힣a-z0-9_-]{2,20}$/;
    if (!nickRegEx.test(nickname)) {
      alert(
        '닉네임은 2자이상 20자이하, 한글,영문 대소문자, 숫자만 사용할 수 있습니다.',
      );
    }
  };

  const handleSubmit = async event => {
    event.preventDefault();
    const localToken = localStorage.getItem('localToken');

    if (!uploadSrc && !nickname) {
      navigate('/');
    } else if (!uploadSrc) {
      updatedUserData = {
        nickname,
        profileImageUrl: null,
      };

      customAxios({
        method: 'patch',
        url: `/members/${user.id}`,
        headers: {
          Authorization: localToken,
        },
        data: { ...updatedUserData },
      })
        .then(res => {
          const result = res.data;
          setUserInfo(prevUserInfo => ({ ...prevUserInfo, ...result }));
        })
        .then(() => {
          navigate('/');
        });
    } else {
      const resultUrl = await uploadS3(uploadSrc);
      updatedUserData = {
        nickname,
        profileImageUrl: resultUrl,
      };

      customAxios({
        method: 'patch',
        url: `/members/${user.id}`,
        headers: {
          Authorization: localToken,
        },
        data: { ...updatedUserData },
      })
        .then(res => {
          const result = res.data;
          setUserInfo(prevUserInfo => ({ ...prevUserInfo, ...result }));
        })
        .then(() => {
          navigate('/');
        });
    }
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
          onChange={handleChangeNickname}
          onBlur={handleValidateNickname}
          id="nickname"
          placeholder=" 알파벳,한글,숫자 20자 이하로 입력해주세요"
          width="550px"
          height="30px"
        />

        <InputLabel htmlFor="profileAvatar">프로필 이미지 선택</InputLabel>
        <ProfileWrapper>
          <ProfileSelectionCard uploadSrc={uploadSrc}>
            <ProfileImg
              margin="1.6rem"
              src={user.profileImageUrl}
              onClick={handleClickKakaoProfile}
              uploadSrc={uploadSrc}
            />
            kakao 프로필로 계속하기
          </ProfileSelectionCard>
          <ProfileSelectionCard>
            <ProfileImgSetter
              id="profileAvatar"
              uploadSrc={uploadSrc}
              defualtPhotoUrl="https://cdn.pixabay.com/photo/2022/02/08/02/52/image-7000639_1280.png"
              setUploadSrc={setUploadSrc}
            />
            이미지 업로드
          </ProfileSelectionCard>
        </ProfileWrapper>

        <WarningWrapper>
          <WanringText>
            <BsFillExclamationCircleFill color="var(--error-color)" />
            <BoldSpan> 추가안내 </BoldSpan>
          </WanringText>
          <WanringText>
            <BoldSpan>프로필 이미지 변경</BoldSpan>은 회원가입 이후에도
            가능합니다.
          </WanringText>
          <WanringText>
            프로필 이미지를 선택하지 않은 경우
            <BoldSpan> 카카오 이미지 </BoldSpan>로 설정됩니다.
          </WanringText>
        </WarningWrapper>
        <SubmitButton
          type="submit"
          width="545px"
          height="3.5rem"
          margin="1.6rem"
          text="회원가입"
          onClick={handleSubmit}
        />
      </SettingForm>
    </SettingPageConainer>
  );
}

const SettingPageConainer = styled.div`
  height: 100%;
  margin-top: 40px;
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
  padding-left: 1rem;
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

  ${props =>
    props.uploadSrc &&
    css`
      filter: brightness(1);
    `}
`;

const WarningWrapper = styled(ProfileWrapper)`
  flex-direction: column;
  align-items: flex-start;
  padding: 2rem;
  width: 545px;
  background-color: rgba(255, 255, 255, 0.05);
  border-radius: 8px;
  margin: 8px;
  font-size: var(--font-size-sm);
  color: var(--font-color);

  @media screen and (max-width: 768px) {
    width: 260px;
  }
`;

const BoldSpan = styled.span`
  font-size: var(--font-size-xs);
  font-weight: 700;
`;

const WanringText = styled(TextParagraph)`
  font-size: var(--font-size-xs);
  font-weight: 300;
  margin-top: 2px;
  margin-bottom: 8px;

  @media screen and (max-width: 768px) {
    margin-bottom: 1.6rem;
    line-height: 2rem;
    word-break: break-word;
  }
`;

const SubmitButton = styled(Button)`
  @media screen and (max-width: 768px) {
    width: 260px;
  }
`;

export default MyinfoPage;
