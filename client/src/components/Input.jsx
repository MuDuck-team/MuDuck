import styled from 'styled-components';

export const StyledInput = styled.input`
  width: ${props => props.width};
  height: ${props => props.height};
  border-radius: 8px;
  border: 1px solid var(--border-color);
  background-color: var(--main-001);
  color: var(--font-color);
  font-size: ${props => props.fontSize || 'var(--font-size-md)'};
  &:focus {
    outline: none;
  }
`;

export const StyledTextArea = styled.textarea`
  width: ${props => props.width};
  height: ${props => props.height};
  border-radius: 8px;
  border: 1px solid var(--border-color);
  background-color: var(--main-001);
  color: var(--font-color);
  font-size: ${props => props.fontSize || 'var(--font-size-md)'};
  resize: none;

  &:focus {
    outline: none;
  }
`;
