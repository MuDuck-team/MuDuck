import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { HiOutlinePencilAlt } from 'react-icons/hi';
import { useRecoilState } from 'recoil';
import { userInfo } from '../../recoil/userAtom';
import customAxios from '../../api/customAxios';
import uploadS3 from '../../components/ProfileImage/ProfileUploader';
import { StyledInput } from '../../components/Input';
import Button from '../../components/Button';
import ProfileImgSetter from '../../components/ProfileImage/ProfileImgSetter';

function Myinfo() {
  const navigate = useNavigate();
  const localToken = localStorage.getItem('localToken');
  const [user, setUserInfo] = useRecoilState(userInfo);

  const [nickname, setNickname] = useState('');
  const [uploadSrc, setUploadSrc] = useState(null);

  const [isEdit, setIsEdit] = useState(false);

  const onEditHandler = () => {
    setIsEdit(!isEdit);
  };

  const handleProfileChange = async event => {
    event.preventDefault();
    const resultUrl = await uploadS3(uploadSrc);
    customAxios({
      method: 'patch',
      url: `/members/${user.id}`,
      headers: {
        Authorization: localToken,
      },
      data: { profileImageUrl: resultUrl },
    })
      .then(res => {
        const result = res.data;
        setUploadSrc(null);
        setUserInfo(prevUserInfo => ({ ...prevUserInfo, ...result }));
      })
      .then(() => {
        navigate('.');
      });
  };

  const handleChangeNickname = event => {
    setNickname(event.target.value);
  };

  const handleValidateNickname = () => {
    const nickRegEx = /^[가-힣a-z0-9_-]{2,20}$/;
    if (!nickRegEx.test(nickname)) {
      alert(
        '닉네임은 2자이상 20자이하, 한글,영문 대소문자, 숫자만 사용할 수 있습니다.',
      );
    }
  };

  const handleNicknameChange = async event => {
    event.preventDefault();
    customAxios({
      method: 'patch',
      url: `/members/${user.id}`,
      headers: {
        Authorization: localToken,
      },
      data: { nickname },
    })
      .then(res => {
        const result = res.data;
        setUserInfo(prevUserInfo => ({ ...prevUserInfo, ...result }));
        setIsEdit(false);
      })
      .then(() => {
        navigate('.');
      });
  };

  return (
    <MyInfoContainer>
      <ProfileContainer>
        <ProfileImgSetter
          defualtPhotoUrl={user.profileImageUrl}
          uploadSrc={uploadSrc}
          setUploadSrc={setUploadSrc}
        />
        {uploadSrc ? (
          <Button
            bgColor="transparent"
            text="변경사항 저장"
            width="fit-content"
            padding="1rem"
            fontSize="1.2rem"
            fontWeight="400"
            textColor="var(--button-color)"
            hover="transparent"
            active="var(--main-003)"
            onClick={handleProfileChange}
          />
        ) : null}
      </ProfileContainer>
      {isEdit ? (
        <EditNicknameBox>
          <ValidationCheck>
            <EditNickname>닉네임 변경</EditNickname>
            <StyledInput
              width="260px"
              height="30px"
              nickname={nickname}
              onChange={handleChangeNickname}
              onBlur={handleValidateNickname}
            />
          </ValidationCheck>
          <ButtonContainer>
            <Button
              text="취소"
              bgColor="var(--main-003)"
              hover="var(--main-002)"
              active="var(--main-002)"
              onClick={onEditHandler}
            />
            <Button text="저장" onClick={handleNicknameChange} />
          </ButtonContainer>
        </EditNicknameBox>
      ) : (
        <EditNicknameBox>
          <Nickname>{user.nickname}님</Nickname>
          <EditButton type="button" onClick={onEditHandler}>
            <EditIcon />
          </EditButton>
        </EditNicknameBox>
      )}
    </MyInfoContainer>
  );
}

const MyInfoContainer = styled.div`
  display: flex;
  margin-bottom: 1.6rem;
`;

const ProfileContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const EditNicknameBox = styled.div`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 1rem;
`;

const Nickname = styled.div`
  font-size: var(--font-size-xl);
`;

const ValidationCheck = styled.div``;

const EditNickname = styled.div`
  margin-bottom: 8px;
  font-size: var(--font-size-md);
`;

const ButtonContainer = styled.div`
  display: flex;
  button + button {
    margin-left: 8px;
  }
`;

const EditButton = styled.button`
  padding: 0;
  margin-left: 4px;
  border: none;
  background: none;
`;

const EditIcon = styled(HiOutlinePencilAlt)`
  width: 25px;
  height: 25px;
  color: var(--font-color);
  cursor: pointer;
`;

export default Myinfo;
