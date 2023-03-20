import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import customAxios from '../../api/customAxios';
import userInfo from '../../recoil/userAtom';
import Loading from '../../components/Loading';

function OauthRedirectPage() {
  const setUser = useSetRecoilState(userInfo);
  const navigate = useNavigate();

  useEffect(() => {
    const { searchParams } = new URL(window.location.href).searchParams;
    const token = searchParams.get('token') || '';
    setUser(initialUserState => ({ ...initialUserState, token }));

    customAxios({
      method: 'get',
      url: '/회원정보요청엔드포인트',
      headers: {
        Authorization: token,
      },
    }).then(res => {
      setUser(initialUserState => ({ ...initialUserState, ...res.data }));
    });

    navigate(-2 || '/');
  });

  return (
    <div>
      <Loading />
    </div>
  );
}

export default OauthRedirectPage;
