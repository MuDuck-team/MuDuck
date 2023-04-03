import { useState } from 'react';
import styled from 'styled-components';
// import axios from 'axios';
import { ReactComponent as Logo } from '../../assets/logo.svg';
import { StyledInput } from '../../components/Input';
import Button from '../../components/Button';

function AdminLoginPage() {
  const [values, setValues] = useState({
    id: '',
    password: '',
  });

  const { id, password } = values;

  const handleChangeInput = event => {
    setValues({
      ...values,
      [event.target.name]: event.target.value,
    });
  };

  const handleSubmit = event => {
    event.preventDefault();

    const inputData = {
      id,
      password,
    };
    console.log(inputData);
    // axios.post('/admin/login', inputData);
    setValues({ id: '', password: '' });
  };

  return (
    <LoginPageConainer>
      <MuduckLogo />
      <TextParagraph>ADMIN LOGIN</TextParagraph>
      <LoginForm>
        <InputLabel htmlFor="adminId">ID</InputLabel>
        <StyledInput
          type="id"
          name="id"
          value={values.id}
          onChange={handleChangeInput}
          id="adminId"
          width="250px"
          height="30px"
        />
        <InputLabel htmlFor="adminPassword">PASSWORD</InputLabel>
        <StyledInput
          type="password"
          name="password"
          value={values.password}
          onChange={handleChangeInput}
          id="adminPassword"
          width="250px"
          height="30px"
        />
        <Button
          onClick={handleSubmit}
          text="LOGIN"
          margin="3rem"
          width="250px"
          height="30px"
          type="submit"
          fontSize="var(--font-size-md)"
        />
      </LoginForm>
    </LoginPageConainer>
  );
}

const LoginPageConainer = styled.div`
  display: flex;
  height: 100%;
  position: relative;
  top: 20vh;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  @media screen and (max-width: 768px) {
    width: 100%;
    padding: 16px;
  }
`;

const MuduckLogo = styled(Logo)`
  width: 173px;
  height: 33px;
  margin-bottom: 32px;
`;

const TextParagraph = styled.p`
  font-size: var(--font-size-xl);
  font-weight: 700;
  margin-top: 8px;
  margin-bottom: 2rem;
`;

const LoginForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: left;
  width: 300px;

  & label:nth-child(1) {
    position: relative;
    left: -35%;
  }
`;

const InputLabel = styled.label`
  font-size: var(--font-size-lg);
  font-weight: 500;
  margin-top: 3rem;
  margin-bottom: 1rem;
  position: relative;
  left: -25%;
`;
export default AdminLoginPage;
