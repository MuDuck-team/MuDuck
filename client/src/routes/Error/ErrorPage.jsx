import { useRouteError } from 'react-router-dom';
import styled from 'styled-components';

function ErrorPage() {
  const error = useRouteError();

  return (
    <ErrorPageLayout>
      <ErrorTitle>Sorry, an unexpected error has occurred.</ErrorTitle>
      <ErrorMessage>
        {error && <em>{error.statusText || error.message}</em>}
      </ErrorMessage>
    </ErrorPageLayout>
  );
}

const ErrorPageLayout = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100vh;
`;

const ErrorTitle = styled.h1`
  padding: 1.6rem;
  font-size: var(--font-size-xxl);
`;

const ErrorMessage = styled.p`
  padding: 1.6rem;
  font-size: var(--font-size-xl);
  color: #828282;
`;

export default ErrorPage;
