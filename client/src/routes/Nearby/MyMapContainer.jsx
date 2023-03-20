import { useRef, useState } from 'react';
import styled from 'styled-components';
import MyMap from './MyMap';

function MyMapContainer() {
  const [inputText, setInputText] = useState('');
  const [searchPlace, setSearchPlace] = useState('');
  // 처음에 들어갔을 때 경고창을 막기 위하여 작성
  const countRef = useRef(0);

  const onChange = e => {
    setInputText(e.target.value);
  };

  const handleSubmit = e => {
    e.preventDefault();
    setSearchPlace(inputText);
    setInputText('');
    countRef.current += 1;
  };
  return (
    <StyledMapContainer>
      <StyledForm onSubmit={handleSubmit}>
        <span>키워드 :</span>
        <input type="text" value={inputText} onChange={onChange} size="15" />
        <button type="submit">검색하기</button>
      </StyledForm>
      <MyMap searchPlace={searchPlace} countRef={countRef} />
    </StyledMapContainer>
  );
}

const StyledMapContainer = styled.div`
  position: relative;
  width: 100%;
  height: 540px;
`;

const StyledForm = styled.form`
  display: flex;
  align-items: center;
  justify-content: space-between;
  position: absolute;
  top: 10px;
  left: 0;
  background-color: #313131;
  border-radius: 0 0 2px 2px;
  width: 245px;
  padding: 5px 0;
  z-index: 3;
  font-size: 8px;

  button {
    width: 60px;
    font-size: 8px;
  }
`;

export default MyMapContainer;
