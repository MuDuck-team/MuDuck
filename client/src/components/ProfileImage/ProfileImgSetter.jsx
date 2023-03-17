import { useState, useRef } from 'react';
import styled, { css } from 'styled-components';

//  ProfileSubmitSrc

function ProfileImgSetter({ uploadSrc, setUploadSrc }) {
  //  프리뷰용 state
  const [image, setImage] = useState(
    'https://cdn.pixabay.com/photo/2022/02/08/02/52/image-7000639_1280.png',
  );
  const imageRef = useRef(null);

  const onChange = event => {
    const file = event.target.files[0];

    if (file) {
      const fileExt = file.name.split('.').pop();
      if (!['jpeg', 'png', 'jpg', 'JPG', 'PNG', 'JPEG'].includes(fileExt)) {
        window.alert('jpeg, png, jpg 파일만 업로드가 가능합니다.');
        return;
      }
      setImage(file);
      setUploadSrc(file);
    } else {
      setImage('../assets/DefaultProfile.png');
    }

    if (!file) {
      window.alert('프로필 사진을 업로드해 주세요');
      setImage(
        'https://cdn.pixabay.com/photo/2022/02/08/02/52/image-7000639_1280.png',
      );
    }

    //  FileReader() 객체는 비동기적으로 파일의 내용을 읽어들이는 데 사용
    //  ReadyState = Empty 0, Loding 1, Complete 2
    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    //  Base64 Encode 문자열로 반환

    reader.onload = () => {
      if (reader.readyState === 2) {
        setImage(reader.result);
      }
    };
  };

  return (
    <>
      <Avatar
        src={image}
        onClick={() => imageRef.current.click()}
        onError={() => {
          imageRef.current.src =
            'https://cdn.pixabay.com/photo/2022/02/08/02/52/image-7000639_1280.png';
        }}
        uploadSrc={uploadSrc}
      />
      <FileInput
        type="file"
        accept="image/*"
        name="profile_Img"
        onChange={onChange}
        ref={imageRef}
      />
    </>
  );
}

const Avatar = styled.img`
  margin: 20px;
  width: 75px;
  height: 75px;
  border-radius: 50%;
  filter: brightness(0.8);
  background-color: transparent;

  ${props =>
    !props.uploadSrc &&
    css`
      filter: brightness(0.2);
      transition: ease-in 0.08s;
    `}
`;

const FileInput = styled.input`
  display: none;
`;

export default ProfileImgSetter;
