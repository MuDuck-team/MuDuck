import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import userInfo from '../../recoil/userAtom';
import customAxios from '../../api/customAxios';
import Loading from '../../components/Loading';

function OauthRedirectPage() {
  const setUser = useSetRecoilState(userInfo);
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(document.location.search);
    const token = params.get('accessToken');
    const signup = params.has('signup');
    // console.log(`params로 들어온토큰 : ${token}`);

    setUser(initialUserState => ({ ...initialUserState, token }));

    localStorage.setItem('localToken', JSON.stringify(token));
    const localToken = localStorage.getItem('localToken');
    console.log(localToken);
    //  커스텀 악시오스에 헤더에 토큰을 기본적으로 보내기위해
    //  recoil 밸류를 가져오자니 react hook 룰 위반
    //  따로 로컬스토리지에 저장하는 방식으로 진행

    customAxios({
      method: 'get',
      url: '/회원정보요청엔드포인트',
      headers: {
        Authorization: token,
      },
    }).then(res => {
      setUser(initialUserState => ({ ...initialUserState, ...res.data }));
    });

    if (signup) {
      return navigate('/myinfo');
    }
    return navigate('/');

    //  회원가입일때 -> '/myinfo'
    //  로그인일때 -> '/'
  }, []);

  return (
    <div>
      <Loading />
    </div>
  );
}

export default OauthRedirectPage;
