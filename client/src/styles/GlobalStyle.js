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
`;

export default GlobalStyle;
