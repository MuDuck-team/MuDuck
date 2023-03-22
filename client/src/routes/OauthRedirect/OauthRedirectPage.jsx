import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { userInfo, userState, adminState } from '../../recoil/userAtom';
import customAxios from '../../api/customAxios';
import Loading from '../../components/Loading';

function OauthRedirectPage() {
  const setUser = useSetRecoilState(userInfo);
  const setUserStatus = useSetRecoilState(userState);
  const setAdminStatus = useSetRecoilState(adminState);
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(document.location.search);
    const token = params.get('accessToken');
    const signup = params.get('signup');
    console.log(signup);

    setUser(initialUserState => ({ ...initialUserState, token }));

    localStorage.setItem('localToken', token);
    // localStorage.setItem('localToken', JSON.stringify(token));
    const localToken = localStorage.getItem('localToken');
    console.log(`로컬토큰이 저장됨 : ${localToken}`);
    //  커스텀 악시오스에 헤더에 토큰을 기본적으로 보내기위해
    //  recoil 밸류를 가져오자니 react hook 룰 위반
    //  따로 로컬스토리지에 저장하는 방식으로 진행

    customAxios({
      method: 'get',
      url: '/members/my-info',
      headers: {
        Authorization: localToken,
      },
    }).then(res => {
      if (res.data.role === 'ROLE_ADMIN') {
        setAdminStatus(!adminState);
        setUser(initialUserState => ({ ...initialUserState, ...res.data }));
      } else {
        setUserStatus(!userState);
        setUser(initialUserState => ({ ...initialUserState, ...res.data }));
      }
    });

    if (signup === 'true') {
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
