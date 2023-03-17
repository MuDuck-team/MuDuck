import { useLoaderData } from 'react-router-dom';
import Editors from '../../components/Editors';

export function loader({ request }) {
  const url = new URL(request.url);
  const { pathname } = url;
  return { pathname };
}

function PostAddPage() {
  const { pathname } = useLoaderData();

  return <Editors pathname={pathname} />;
}

export default PostAddPage;
