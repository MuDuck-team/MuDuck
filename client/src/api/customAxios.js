import axios from 'axios';
import { useSetRecoilState } from 'recoil';
import userAtom from '../recoil/userAtom';

const token = localStorage.getItem('localToken')
  ? JSON.parse(localStorage.getItem('localToken'))
  : null;

// !커스텀 악시오스 생성
const customAxios = axios.create({
  baseURL: '서버주소',
  headers: {
    'Content-Type': 'application/json',
    Authorization: `Bearer ${token}`,
  },
});

customAxios.defaults.withCredentials = true;

// const { token } = user.token;
// customAxios.defaults.headers.common.authorization = token
//   ? `Bearer ${token}`
//   : null;

// !응답에러 처리
// 오류가 AccessToken 만료때문에 난 경우
customAxios.interceptors.response.use(
  res => {
    return res;
  },
  async error => {
    const setUser = useSetRecoilState(userAtom);

    // response에서 error가 발생했을 경우 catch로 넘어가기 전에 처리
    try {
      const errResponseStatus = error.response.status;
      const errResponseData = error.response.data;
      const prevRequest = error.config;

      // access token이 만료되어 발생하는 에러인 경우 --서버랑 코드, 메세지 합의봐야함
      if (
        errResponseData.error?.message === 'token expired' &&
        errResponseStatus === 401
      ) {
        return async function regenerateToken() {
          await customAxios
            .get('api/user/token')
            .then(async res => {
              const { newToken } = res.headers.Authorization;
              // header 새로운 token으로 재설정
              prevRequest.headers.Authorization = `Bearer ${newToken}`;
              setUser(initialUserState => ({
                ...initialUserState,
                token: newToken,
              }));
              localStorage.setItem('localToken', JSON.stringify(newToken));
              const localToken = localStorage.getItem('localToken');
              console.log(localToken);
              // 실패했던 기존 request 재시도
              return customAxios(prevRequest);
            })
            .catch(e => {
              window.location.href = '/login';
              return new Error(e);
            });
        };
      }
    } catch (e) {
      // 오류 내용 출력 후 요청 거절
      return Promise.reject(e);
    }
    return error;
  },
);

export default customAxios;
