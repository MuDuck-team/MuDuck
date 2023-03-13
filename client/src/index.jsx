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
import PostsPage from './routes/Posts/PostsPage';
import PostPage from './routes/Post/PostPage';
import NoticesPage from './routes/Notices/NoticesPage';
import NoticePage from './routes/Notice/Notice';
import AddPage from './routes/Add/AddPage';
import UpdatePage from './routes/Update/UpdatePage';
import MyPage from './routes/Mypage/MyPage';

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
          { path: '/posts', element: <PostsPage /> },
          { path: '/post/:id', element: <PostPage /> },
          { path: '/notices', element: <NoticesPage /> },
          { path: '/notice/:id', element: <NoticePage /> },
          { path: '/add', element: <AddPage /> },
          { path: '/update', element: <UpdatePage /> },
          { path: '/mypage', element: <MyPage /> },
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
