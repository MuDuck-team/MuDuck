import { useState } from 'react';
import { Form } from 'react-router-dom';
import styled from 'styled-components';
import Select from 'react-select';
import Button from './Button';
import Dropdown from './DropDown';
import { StyledInput, StyledTextArea } from './Input';

const customStyles = {
  control: baseStyles => ({
    ...baseStyles,
    backgroundColor: 'var(--main-002)',
    fontSize: 'var(--font-size-md)',
    width: '315px',
    borderColor: 'var(--border-color)',
    '&:hover': { borderColor: 'var(--border-color)' },
  }),
  container: baseStyles => ({
    ...baseStyles,
    '&:hover': { borderColor: 'var(--border-color)' },
  }),
  input: baseStyles => ({
    ...baseStyles,
    color: 'var(--font-color)',
    '&::placeholder': { color: 'var(--font-color)' },
  }),
  option: (provided, state) => ({
    ...provided,
    backgroundColor: state.isFocused ? 'var(--main-003)' : 'var(--main-002)',
    fontSize: 'var(--font-size-md)',
    color: 'var(--font-color)',
  }),
  menu: provided => ({
    ...provided,
    backgroundColor: 'var(--main-002)',
  }),
};

const options = [
  {
    id: 4,
    label: '2014 레베카',
    value: 2,
  },
  {
    id: 5,
    label: '2017 레베카',
    value: 2,
  },
  {
    id: 6,
    label: '2019 헤드윅',
    value: 2,
  },
];

function Editors() {
  const [id] = useState([]);
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [selectedOptions, setSelectedOptions] = useState([]);

  const handleChange = selected => {
    setSelectedOptions(selected);
  };

  const handleValue = e => {
    const { name, value } = e.target;
    if (name === 'title') {
      setTitle(value);
    } else {
      setContent(value);
    }
  };

  return (
    <EditorWrapper>
      <h2>게시글 작성하기</h2>
      <CategoryWrapper>
        <CategoryContent>
          <p>카테고리</p> <Dropdown width="315px" hegiht="37px" />
        </CategoryContent>
        <CategoryContent>
          <p>뮤지컬</p>{' '}
          <Select
            options={options}
            value={selectedOptions}
            onChange={handleChange}
            styles={customStyles}
            isClearable="true"
            isSearchable="true"
          />
        </CategoryContent>
      </CategoryWrapper>
      <Form>
        <input type="hidden" name="id" value={id} />
        <InputWrapper>
          <label htmlFor="title">제목</label>
          <StyledInput
            name="title"
            value={title}
            width="100%"
            height="50px"
            onChange={handleValue}
            placeholder="제목을 입력해주세요."
          />
        </InputWrapper>
        <InputWrapper>
          <label htmlFor="content">내용</label>
          <StyledTextArea
            type="text"
            id="content"
            name="content"
            value={content}
            width="100%"
            height="400px"
            onChange={handleValue}
            placeholder="내용을 입력해주세요."
          />
        </InputWrapper>
        <ButtonWrapper>
          <Button
            type="button"
            text="취소하기"
            bgColor="var(--main-002)"
            hover="#0a0a0a"
            active="#0a0a0a"
          />
          <Button type="submit" text="등록하기" />
        </ButtonWrapper>
      </Form>
    </EditorWrapper>
  );
}

const EditorWrapper = styled.article`
  h2 {
    margin-top: 40px;
    margin-bottom: 8px;
    font-size: var(--font-size-xxl);
  }
`;

const CategoryWrapper = styled.section`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-end;
`;

const CategoryContent = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;

  p {
    margin-right: 8px;
    font-size: var(--font-size-sm);
  }
`;

const InputWrapper = styled.section`
  label {
    display: block;
    font-size: var(--font-size-xl);
    margin-bottom: 16px;
  }

  margin-bottom: 32px;
`;

const ButtonWrapper = styled.section`
  display: flex;
  margin-top: 64px;
  justify-content: center;
  margin-bottom: 40px;

  button + button {
    margin-left: 24px;
  }
`;

export default Editors;
