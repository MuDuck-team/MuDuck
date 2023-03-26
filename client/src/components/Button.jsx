import styled from 'styled-components';

/** - 버튼의 디폴트값 : bgColor 주황색, width는 75px, height 30px 입니다.
 - 변경하여 사용하기 : Button 태그에 프롭스로 text="확인" bgColor="#d3233a" 등으로 내려주세요 */
function Button({
  type,
  text,
  border,
  bgColor,
  textColor,
  hover,
  active,
  height,
  width,
  fontSize,
  fontWeight,
  padding,
  onClick,
  ...rest
}) {
  return (
    <GlobalButton
      onClick={onClick}
      type={type}
      border={border}
      bgColor={bgColor}
      textColor={textColor}
      hover={hover}
      active={active}
      height={height}
      width={width}
      fontSize={fontSize}
      padding={padding}
      fontWeight={fontWeight}
      {...rest}
    >
      {text}
    </GlobalButton>
  );
}

const GlobalButton = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  outline: none;
  :focus {
    outline: none;
  }
  height: ${({ height }) => height || '30px'};
  width: ${({ width }) => width || '75px'};
  font-size: ${({ fontSize }) => fontSize || 'var(--font-size-md)'};
  font-weight: ${({ fontWeight }) => fontWeight || 600};
  padding: ${({ padding }) => padding || 0};
  border: ${({ border }) => border || 'none'};
  margin: ${({ margin }) => margin || 'none'};
  border-radius: 8px;
  background-color: ${({ bgColor }) => bgColor || 'var(--button-color)'};
  color: ${({ textColor }) => textColor || 'var(--font-color)'};
  :hover {
    background-color: ${({ hover }) => hover || '#EA7726'};
  }
  :active {
    background-color: ${({ active }) => active || '#EA6D1C'};
  }
  :disabled {
    cursor: default;
    opacity: 0.5;
    background: var(--button-color);
  }
`;

export default Button;
