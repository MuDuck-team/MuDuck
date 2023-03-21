export const changeMuDuckToKakaoObj = muDuckObj => {
  const kakaoObj = {};
  kakaoObj.place_name = muDuckObj.name;
  kakaoObj.address_name = muDuckObj.address;
  kakaoObj.road_address_name = muDuckObj.roadAddress;
  kakaoObj.phone = muDuckObj.phone;
  kakaoObj.x = muDuckObj.longitude;
  kakaoObj.y = muDuckObj.latitube;
  kakaoObj.place_url = muDuckObj.placeUrl;
  kakaoObj.score = muDuckObj.score;

  return kakaoObj;
};

export const changeKakaoToMuDuckObj = kakaoObj => {
  const muDuckObj = {};
  muDuckObj.name = kakaoObj.place_name;
  muDuckObj.address = kakaoObj.address_name;
  muDuckObj.roadAddress = kakaoObj.road_address_name;
  muDuckObj.phone = kakaoObj.phone;
  muDuckObj.longitude = kakaoObj.x;
  muDuckObj.latitube = kakaoObj.y;
  muDuckObj.placeUrl = kakaoObj.place_url;
  muDuckObj.score = kakaoObj.score;
  muDuckObj.mapId = kakaoObj.id;

  // category_group_code
  return muDuckObj;
};
