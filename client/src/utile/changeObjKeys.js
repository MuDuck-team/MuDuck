export const changeMuDuckToKakaoObj = muDuckObj => {
  const kakaoObj = {};
  kakaoObj.place_name = muDuckObj.name;
  kakaoObj.address_name = muDuckObj.address;
  kakaoObj.road_address_name = muDuckObj.roadAddress;
  kakaoObj.phone = muDuckObj.phone;
  kakaoObj.x = muDuckObj.longitude;
  kakaoObj.y = muDuckObj.latitude;
  kakaoObj.place_url = muDuckObj.placeUrl;
  kakaoObj.id = muDuckObj.placeId;
  kakaoObj.category_group_code = muDuckObj.categoryGroupCode;

  return kakaoObj;
};

export const changeKakaoToMuDuckObj = kakaoObj => {
  const muDuckObj = {};
  muDuckObj.name = kakaoObj.place_name;
  muDuckObj.address = kakaoObj.address_name;
  muDuckObj.roadAddress = kakaoObj.road_address_name;
  muDuckObj.phone = kakaoObj.phone;
  muDuckObj.longitude = kakaoObj.x;
  muDuckObj.latitude = kakaoObj.y;
  muDuckObj.placeUrl = kakaoObj.place_url;
  muDuckObj.placeId = kakaoObj.id;
  muDuckObj.categoryGroupCode = kakaoObj.category_group_code;

  // category_group_code
  return muDuckObj;
};
