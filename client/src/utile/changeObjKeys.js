const changeObjKeys = obj => {
  const kakaoObj = {};
  kakaoObj.place_name = obj.name;
  kakaoObj.address_name = obj.address;
  kakaoObj.road_address_name = obj.roadAddress;
  kakaoObj.phone = obj.phone;
  kakaoObj.x = obj.longitude;
  kakaoObj.y = obj.latitube;
  kakaoObj.place_url = obj.placeUrl;
  kakaoObj.score = obj.score;

  return kakaoObj;
};

export default changeObjKeys;
