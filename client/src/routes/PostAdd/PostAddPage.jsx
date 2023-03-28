import { useEffect } from 'react';
import { redirect, useLoaderData, useNavigate } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import customAxios from '../../api/customAxios';
import Editors from '../../components/Editors';
import { userInfo } from '../../recoil/userAtom';

export async function loader({ request }) {
  const url = new URL(request.url);
  const { pathname } = url;
  const response = await customAxios.get('/boards/category');
  const { category, mentionedMusical } = response.data;
  return { pathname, category, mentionedMusical };
}

export async function action({ request }) {
  try {
    const formData = await request.formData();
    const postFormData = Object.fromEntries(formData);
    const categoryIds = postFormData.categoryIds.split(',').map(Number);
    const token = localStorage.getItem('localToken');
    const data = { ...postFormData, categoryIds };
    await customAxios.post(
      '/boards',
      {
        ...data,
      },
      {
        headers: {
          Authorization: token,
        },
      },
    );

    return redirect(`/posts`);
  } catch (err) {
    console.log(err);
    throw new Error(err.response);
  }
}

function PostAddPage() {
  const obj = useLoaderData();
  const navigate = useNavigate();
  const user = useRecoilValue(userInfo);

  useEffect(() => {
    if (!user) {
      navigate('/login');
    }
  }, []);

  return <Editors {...obj} />;
}

export default PostAddPage;
