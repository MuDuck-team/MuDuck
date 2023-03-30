import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';
import variables from './variables';

const GlobalStyle = createGlobalStyle`
  ${reset}
  ${variables}

  *, *:before, *:after {
    box-sizing: border-box;
  }

  html, body {
    font-size: 62.5%;
    font-family: 'Montserrat', 'Noto Sans KR', sans-serif;
    background-color: var(--main-001);
    color: var(--font-color);
  }

  a {
    text-decoration: none;
    color: inherit;
  }

  button {
    cursor: pointer;
    font-family: 'Montserrat', 'Noto Sans KR', sans-serif;
  }

  ::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
    border-radius: 10px;
    background-color: #F5F5F5;
  }

  ::-webkit-scrollbar {
    width: 12px;
    background-color: #F5F5F5;
  }

  ::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
    background-color: #555;
  }

  .Toastify__toast-container {
    font-size: var(--font-size-sm);
  }
`;

export default GlobalStyle;
