import { useState } from 'react';
import styled from 'styled-components';
import Map from './Map';

const MapContainer = styled.div`
  width: ${({ size }) => size.width};
  height: ${({ size }) => size.hieght};
  position: relative;
`;

const Search = styled.input`
  width: 300px;
  height: 40px;
  border-radius: 30px 0px 0px 30px;
  font-size: 20px;
`;

const SearchForm = styled.form`
  position: absolute;
  z-index: 2;
  top: 10px;
  left: 0;
`;

const SearchBtn = styled.button`
  width: 50px;
  height: 45px;
  border-radius: 0px 30px 30px 0px;

  background-color: #fff;
  color: #333;
  &:hover {
    cursor: pointer;
    background-color: #333;
    color: #fff;
  }
`;

function MapSection({ size, theater }) {
  const [InputText, setInputText] = useState('');
  const [Place, setPlace] = useState('');

  const onChange = e => {
    setInputText(e.target.value);
  };

  const handleSubmit = e => {
    e.preventDefault();
    setPlace(InputText);
    setInputText('');
  };

  return (
    <MapContainer size={size}>
      <SearchForm className="inputForm" onSubmit={handleSubmit}>
        <Search
          placeholder="검색어를 입력하세요"
          onChange={onChange}
          value={InputText}
        />
        <SearchBtn type="submit">검색</SearchBtn>
      </SearchForm>
      <Map
        size={size}
        searchPlace={Place}
        defalutLocation={{ lon: theater.longitude, lat: theater.latitube }}
      />
    </MapContainer>
  );
}

export default MapSection;
