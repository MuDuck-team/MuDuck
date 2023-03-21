import { useLoaderData } from 'react-router-dom';
import MyMapContainer from './MyMapContainer';

const responce = {
  theater: {
    id: 1,
    placeName: '오둥이극장',
    longitude: 126.978891,
    latitube: 37.5709794,
    phone: '02-555-5555',
    address: '서울특별시 어쩌구 23로 233',
    roadAddress: '서울특별시 어쩌구 23로 233',
  },
  restaurants: [
    {
      id: 1,
      recoomendPlaceId: 1,
      placeId: 1,
      name: '오둥이식당',
      address: '서울특별시 어쩌구 23로 233',
      roadAddress: '서울특별시 어쩌구 23로 233',
      phone: '02-555-5555',
      score: 5.0,
      totalReview: 200,
      longitude: 126.97607241059578,
      latitube: 37.57286713479182,
      placeUrl: 'http://place.map.kakao.com/10753713',
    },
    {
      id: 2,
      recoomendPlaceId: 2,
      placeId: 2,
      name: '오둥이식당2',
      address: '서울특별시 어쩌구 23로 233',
      roadAddress: '서울특별시 어쩌구 23로 233',
      phone: '02-555-5555',
      score: 5.0,
      totalReview: 200,
      longitude: 126.973265,
      latitube: 37.572695,
      placeUrl: 'http://place.map.kakao.com/10753713',
    },
  ],
  cafes: [
    {
      id: 3,
      recoomendPlaceId: 1,
      placeId: 1,
      name: '오둥이식당',
      address: '서울특별시 어쩌구 23로 233',
      roadAddress: '서울특별시 어쩌구 23로 233',
      phone: '02-555-5555',
      score: 5.0,
      totalReview: 200,
      longitude: 126.97607241059578,
      latitube: 37.57286713479182,
      placeUrl: 'http://place.map.kakao.com/10753713',
    },
    {
      id: 4,
      recoomendPlaceId: 2,
      placeId: 2,
      name: '오둥이식당2',
      address: '서울특별시 어쩌구 23로 233',
      roadAddress: '서울특별시 어쩌구 23로 233',
      phone: '02-555-5555',
      score: 5.0,
      totalReview: 200,
      longitude: 126.973331,
      latitube: 37.5764652,
      placeUrl: 'http://place.map.kakao.com/10753713',
    },
  ],
  parkings: [
    {
      id: 5,
      recoomendPlaceId: 1,
      placeId: 1,
      name: '오둥이식당',
      address: '서울특별시 어쩌구 23로 233',
      roadAddress: '서울특별시 어쩌구 23로 233',
      phone: '02-555-5555',
      score: 5.0,
      totalReview: 200,
      longitude: 126.984085,
      latitube: 37.5770079,
      placeUrl: 'http://place.map.kakao.com/10753713',
    },
    {
      id: 5,
      recoomendPlaceId: 2,
      placeId: 2,
      name: '오둥이식당2',
      address: '서울특별시 어쩌구 23로 233',
      roadAddress: '서울특별시 어쩌구 23로 233',
      phone: '02-555-5555',
      score: 5.0,
      totalReview: 200,
      longitude: 126.974402,
      latitube: 37.572972,
      placeUrl: 'http://place.map.kakao.com/10753713',
    },
  ],
};

function getData(id) {
  console.log(id);
  return responce;
}

export async function loader({ params }) {
  const placeData = await getData(params.id);
  return { placeData };
}

function NearbyPage() {
  const { placeData } = useLoaderData();
  const { theater, restaurants, cafes, parkings } = placeData;
  console.log(theater, restaurants, cafes, parkings);
  return <MyMapContainer placeData={placeData} />;
}

export default NearbyPage;
