import { useEffect, useState } from 'react';
import styled from 'styled-components';

const { kakao } = window;

const StyledMap = styled.div`
  width: ${({ size }) => size.width};
  height: ${({ size }) => size.height};
  border-radius: 20px;
`;

const ResultStyle = styled.div`
  width: 32%;
  min-width: 150px;
  height: 473px;
  overflow: scroll;
  position: absolute;
  bottom: 10px;
  left: 0px;
  background-color: white;
  z-index: 1;
  font-size: 17px;
  color: #000000;
`;

const Pagination = styled.div`
  margin-top: 3rem;
  a {
    color: black;
    font-size: 20px;
    text-decoration: none;
    margin: 0 10px;
    &on {
      color: lightblue;
      font-weight: bold;
    }
  }
`;

function Map({ searchPlace, size, defalutLocation }) {
  // 검색결과 배열에 담아줌
  const [Places, setPlaces] = useState([]);

  useEffect(() => {
    const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    // const markers = [];
    const container = document.getElementById('myMap');
    const options = {
      center: new kakao.maps.LatLng(defalutLocation.lon, defalutLocation.lat),
      level: 3,
    };
    const map = new kakao.maps.Map(container, options);
    const ps = new kakao.maps.services.Places();

    // 마커가 표시될 위치
    const markerPosition = new kakao.maps.LatLng(
      defalutLocation.lon,
      defalutLocation.lat,
    );

    // 마커를 생성
    const defalutMarker = new kakao.maps.Marker({
      position: markerPosition,
    });

    defalutMarker.setMap(map);

    // 검색결과 목록 하단에 페이지 번호 표시
    function displayPagination(pagination) {
      const paginationEl = document.getElementById('pagination');
      const fragment = document.createDocumentFragment();

      // 기존에 추가된 페이지 번호 삭제
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      for (let i = 1; i <= pagination.last; i += 1) {
        const el = document.createElement('a');
        el.href = '#';
        el.innerHTML = i;

        if (i === pagination.current) {
          el.className = 'on';
        } else {
          el.onclick = (() => {
            return () => {
              pagination.gotoPage(i);
            };
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    }

    function displayMarker(place) {
      const marker = new kakao.maps.Marker({
        map,
        position: new kakao.maps.LatLng(place.y, place.x),
      });

      kakao.maps.event.addListener(marker, 'click', () => {
        infowindow.setContent(
          `<div style="padding:5px;font-size:12px;">${place.place_name}</div>`,
        );
        infowindow.open(map, marker);
      });
    }

    function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        const bounds = new kakao.maps.LatLngBounds();

        for (let i = 0; i < data.length; i += 1) {
          displayMarker(data[i]);
          bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        map.setBounds(bounds);

        // 페이지 목록 보여주는 displayPagination() 추가
        displayPagination(pagination);
        setPlaces(data);
      }
    }

    ps.keywordSearch(searchPlace, placesSearchCB);
  }, [searchPlace]);

  return (
    <>
      <StyledMap size={size} id="myMap" />

      <ResultStyle>
        {Places.map((item, i) => (
          <div key={i} style={{ marginTop: '20px' }}>
            <span>{i + 1}</span>
            <div>
              <h5>{item.place_name}</h5>
              {item.road_address_name ? (
                <div>
                  <span>{item.road_address_name}</span>
                  <span>{item.address_name}</span>
                </div>
              ) : (
                <span>{item.address_name}</span>
              )}
              <span>{item.phone}</span>
            </div>
          </div>
        ))}
        <Pagination id="pagination" />
      </ResultStyle>
    </>
  );
}

export default Map;
