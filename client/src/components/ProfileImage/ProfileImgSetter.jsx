import { useState, useRef } from 'react';
import styled from 'styled-components';

//  ProfileSubmitSrc

function ProfileImgSetter({ uploadSrc, setUploadSrc, defualtPhotoUrl }) {
  // const defualtPhotoUrl =
  //   'https://cdn.pixabay.com/photo/2022/02/08/02/52/image-7000639_1280.png';
  //  프리뷰용 state
  const [image, setImage] = useState(`${defualtPhotoUrl}`);
  const imageRef = useRef(null);

  const onChange = event => {
    const file = event.target.files[0];

    if (!file) {
      window.alert('프로필 사진을 업로드해 주세요');
      setImage(defualtPhotoUrl);
      return;
    }

    if (file) {
      const fileExt = file.name.split('.').pop();
      if (!['jpeg', 'png', 'jpg', 'JPG', 'PNG', 'JPEG'].includes(fileExt)) {
        window.alert('jpeg, png, jpg 파일만 업로드가 가능합니다.');
        return;
      }
      setImage(file);
      setUploadSrc(file);
    } else {
      setImage(defualtPhotoUrl);
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
          imageRef.current.src = defualtPhotoUrl;
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
  object-fit: cover;
`;

const FileInput = styled.input`
  display: none;
`;

export default ProfileImgSetter;
