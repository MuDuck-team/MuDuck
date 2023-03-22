import { RecoilRoot } from 'recoil';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import GlobalStyle from './styles/GlobalStyle';
import Root from './routes/Root';
import ErrorPage from './routes/Error/ErrorPage';
import MainPage from './routes/Main/MainPage';
import LoginPage from './routes/Login/LoginPage';
import SingupPage from './routes/Signup/SingupPage';
import MyinfoPage from './routes/Myinfo/MyinfoPage';
import PlaysPage from './routes/Plays/PlaysPage';
import PlayPage from './routes/Play/PlayPage';
import NearbyPage from './routes/Nearby/NearbyPage';
import PostsPage, { loader as postsPageLoader } from './routes/Posts/PostsPage';
import PostPage, { loader as postPageLoader } from './routes/Post/PostPage';
import PostAddPage, {
  loader as postAddPageLoader,
} from './routes/PostAdd/PostAddPage';
import PostEditPage, {
  loader as postEditPageLoader,
} from './routes/PostEdit/PostEditPage';
import NoticesPage, {
  loader as noticesPageLoader,
} from './routes/Notices/NoticesPage';
import NoticePage from './routes/Notice/Notice';
import NoticeAddPage, {
  loader as noticeAddPageLoader,
} from './routes/NoticeAdd/NoticeAddPage';
import NoticeEditPage, {
  loader as noticeEditPageLoader,
} from './routes/NoticeEdit/NoticeEditPage';
import MyPage from './routes/Mypage/MyPage';
import AdminLoginPage from './routes/AdminLogin/AdminLoginPage';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    errorElement: <ErrorPage />,
    children: [
      {
        errorElement: <ErrorPage />,
        children: [
          { index: true, element: <MainPage /> },
          { path: '/login', element: <LoginPage /> },
          { path: '/signup', element: <SingupPage /> },
          { path: '/myinfo', element: <MyinfoPage /> },
          { path: '/plays', element: <PlaysPage /> },
          { path: '/play/:id', element: <PlayPage /> },
          { path: '/nearby', element: <NearbyPage /> },
          { path: '/posts', element: <PostsPage />, loader: postsPageLoader },
          { path: '/post/:id', element: <PostPage />, loader: postPageLoader },
          {
            path: '/post/add',
            element: <PostAddPage />,
            loader: postAddPageLoader,
          },
          {
            path: '/post/edit/:id',
            element: <PostEditPage />,
            loader: postEditPageLoader,
          },
          {
            path: '/notices',
            element: <NoticesPage />,
            loader: noticesPageLoader,
          },
          { path: '/notice/:id', element: <NoticePage /> },
          {
            path: '/notice/add',
            element: <NoticeAddPage />,
            loader: noticeAddPageLoader,
          },
          {
            path: '/notice/edit/:id',
            element: <NoticeEditPage />,
            loader: noticeEditPageLoader,
          },
          { path: '/mypage', element: <MyPage /> },
          { path: '/admin', element: <AdminLoginPage /> },
        ],
      },
    ],
  },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <RecoilRoot>
    <GlobalStyle />
    <RouterProvider router={router} />
  </RecoilRoot>,
);
