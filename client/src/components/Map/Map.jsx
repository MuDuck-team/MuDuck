import { useEffect } from 'react';
import styled from 'styled-components';
import {
  changeMuDuckToKakaoObj,
  changeKakaoToMuDuckObj,
} from '../../utile/changeObjKeys';

const { kakao } = window;

function Map({
  searchPlace,
  countRef,
  category,
  onMarkerClick,
  currentTheater,
  restaurants,
  cafes,
  parkings,
  markerMode,
}) {
  useEffect(() => {
    const { latitude: defalutLat, longitude: defalutLng } = currentTheater;

    const theaterMarkerImageSrc =
      'https://muduckbucket.s3.ap-northeast-2.amazonaws.com/muduckIcon.png';
    const theaterMarkerImageSize = new kakao.maps.Size(64, 69);
    const theaterMarkerOption = { offset: new kakao.maps.Point(27, 69) };
    const theaterImage = new kakao.maps.MarkerImage(
      theaterMarkerImageSrc,
      theaterMarkerImageSize,
      theaterMarkerOption,
    );
    const theaterMarkerPosition = new kakao.maps.LatLng(defalutLat, defalutLng);

    const theaterMarker = new kakao.maps.Marker({
      position: theaterMarkerPosition,
      image: theaterImage,
    });

    let markers = [];

    const mapContainer = document.getElementById('map'); // 지도를 표시할 div
    const mapOption = {
      center: new kakao.maps.LatLng(defalutLat, defalutLng), // 지도의 중심좌표
      level: 3, // 지도의 확대 레벨
    };

    // 지도를 생성합니다
    const map = new kakao.maps.Map(mapContainer, mapOption);

    // 장소 검색 객체를 생성합니다
    const ps = new kakao.maps.services.Places();

    theaterMarker.setMap(map);

    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
    const infowindow = new kakao.maps.InfoWindow({
      zIndex: 1,
      clickable: true,
      removable: true,
    });

    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
    function removeAllChildNods(el) {
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
    }

    // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
    // 인포윈도우에 장소명을 표시합니다
    // function displayInfowindow(marker, title) {
    //   const content = `<div style="padding:5px; z-index:1;">${title}</div>`;

    //   infowindow.setContent(content);
    //   infowindow.open(map, marker);
    // }

    function closeDisplayInfowindow(marker, place) {
      const content = `
      <div class="speech-bubble" >
        <h4>${place.place_name}</h4>
        <div>
          <a href=${place.place_url} target="_blank"> 
          카카오 리뷰 보기
        </a>
       </div>
        <hr/>
        <address>${place.address_name}</address>
        <footer>${place.phone === null ? '' : place.phone}</footer>
      </div>
      `;

      infowindow.setContent(content);
      infowindow.open(map, marker);
    }

    // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    function displayPagination(pagination) {
      const paginationEl = document.getElementById('pagination');
      const fragment = document.createDocumentFragment();

      // 기존에 추가된 페이지번호를 삭제합니다
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      if (Object.keys(pagination).length === 0) {
        return undefined;
      }

      for (let i = 1; i <= pagination.last; i += 1) {
        const el = document.createElement('button');
        el.type = 'button';
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
      return undefined;
    }

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    function removeMarker() {
      for (let i = 0; i < markers.length; i += 1) {
        markers[i].setMap(null);
      }
      markers = [];
    }

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    function addMarker(position, idx) {
      const imageSrc =
        'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png'; // 마커 이미지 url, 스프라이트 이미지를 씁니다
      const imageSize = new kakao.maps.Size(36, 37); // 마커 이미지의 크기
      const imgOptions = {
        spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
        spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      };
      const markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imgOptions,
      );
      const marker = new kakao.maps.Marker({
        position, // 마커의 위치
        image: markerImage,
      });

      marker.setMap(map); // 지도 위에 마커를 표출합니다
      markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    }

    // 검색결과 항목을 Element로 반환하는 함수입니다
    function getListItem(index, places) {
      const el = document.createElement('li');
      let itemStr =
        `<span class="markerbg marker_${index + 1}"></span>` +
        `<div class="info">` +
        `   <h5>${places.place_name}</h5>`;

      if (places.road_address_name) {
        itemStr +=
          `    <span>${places.road_address_name}</span>` +
          `   <span class="jibun gray">${places.address_name}</span>`;
      } else {
        itemStr += `    <span>${places.address_name}</span>`;
      }

      itemStr += `  <span class="tel">
        ${places.phone === null ? '' : places.phone}
      </span>`;
      itemStr += `</div>`;
      el.innerHTML = itemStr;
      el.className = 'item';

      return el;
    }

    // 검색 결과 목록과 마커를 표출하는 함수입니다
    function displayPlaces(placesProp, isMarker) {
      const listEl = document.getElementById('placesList');
      const menuEl = document.getElementById('menu_wrap');
      const fragment = document.createDocumentFragment();
      const bounds = new kakao.maps.LatLngBounds();
      let places;
      if (isMarker) {
        places = placesProp.map(obj => changeMuDuckToKakaoObj(obj));
      } else {
        places = placesProp;
      }

      if (placesProp.length === 0) {
        return;
      }

      // 지도에 표시되고 있는 마커를 제거합니다
      removeMarker();

      // 검색 결과 목록에 추가된 항목들을 제거합니다
      removeAllChildNods(listEl);

      for (let i = 0; i < places.length; i += 1) {
        // 마커를 생성하고 지도에 표시합니다

        const placePosition = new kakao.maps.LatLng(places[i].y, places[i].x);
        const marker = addMarker(placePosition, i);
        const itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
        // const title = places[i].place_name;
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다

        (() => {
          kakao.maps.event.addListener(marker, 'mouseover', () => {
            closeDisplayInfowindow(marker, places[i]);
          });

          kakao.maps.event.addListener(marker, 'click', () => {
            onMarkerClick(changeKakaoToMuDuckObj(places[i]));
          });

          itemEl.onmouseover = () => {
            // displayInfowindow(marker, title);
          };

          itemEl.onmousedown = () => {
            onMarkerClick(changeKakaoToMuDuckObj(places[i]));
            closeDisplayInfowindow(marker, places[i]);
          };

          itemEl.onmouseout = () => {
            // infowindow.close();
          };
        })();

        fragment.appendChild(itemEl);
      }

      // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
      listEl.appendChild(fragment);
      menuEl.scrollTop = 0;

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      map.setBounds(bounds);
    }

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert('검색 결과가 존재하지 않습니다.');
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert('검색 결과 중 오류가 발생했습니다.');
      }
    }

    // 카테고리를 클릭했을 때 마커를 보여주는 함수입니다.
    function choiceCategory() {
      if (category === 'restaurants') {
        displayPlaces(restaurants, true);
      }
      if (category === 'cafes') {
        displayPlaces(cafes, true);
      }
      if (category === 'parkings') {
        displayPlaces(parkings, true);
      } else {
        displayPlaces([], true);
      }
      displayPagination({});
      return undefined;
    }

    // 키워드 검색을 요청하는 함수입니다
    function searchPlaces() {
      if (!searchPlace.replace(/^\s+|\s+$/g, '')) {
        // 처음에 들어갔을 때 경고창을 막기 위하여 작성
        const listEl = document.getElementById('placesList');
        removeAllChildNods(listEl);
        if (countRef.current === 0) {
          return undefined;
        }
        alert('키워드를 입력해주세요!');
        return false;
      }

      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다

      ps.keywordSearch(searchPlace, placesSearchCB);

      return undefined;
    }

    // 키워드로 장소를 검색합니다
    if (!markerMode) {
      searchPlaces();
    }
    choiceCategory();
  }, [searchPlace, category, currentTheater]);

  return (
    <StyledMapWrapper className="map_wrap">
      <StyledMap id="map" />
      <StyledMenu id="menu_wrap" className="bg_white" markerMode={markerMode}>
        <hr />
        <StyledUl id="placesList" />
        <StyledPagenation id="pagination" />
      </StyledMenu>
    </StyledMapWrapper>
  );
}

