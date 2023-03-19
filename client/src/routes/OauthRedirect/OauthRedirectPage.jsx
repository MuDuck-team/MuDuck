import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import axios from 'axios';
import userInfo from '../../recoil/userAtom';
import Loading from '../../components/Loading';

function OauthRedirectPage() {
  const setUser = useSetRecoilState(userInfo);
  const navigate = useNavigate();

  useEffect(() => {
    const { searchParams } = new URL(window.location.href).searchParams;
    const token = searchParams.get('token') || '';
    setUser(initialUserState => ({ ...initialUserState, token }));

    axios({
      method: 'get',
      url: '서버주소/회원정보요청엔드포인트',
      headers: {
        Authorization: token,
      },
    }).then(res => {
      setUser(...res.data);
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
