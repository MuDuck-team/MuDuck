import { useState } from 'react';
import { useNavigate, useLoaderData } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import styled from 'styled-components';
import { ToastContainer, toast } from 'react-toastify';
import customAxios from '../../api/customAxios';
import Button from '../../components/Button';
import { RatingCard } from '../../components/Cards';
import Dropdown from '../../components/DropDown';
import { StyledInput } from '../../components/Input';
import StarRating from '../../components/StarRating';
import { userInfo } from '../../recoil/userAtom';
import MyMapContainer from '../../components/Map/MapContainer';
import 'react-toastify/dist/ReactToastify.css';

export async function loader({ params }) {
  const placeDataResponse = await customAxios.get(`/maps/theater/${params.id}`);
  const theaterListResponse = await customAxios.get(`/theaters`);
  const { data: placeData } = placeDataResponse;
  let { data: theaterList } = theaterListResponse;
  const changeKey = obj => {
    const { id, placeName: categoryName, latitude, longitude } = obj;
    return { id, categoryName, latitude, longitude };
  };
  const changeArray = array => {
    return array.map(obj => changeKey(obj));
  };
  theaterList = changeArray(theaterList);
  const theaterId = params.id;
  return { placeData, theaterId, theaterList };
}

function NearbyPage() {
  const categorys = ['FD6', 'PK6', 'CE7'];
  const navigate = useNavigate();
  const { placeData, theaterId, theaterList } = useLoaderData();
  const currentTheater = theaterList.filter(obj => obj.id === +theaterId)[0];
  const [selectPlaceObj, setSelectPlaceObj] = useState({});
  const [isEdit, setIsEdit] = useState(false);
  const [rate, setRate] = useState(3);
  const [oneLine, setOneLine] = useState('');
  const [prevOnelineObj, setPrevOnelineObj] = useState({});
  const user = useRecoilValue(userInfo);

  const { restaurants = [], cafes = [], parkings = [] } = placeData;

  const onMarkerClick = async obj => {
    setSelectPlaceObj(obj);
    setIsEdit(false);
    if (user?.id) {
      const { getOneLineAndRate } = await import('../../api/muduckApi');
      const data = await getOneLineAndRate(obj.placeId, user.id);
      if (Object.keys(data).length) {
        setPrevOnelineObj(data);
        setRate(data.score);
        setOneLine(data.oneLine);
        setIsEdit(true);
      }
    }
  };
  const onClickRate = rateProp => {
    setRate(rateProp);
  };

  const isLogin = () => {
    return !!user?.id;
  };

  const inCategory = category => {
    return categorys.includes(category);
  };

  const isMarkerSelect = obj => {
    return !!Object.keys(obj).length;
  };

  const changeKeyName = obj => {
    const newObj = { ...obj, placeName: obj.name };
    delete newObj.name;
    return newObj;
  };

  const getText = obj => {
    if (!isMarkerSelect(obj)) {
      toast.warn(
        '리뷰를 남기려면 검색을 하거나 카테고리를 선택해서 장소(식당, 카페, 주차장)를 선택하셔야 합니다.',
      );
      return '리뷰를 남기려면 검색을 하거나 카테고리를 선택해서 장소(식당, 카페, 주차장)를 선택하셔야 합니다.';
    }
    if (!inCategory(obj.categoryGroupCode)) {
      toast.warn('리뷰를 남기려면 식당, 카페, 주차장 중에 선택하여야 합니다.');
      return '리뷰를 남기려면 식당, 카페, 주차장 중에 선택하여야 합니다.';
    }
    return `${obj.name}에 대해 리뷰를 남겨주세요`;
  };

  const canLeaveComment = obj => {
    return (
      isLogin() && inCategory(obj.categoryGroupCode) && isMarkerSelect(obj)
    );
  };

  // Dropdown 전용 함수
  const handleDropDown = dropDownObj => {
    return navigate(`/nearby/${dropDownObj.id}`);
  };

  const isEmpty = str => {
    return str.length === 0;
  };

  const onSubmit = async e => {
    e.preventDefault();
    if (isEmpty(oneLine)) {
      toast.warn('글자 수가 한 글자 이상이여야 합니다');
      return;
    }
    const localToken = localStorage.getItem('localToken');
    let map = { ...selectPlaceObj, theaterId: +theaterId };
    map = changeKeyName(map);

    if (isEdit) {
      const response = await customAxios.patch(
        `/recommend-place/${prevOnelineObj.id}/maps/${prevOnelineObj.mapId}/members/${user?.id}`,
        {
          score: rate,
          oneLine,
        },
        {
          headers: {
            Authorization: localToken,
          },
        },
      );
      if (response.status === 200) {
        toast.success('수정에 성공했습니다.');
      }
    } else {
      const response = await customAxios.post(
        '/recommend-place',
        {
          map,
          recommendPlace: {
            memberId: user?.id,
            score: rate,
            oneLine,
          },
        },
        {
          headers: {
            Authorization: localToken,
          },
        },
      );
      if (response.status === 200) {
        toast.success('게시물 등록에 성공했습니다.');
      }
    }

    setRate(3);
    setOneLine('');
    setSelectPlaceObj({});
    setPrevOnelineObj({});
    setIsEdit(false);
    navigate('.');
  };

  const onChange = e => {
    setOneLine(e.target.value);
  };

  return (
    <NearbyPageContainer>
      <StyledH2>{currentTheater.categoryName}</StyledH2>
      <Label>아래 드롭다운으로 극장을 선택해주세요</Label>
      <Dropdown
        width="315px"
        height="42px"
        onClick={handleDropDown}
        options={theaterList}
        defaultValue={currentTheater}
        selectedValue={currentTheater}
      />
      <MarginBottom margin="16px" />
      <Label>검색창을 이용해 새로운 장소를 추가 해주세요</Label>
      <MyMapContainer
        onMarkerClick={onMarkerClick}
        currentTheater={currentTheater}
        restaurants={restaurants}
        cafes={cafes}
        parkings={parkings}
      />
      <CommentCotainer>
        <H3>
          {user?.id
            ? getText(selectPlaceObj)
            : '리뷰를 남기려면 로그인을 하셔야 합니다'}
        </H3>
        <FormWrapper>
          <RatingContainer>
            <div>평점</div>
            <StarRating
              defaultValue={rate}
              onClick={onClickRate}
              size="24px"
              width="200px"
              readonly={!canLeaveComment(selectPlaceObj)}
            />
          </RatingContainer>
          <StyledForm>
            <StyledInput
              type="text"
              name="oneLine"
              placeholder="리뷰를 입력해주세요."
              value={oneLine}
              width="calc(100% - 75px)"
              height="50px"
              onChange={onChange}
              disabled={!canLeaveComment(selectPlaceObj)}
            />
            <Button
              type="submit"
              text={isEdit ? '수정' : '등록'}
              height="50px"
              margin="0 0 0 16px"
              onClick={onSubmit}
              disabled={!canLeaveComment(selectPlaceObj)}
            />
          </StyledForm>
        </FormWrapper>
      </CommentCotainer>
      <RatingCardContainer>
        <LeftContianer>
          <H3>뮤덕들이 추천한 주변 맛집 TOP 5 </H3>
          <Ul>
            {restaurants.map(obj => (
              <RatingCard
                width="100%"
                height="80px"
                title={obj.name}
                address={obj.address}
                value={obj.avgScore}
                reviewsNum={obj.totalReviews}
                key={obj.id}
              />
            ))}
          </Ul>
        </LeftContianer>
        <RightContianer>
          <H3>뮤덕들이 추천한 주변 카페 TOP 5 </H3>
          <Ul>
            {cafes.map(obj => (
              <RatingCard
                width="100%"
                height="80px"
                title={obj.name}
                address={obj.address}
                value={obj.avgScore}
                reviewsNum={obj.totalReviews}
                key={obj.id}
              />
            ))}
          </Ul>
        </RightContianer>

        <div />
      </RatingCardContainer>
      <ToastContainer
        position="top-right"
        autoClose={1500}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="dark"
      />
    </NearbyPageContainer>
  );
}
const NearbyPageContainer = styled.div`
  width: 100%;
  height: fit-content;

  @media screen and (max-width: 768px) {
    width: 100%;
    padding: 2rem;
  }
`;

const StyledH2 = styled.h2`
  margin-top: 40px;
  font-size: var(--font-size-xxl);
  margin-bottom: 30px;
  font-weight: bold;
`;

const Label = styled.div`
  font-size: var(--font-size-sm);
  margin-bottom: 16px;
  font-weight: 700;
`;

const CommentCotainer = styled.section`
  margin-top: 32px;
  margin-bottom: 32px;
`;

const H3 = styled.h3`
  font-size: var(--font-size-md);
  margin-bottom: 16px;
  line-height: 2.5rem;
`;

const FormWrapper = styled.section`
  display: flex;
  flex-wrap: wrap;
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

const StyledForm = styled.form`
  display: flex;
  align-items: center;
  flex: 1 0 450px;
`;

const MarginBottom = styled.div`
  margin-bottom: ${props => props.margin};
`;

const RatingCardContainer = styled.section`
  display: flex;
  flex-wrap: wrap;
  gap: 16px;

  @media screen and (max-width: 768px) {
    width: 100%;
    flex-direction: column;
  }
`;

const LeftContianer = styled.section`
  flex: 1 0 200px;
`;

const RightContianer = styled.section`
  flex: 1 0 200px;
`;

const Ul = styled.ul`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
`;

export default NearbyPage;
