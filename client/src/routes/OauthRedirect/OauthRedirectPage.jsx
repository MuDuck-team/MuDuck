import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { userInfo, userState } from '../../recoil/userAtom';
import customAxios from '../../api/customAxios';
import Loading from '../../components/Loading';

function OauthRedirectPage() {
  const setUser = useSetRecoilState(userInfo);
  const setUserStatus = useSetRecoilState(userState);
  // const setAdminStatus = useSetRecoilState(adminState);
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(document.location.search);
    const token = params.get('accessToken');
    const signup = params.get('signup');

    localStorage.setItem('localToken', token);
    const localToken = localStorage.getItem('localToken');

    customAxios({
      method: 'get',
      url: '/members/my-info',
      headers: {
        Authorization: localToken,
      },
    })
      .then(res => {
        // if (res.data.role === 'ROLE_ADMIN') {
        //   setAdminStatus(!adminState);
        //   setUser(initialUserState => ({ ...initialUserState, ...res.data }));
        // } else {
        setUserStatus(!userState);
        setUser(initialUserState => ({ ...initialUserState, ...res.data }));
        // }
      })
      .then(() => {
        if (signup === 'true') {
          return navigate('/myinfo');
        }
        return navigate('/');
      });
  }, []);

  return (
    <div>
      <Loading />
    </div>
  );
}

export default OauthRedirectPage;
