import { useState } from 'react';
import { Form, useLoaderData } from 'react-router-dom';
import styled from 'styled-components';
import Button from '../../components/Button';
import { RatingCard } from '../../components/Cards';
import { StyledInput } from '../../components/Input';
import StarRating from '../../components/StarRating';
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
      categoryGroupCode: 'FD6',
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
      categoryGroupCode: 'FD6',
    },
  ],
  cafes: [
    {
      id: 3,
      recoomendPlaceId: 1,
      placeId: 1,
      name: '오둥이카페',
      address: '서울특별시 어쩌구 23로 233',
      roadAddress: '서울특별시 어쩌구 23로 233',
      phone: '02-555-5555',
      score: 5.0,
      totalReview: 200,
      longitude: 126.97607241059578,
      latitube: 37.57286713479182,
      placeUrl: 'http://place.map.kakao.com/10753713',
      categoryGroupCode: 'FD6',
    },
    {
      id: 4,
      recoomendPlaceId: 2,
      placeId: 2,
      name: '오둥이카페2',
      address: '서울특별시 어쩌구 23로 233',
      roadAddress: '서울특별시 어쩌구 23로 233',
      phone: '02-555-5555',
      score: 5.0,
      totalReview: 200,
      longitude: 126.973331,
      latitube: 37.5764652,
      placeUrl: 'http://place.map.kakao.com/10753713',
      categoryGroupCode: 'FD6',
    },
  ],
  parkings: [
    {
      id: 5,
      recoomendPlaceId: 1,
      placeId: 1,
      name: '오둥이주차장',
      address: '서울특별시 어쩌구 23로 233',
      roadAddress: '서울특별시 어쩌구 23로 233',
      phone: '02-555-5555',
      score: 5.0,
      totalReview: 200,
      longitude: 126.984085,
      latitube: 37.5770079,
      placeUrl: 'http://place.map.kakao.com/10753713',
      categoryGroupCode: 'FD6',
    },
    {
      id: 5,
      recoomendPlaceId: 2,
      placeId: 2,
      name: '오둥이주차장2',
      address: '서울특별시 어쩌구 23로 233',
      roadAddress: '서울특별시 어쩌구 23로 233',
      phone: '02-555-5555',
      score: 5.0,
      totalReview: 200,
      longitude: 126.974402,
      latitube: 37.572972,
      placeUrl: 'http://place.map.kakao.com/10753713',
      categoryGroupCode: 'FD6',
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
  const categorys = ['FD6', 'PK6', 'CE7'];
  const { placeData } = useLoaderData();
  const { theater, restaurants, cafes, parkings } = placeData;
  const [selectPlaceObj, setSelectPlaceObj] = useState({});
  const [rate, setRate] = useState(0);
  console.log(selectPlaceObj);

  const onMarkerClick = obj => {
    setSelectPlaceObj(obj);
  };

  const onClickRate = rateProp => {
    setRate(rateProp);
  };

  const inCategory = category => {
    return categorys.includes(category);
  };

  const isMarkerSelect = obj => {
    return !!Object.keys(obj).length;
  };

  const getText = obj => {
    if (!isMarkerSelect(obj)) {
      return '마커를 선택하셔야 합니다.';
    }
    if (!inCategory(obj.categoryGroupCode)) {
      return '식당, 카페, 주차장 중에 선택하여야 합니다.';
    }
    return `${obj.name}에 대해 리뷰를 남겨주세요`;
  };

  console.log(theater, restaurants, cafes, parkings);
  return (
    <>
      <StyledH2>{theater.placeName}</StyledH2>
      <MyMapContainer placeData={placeData} onMarkerClick={onMarkerClick} />
      <CommentCotainer>
        <H3>{getText(selectPlaceObj)}</H3>
        <FormWrapper>
          <RatingContainer>
            <div>평점</div>
            <StarRating
              defaultValue={rate}
              onClick={onClickRate}
              size="24px"
              width="200px"
            />
          </RatingContainer>
          <StyledForm method="post">
            <StyledInput
              placeholder="댓글을 작성하려면 로그인 해주세요"
              width="calc(100% - 75px)"
              height="50px"
            />
            <input type="hidden" name="mapId" value={theater.id} />
            <input type="hidden" name="score" value={rate} />
            <input
              type="hidden"
              name="map"
              value={{ ...selectPlaceObj, 'theater.id': theater.id }}
            />
            <Button
              type="submit"
              text="등록"
              height="50px"
              margin="0 0 0 16px"
            />
          </StyledForm>
        </FormWrapper>
      </CommentCotainer>
      <RatingCard
        width="550px"
        height="80px"
        title="이효근식당"
        address="서울특별시 어쩌구 23로 223"
        value="4.8"
        reviewsNum={213}
      />
    </>
  );
}

const StyledH2 = styled.h2`
  margin-top: 40px;
  font-size: var(--font-size-xxl);
  margin-bottom: 30px;
  font-weight: bold;
`;

const CommentCotainer = styled.section`
  margin-top: 32px;
`;

const H3 = styled.h3`
  font-size: var(--font-size-md);
  margin-bottom: 16px;
`;

const FormWrapper = styled.section`
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: space-between;
`;

const RatingContainer = styled.section`
  display: flex;
  height: 100%;
  align-items: center;
  font-size: var(--font-size-md);

  div {
    white-space: nowrap;
    margin-right: 4px;
  }
`;

const StyledForm = styled(Form)`
  width: 80%;
  display: flex;
  align-items: center;
`;

export default NearbyPage;
