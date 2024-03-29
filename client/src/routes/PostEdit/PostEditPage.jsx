import { redirect, useLoaderData } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import customAxios from '../../api/customAxios';
import Editors from '../../components/Editors';
import { userInfo } from '../../recoil/userAtom';

export async function loader({ request, params }) {
  try {
    const url = new URL(request.url);
    const paramsId = params.id;
    const response = await customAxios.get(`/boards/${paramsId}`);
    const { pathname } = url;

    return { pathname, response };
  } catch (e) {
    throw new Error(e);
  }
}

export async function action({ request, params }) {
  try {
    const formData = await request.formData();
    const editFormData = Object.fromEntries(formData);
    delete editFormData.categoryIds;
    const token = localStorage.getItem('localToken');
    await customAxios.patch(
      `/boards/${params.id}`,
      {
        ...editFormData,
      },
      {
        headers: {
          Authorization: token,
        },
      },
    );

    return redirect(`/post/${params.id}`);
  } catch (e) {
    console.log(e);
    return false;
  }
}

function PostEditPage() {
  const { pathname, response } = useLoaderData();
  const user = useRecoilValue(userInfo);
  const userId = user?.id;
  const publisherId = response.data.boardContent.head.memberId;
  const { title: defaultTitle, content: defalutContent } =
    response.data.boardContent.body;
  if (!(userId === publisherId)) {
    redirect(-1);
  }

  return (
    <Editors
      pathname={pathname}
      defaultTitle={defaultTitle}
      defalutContent={defalutContent}
    />
  );
}

export default PostEditPage;
