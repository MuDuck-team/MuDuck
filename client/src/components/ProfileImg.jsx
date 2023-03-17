import styled, { css } from 'styled-components';
import DefaultProfile from '../assets/DefaultProfile.png';

/** 공연정보에서 프로필이미지 사용할때는 type=actor, name=배우이름을 넘겨주기 */
function ProfileImg({
  type,
  name,
  src,
  alt,
  width,
  height,
  borderRadius,
  margin,
  ...rest
}) {
  /** src에서 이미지를 불러오는데 모종의 이유로 실패할 경우, 디폴트 png 이미지로 설정합니다. */
  const handleImgError = e => {
    e.target.src = DefaultProfile;
  };

  return (
    <ProfileContainer>
      <ProfileImage
        src={src}
        type={type}
        alt={alt}
        onError={handleImgError}
        width={width}
        height={height}
        borderRadius={borderRadius}
        margin={margin}
        {...rest}
      />
      {name}
    </ProfileContainer>
  );
}

const ProfileContainer = styled.div`
  width: fit-content;
  height: fit-content;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

/** 공연정보에서 배우프로필용으로 사용할 경우 type="actor" 프롭스를 내려 호버나 액티브 컬러를 지정하지 않습니다.
나머지 유저 프로필이나, 댓글창 프로필에서 사용할 경우 따로 type을 지정하지 않고 사용합니다. */
const ProfileImage = styled.img`
  src: ${({ src }) => src};
  alt: ${({ alt }) => alt || 'Profile Image'};
  height: ${({ height }) => height || '75px'};
  width: ${({ width }) => width || '75px'};
  margin: ${({ margin }) => margin || 'var(--font-size-xs)'};
  border: ${({ border }) => border || 'none'};
  border-radius: ${({ borderRadius }) => borderRadius || '50%'};
  :hover {
    filter: ${({ type }) => (type === 'actor' ? 'none' : 'brightness(0.8)')};
  }
  :active {
    filter: ${({ type }) => (type === 'actor' ? 'none' : 'brightness(0.5)')};
  }
  ${props =>
    props.uploadSrc &&
    css`
      filter: brightness(0.2);
    `}
`;

export default ProfileImg;
