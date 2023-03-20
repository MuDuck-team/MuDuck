import { useLoaderData } from 'react-router-dom';
// import MapSection from './MapSection';

import MyMapContainer from './MyMapContainer';

const responce = {
  theater: {
    id: 1,
    placeName: '오둥이극장',
    longitude: 37.5709794,
    latitube: 126.978891,
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
      longitude: 37.57286713479182,
      latitube: 126.97607241059578,
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
      longitude: 37.572695,
      latitube: 126.973265,
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
      longitude: 37.57286713479182,
      latitube: 126.97607241059578,
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
      longitude: 37.5764652,
      latitube: 126.973331,
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
      longitude: 37.5770079,
      latitube: 126.984085,
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
      longitude: 37.572972,
      latitube: 126.974402,
    },
  ],
};

function getData(id) {
  console.log(id);
  return responce;
}

export async function loader({ params }) {
  const localData = await getData(params.id);
  return { localData };
}

function NearbyPage() {
  const { localData } = useLoaderData();
  const { theater, restaurants, cafes, parkings } = localData;
  // const size = { width: '100%', height: '540px' };
  console.log(theater, restaurants, cafes, parkings);
  // return <MapSection size={size} {...localData} />;
  return <MyMapContainer />;
}

export default NearbyPage;
