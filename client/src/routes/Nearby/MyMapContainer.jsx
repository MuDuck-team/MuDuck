import { useRef, useState } from 'react';
import styled from 'styled-components';
import MyMap from './MyMap';

function MyMapContainer({ placeData, onMarkerClick }) {
  const [inputText, setInputText] = useState('');
  const [searchPlace, setSearchPlace] = useState('');
  const [category, setCategory] = useState('');
  // 처음에 들어갔을 때 경고창을 막기 위하여 작성
  const countRef = useRef(0);

  const onChange = e => {
    setInputText(e.target.value);
  };

  const handleSubmit = e => {
    e.preventDefault();
    setSearchPlace(inputText);
    setInputText('');
    setCategory('');
    countRef.current += 1;
  };

  const onClick = e => {
    const target = e.target.closest('li');
    setCategory(target.dataset.name);
    setSearchPlace('');
    countRef.current = 0;
  };

  return (
    <StyledMapContainer>
      <StyledForm onSubmit={handleSubmit}>
        <span>키워드 :</span>
        <input type="text" value={inputText} onChange={onChange} size="15" />
        <button type="submit">검색하기</button>
      </StyledForm>
      <CategoryContainer className="category">
        <ul>
          <li
            value="restaurants"
            role="presentation"
            data-name="restaurants"
            onClick={onClick}
          >
            <span className="ico_comm ico_coffee" />
            맛집
          </li>
          <li
            id="storeMenu"
            data-name="cafes"
            role="presentation"
            onClick={onClick}
          >
            <span className="ico_comm ico_store" />
            카페
          </li>
          <li
            id="carparkMenu"
            data-name="packings"
            role="presentation"
            onClick={onClick}
          >
            <span className="ico_comm ico_carpark" />
            주차장
          </li>
        </ul>
      </CategoryContainer>
      <MyMap
        searchPlace={searchPlace}
        countRef={countRef}
        placeData={placeData}
        category={category}
        onMarkerClick={onMarkerClick}
      />
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
  top: 0;
  left: 0;
  background-color: #313131;
  border-radius: 10px 10px 0 0;
  margin: 10px 0 30px 10px;
  width: 250px;
  height: 37px;
  padding: 5px 3px;
  z-index: 3;
  font-size: 8px;

  button {
    width: 60px;
    font-size: 8px;
  }
`;

const CategoryContainer = styled.section`
  position: absolute;
  z-index: 3;
  top: 3px;
  right: 3px;
  display: flex;
  width: 160px;
  height: 50px;
  justify-content: space-evenly;
  overflow: hidden;
  border: 1px solid black;
  font-size: var(--font-size-xs);
  background-color: var(--main-001);
  border-radius: 8px;
  text-align: center;

  .menu_selected {
    background: #ff5f4a;
    color: #fff;
    border-left: 1px solid #915b2f;
    border-right: 1px solid #915b2f;
    margin: 0 -1px;
  }
  li {
    list-style: none;
    float: left;
    width: 50px;
    height: 45px;
    padding-top: 5px;
    cursor: pointer;
  }
  .ico_comm {
    display: block;
    margin: 0 auto 2px;
    width: 22px;
    height: 26px;
    background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/category.png')
      no-repeat;
  }
  .ico_coffee {
    background-position: -10px 0;
  }
  .ico_store {
    background-position: -10px -36px;
  }
  .ico_carpark {
    background-position: -10px -72px;
  }
`;

export default MyMapContainer;
