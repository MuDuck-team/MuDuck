import styled from 'styled-components';

export const StyledInput = styled.input`
  width: ${props => props.width};
  height: ${props => props.height};
  border-radius: 8px;
  border: 1px solid var(--border-color);
  background-color: var(--main-001);
  color: var(--font-color);
  font-size: ${props => props.fontSize || 'var(--font-size-md)'};
  padding: 0px 16px;
  &:focus {
    outline: none;
  }
`;

export const StyledTextArea = styled.textarea`
  width: ${props => props.width};
  height: ${props => props.height};
  min-height: ${props => props.minHeight};
  border-radius: 8px;
  border: 1px solid var(--border-color);
  background-color: var(--main-001);
  color: var(--font-color);
  font-size: ${props => props.fontSize || 'var(--font-size-md)'};
  resize: none;
  padding: 16px;
  line-height: 2em;
  &:focus {
    outline: none;
  }
`;
