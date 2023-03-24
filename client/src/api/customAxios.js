import axios from 'axios';

axios.defaults.withCredentials = true;

// !커스텀 악시오스 생성
const customAxios = axios.create({
  baseURL: `${process.env.REACT_APP_SERVER_URL}`,
  headers: {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': `${process.env.REACT_APP_SERVER_URL}`,
  },
  withCredentials: true,
});

customAxios.defaults.withCredentials = true;

// !응답에러 처리
// 오류가 AccessToken 만료때문에 난 경우

async function reissueToken() {
  await customAxios.get('/refresh-token/reissuance').then(res => {
    console.log(res);
    const { newToken } = res.data.token;
    return newToken;
  });
}

customAxios.interceptors.response.use(
  function (res) {
    return res;
  },
  async function (error) {
    // response에서 error가 발생했을 경우 catch로 넘어가기 전에 처리
    try {
      console.log('인터셉터동작중');
      const errResponseMessage = error.response.data.message;
      const errStatus = error.response.status;
      const prevRequest = error.config;

      console.log(errResponseMessage);

      if (errResponseMessage === 'Token expired' && errStatus === 401) {
        reissueToken()
          .then(newToken => {
            prevRequest.headers.Authorization = newToken;
            localStorage.setItem('localToken', newToken);
            const localToken = localStorage.getItem('localToken');
            console.log(`token reissued! ${localToken}`);
            return customAxios(prevRequest);
          })
          .catch(e => {
            localStorage.clear();
            window.location.href =
              'http://muduckbucket.s3-website.ap-northeast-2.amazonaws.com/login';
            return new Error(e);
          });
      }
    } catch (err) {
      // 오류 내용 출력 후 요청 거절
      return Promise.reject(err);
    }
    return error;
  },
);

export default customAxios;
