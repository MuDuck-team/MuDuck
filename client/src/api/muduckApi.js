import customAxios from './customAxios';

// 뮤지컬정보를 가져오는 api
export const getTheaterInfo = async theaterId => {
  return customAxios({
    method: 'get',
    url: `/theaters/${theaterId}`,
  });
};

// 해당 뮤지컬관련 커뮤니티글 가져오기
export const getNearbyTheaterData = async theaterId => {
  return customAxios({
    method: 'get',
    url: `/maps/theater/${theaterId}`,
  });
};

// 뮤지컬정보를 가져오는 api
export const getMusicalDetail = async musicalId => {
  return customAxios({
    method: 'get',
    url: `/musicals/${musicalId}`,
  });
};

// 뮤지컬 출연진 정보 가져오기
export const getActorsDetail = async musicalId => {
  return customAxios({
    method: 'get',
    url: `/musicals/${musicalId}/actors`,
  });
};

// 해당 뮤지컬관련 커뮤니티글 가져오기
export const getRelatedBoard = async musicalId => {
  const token = localStorage.getItem('localToken');
  return customAxios({
    method: 'get',
    url: `/musicals/${musicalId}/board`,
    headers: { Authorization: token },
  });
};

// 해당 마커의 내가 달은 한줄평과 평점 가져오기
export const getOneLineAndRate = async (mapId, memberId) => {
  const token = localStorage.getItem('localToken');

  const response = await customAxios.get(
    `/recommend-place/maps/${mapId}/members/${memberId}`,
    {
      headers: { Authorization: token },
    },
  );
  const { data } = response;

  return data || {};
};

export const getPopularDailyPosts = async () => {
  return customAxios({
    method: 'get',
    url: `/main-page/daily`,
  });
};

export const getPopularWeeklyPosts = async () => {
  return customAxios({
    method: 'get',
    url: `/main-page/weekly`,
  });
};

export const getPopularMusicals = async () => {
  return customAxios({
    method: 'get',
    url: `/main-page/musicals`,
  });
};
