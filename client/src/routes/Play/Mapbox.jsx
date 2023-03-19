import { useEffect } from 'react';
import styled from 'styled-components';

function Mapbox() {
  const newScript = src => {
    return new Promise((resolve, reject) => {
      const script = document.createElement('script');
      script.src = src;
      script.addEventListener('load', () => {
        resolve();
      });
      script.addEventListener('error', e => {
        reject(e);
      });
      document.head.appendChild(script);
    });
  };

  useEffect(() => {
    //  카카오맵 스크립트 읽어오기
    const myScript = newScript(
      'https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=149bc65697286def75781d3d5700b384',
    );

    //  스크립트 읽기 완료 후 카카오맵 설정
    myScript.then(() => {
      const { kakao } = window;
      kakao.maps.load(() => {
        const mapContainer = document.getElementById('map');
        const options = {
          center: new kakao.maps.LatLng(37.57286713479182, 126.97607241059578), //  좌표설정
          // center: new kakao.maps.LatLng({ latitude }, { longitude }), // 좌표설정
          level: 3, //  지도확대 축소정도
        };
        const map = new kakao.maps.Map(mapContainer, options); // 맵생성
        console.log(map);

        // const coffePositions = [
        const positions = [
          {
            title: '포비 광화문',
            latlng: new kakao.maps.LatLng(37.5709794, 126.978891),
          },
          {
            title: '카페 베란다',
            latlng: new kakao.maps.LatLng(
              37.57286713479182,
              126.97607241059578,
            ),
          },
          {
            title: '쏘리에스프레소',
            latlng: new kakao.maps.LatLng(37.5764652, 126.973331),
          },
          {
            title: '도트블랭킷 안국점',
            latlng: new kakao.maps.LatLng(37.5770079, 126.984085),
          },
        ];

        // const foodPositions = [
        //   {
        //     title: '미학 상차림',
        //     latlng: new kakao.maps.LatLng(
        //       37.57286713479182,
        //       126.97607241059578,
        //     ),
        //   },
        //   {
        //     title: '평안도만두집',
        //     latlng: new kakao.maps.LatLng(37.572695, 126.973265),
        //   },
        //   {
        //     title: '어바웃진스',
        //     latlng: new kakao.maps.LatLng(37.572844, 126.973266),
        //   },
        //   {
        //     title: '루뽀',
        //     latlng: new kakao.maps.LatLng(37.572972, 126.974402),
        //   },
        // ];

        const imageSrc =
          'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';

        for (let i = 0; i < positions.length; i += 1) {
          // 마커 이미지의 이미지 크기
          const imageSize = new kakao.maps.Size(24, 35);

          // 마커 이미지를 생성합니다
          const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
          const marker = new kakao.maps.Marker({
            map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
          });
          console.log(marker);
          // marker.setMap(map);
        }
      });
    });
  }, []);

  return (
    <MapContainer id="map">
      <categoyBox />
    </MapContainer>
  );
}

const MapContainer = styled.div`
  width: 100%;
  margin: 1rem;
  height: 60rem;
  border: 1px solid gray;
  z-index: 0;
`;

export default Mapbox;
