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

    setUser(initialUserState => ({ ...initialUserState, token }));
    console.log(token);
    console.log(signup);

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
    //  로그인일때 -> '/' 이거나 navigate(-2) 거나....
  });

  return (
    <div>
      <Loading />
    </div>
  );
}

export default OauthRedirectPage;