const StyledMap = styled.div`
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
`;

const StyledMapWrapper = styled.div`
  .speech-bubble {
    width: 150px;
    background-color: var(--main-001);
    padding: 20px;
    z-index: 1;
    color: var(--font-color);

    h4 {
      font-weight: 700;
      font-size: var(--font-size-sm);
      margin-bottom: 8px;
    }

    a {
      color: var(--button-color);
      font-weight: 500;

      :hover {
        color: #c77a27;
      }
    }

    hr {
      margin: 8px 0;
    }

    address {
      margin-bottom: 4px;
    }
  }

  a,
  a:hover,
  a:active {
    color: #000;
    text-decoration: none;
  }

  position: relative;
  width: 100%;
  height: 100%;
  color: #000;
`;

const StyledMenu = styled.div`
  display: ${props => (props.markerMode ? 'none' : 'initial')};
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;

  .bg_white {
    background: #fff;
  }
  hr {
    display: block;
    height: 1px;
    border: 0;
    border-top: 2px solid #5f5f5f;
    margin: 35px 0 3px;
  }
  .option {
    text-align: center;
  }
  .option p {
    margin: 10px 0;
  }
  .option button {
    margin-left: 5px;
  }
`;

const StyledUl = styled.ul`
  li {
    list-style: none;
  }
  .item {
    position: relative;
    border-bottom: 1px solid #888;
    overflow: hidden;
    cursor: pointer;
    min-height: 65px;
  }
  .item span {
    display: block;
    margin-top: 4px;
  }
  .item h5,
  .item .info {
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
  }
  .item .info {
    padding: 10px 0 10px 55px;
  }
  .info .gray {
    color: #8a8a8a;
  }
  .info .jibun {
    padding-left: 26px;
    background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
      no-repeat;
  }
  .info .tel {
    color: #009900;
  }
  .item .markerbg {
    float: left;
    position: absolute;
    width: 36px;
    height: 37px;
    margin: 10px 0 0 10px;
    background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
      no-repeat;
  }
  .item .marker_1 {
    background-position: 0 -10px;
  }
  .item .marker_2 {
    background-position: 0 -56px;
  }
  .item .marker_3 {
    background-position: 0 -102px;
  }
  .item .marker_4 {
    background-position: 0 -148px;
  }
  .item .marker_5 {
    background-position: 0 -194px;
  }
  .item .marker_6 {
    background-position: 0 -240px;
  }
  .item .marker_7 {
    background-position: 0 -286px;
  }
  .item .marker_8 {
    background-position: 0 -332px;
  }
  .item .marker_9 {
    background-position: 0 -378px;
  }
  .item .marker_10 {
    background-position: 0 -423px;
  }
  .item .marker_11 {
    background-position: 0 -470px;
  }
  .item .marker_12 {
    background-position: 0 -516px;
  }
  .item .marker_13 {
    background-position: 0 -562px;
  }
  .item .marker_14 {
    background-position: 0 -608px;
  }
  .item .marker_15 {
    background-position: 0 -654px;
  }
`;

const StyledPagenation = styled.div`
  margin: 10px auto;
  text-align: center;

  a {
    display: inline-block;
    margin-right: 10px;
  }
  .on {
    font-weight: bold;
    cursor: default;
    color: #777;
  }
`;

export default Map;
