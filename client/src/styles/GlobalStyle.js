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
    background-color: var(--main-001);
    color: var(--font-color);
  }

  a {
    text-decoration: none;
    color: inherit;
  }
  button {
    cursor: pointer;
  }

  ::-webkit-scrollbar-track
{
  -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
  border-radius: 10px;
  background-color: #F5F5F5;
}

::-webkit-scrollbar
{
  width: 12px;
  background-color: #F5F5F5;
}

::-webkit-scrollbar-thumb
{
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
  background-color: #555;
}
  
`;

export default GlobalStyle;
