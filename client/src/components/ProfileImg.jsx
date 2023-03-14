import styled from 'styled-components';
import DefaultProfile from '../assets/DefaultProfile.png';

/** 추후 배우페이지 확장성을 위해 onClick 속성을 프롭스로 연결해두었습니다 */
function ProfileImg({
  src,
  alt,
  onClick,
  width,
  height,
  borderRadius,
  margin,
  type,
}) {
  /** src에서 이미지를 불러오는데 모종의 이유로 실패할 경우, 디폴트 png 이미지로 설정합니다. */
  const handleImgError = e => {
    e.target.src = DefaultProfile;
  };

  return (
    <ProfileImage
      src={src}
      type={type}
      alt={alt}
      onClick={onClick}
      onError={handleImgError}
      width={width}
      height={height}
      borderRadius={borderRadius}
      margin={margin}
    />
  );
}

/** 공연정보에서 배우프로필용으로 사용할 경우 type="actor" 프롭스를 내려 호버나 액티브 컬러를 지정하지 않습니다.
나머지 유저 프로필이나, 댓글창 프로필에서 사용할 경우 따로 type을 지정하지 않고 사용합니다.*/
const ProfileImage = styled.img`
  src: ${({ src }) => src};
  alt: ${({ alt }) => alt || 'Profile Image'};
  height: ${({ height }) => height || '75px'};
  width: ${({ width }) => width || '75px'};
  margin: ${({ margin }) => margin || 0};
  border: ${({ border }) => border || 'none'};
  border-radius: ${({ borderRadius }) => borderRadius || '50%'};
  :hover {
    filter: ${({ type }) => (type === 'actor' ? 'none' : 'brightness(0.8)')};
  }
  :active {
    filter: ${({ type }) => (type === 'actor' ? 'none' : 'brightness(0.5)')};
  }
`;

export default ProfileImg;
