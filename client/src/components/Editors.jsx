import { useEffect, useState } from 'react';
import { Form } from 'react-router-dom';
import styled from 'styled-components';
import Select from 'react-select';
import Button from './Button';
import Dropdown from './DropDown';
import { StyledInput, StyledTextArea } from './Input';

// 더미데이터
const objArr = [
  { id: 1, categoryName: '최신순' },
  { id: 2, categoryName: '조회순' },
  { id: 3, categoryName: '이름순' },
];

// 더미데이터
const options = [
  {
    id: 4,
    label: '2014 레베카',
    value: 1,
  },
  {
    id: 5,
    label: '2017 레베카',
    value: 2,
  },
  {
    id: 6,
    label: '2019 헤드윅',
    value: 3,
  },
];

// DropDownSearch 스타일
const customStyles = {
  control: baseStyles => ({
    ...baseStyles,
    backgroundColor: 'var(--main-002)',
    fontSize: 'var(--font-size-md)',
    width: '315px',
    height: '43px',
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

function Editors({ pathname, defaultTitle = '', defalutContent = '' }) {
  const [categoryIds, setIds] = useState([1]);
  const [title, setTitle] = useState(defaultTitle);
  const [content, setContent] = useState(defalutContent);
  const [selectedOptions, setSelectedOptions] = useState([]);
  const isPost = pathname.includes('post');

  useEffect(() => {
    console.log(categoryIds);
  }, [categoryIds]);

  // DropdownSearch 전용 함수
  const handleChange = selected => {
    setSelectedOptions(selected);
    if (!selected) {
      const idsArray = categoryIds.slice(0, 1);
      setIds(idsArray);
      return;
    }
    if (categoryIds[1]) {
      const idsArray = categoryIds.map((id, idx) =>
        idx === 1 ? selected.value : id,
      );
      setIds(idsArray);
      return;
    }
    const idsArray = [...categoryIds, selected.value];
    setIds(idsArray);
  };

  // Dropdown 전용 함수
  const handleDropDown = dropDownid => {
    const idsArray = categoryIds.map((id, idx) =>
      idx === 0 ? dropDownid : id,
    );
    setIds(idsArray);
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
      {pathname.includes('add') ? (
        <StyledH2 isPost={isPost}>
          {isPost ? '게시글' : '공지사항'} 작성하기
        </StyledH2>
      ) : (
        <StyledH2 isPost={isPost}>
          {isPost ? '게시글' : '공지사항'} 수정하기
        </StyledH2>
      )}
      {isPost && (
        <CategoryWrapper>
          <CategoryContent>
            <p>카테고리</p>{' '}
            <Dropdown
              width="315px"
              height="42px"
              options={objArr}
              onClick={handleDropDown}
              defaultValue={objArr[1]}
            />
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
      )}
      <Form>
        <input type="hidden" name="id" value={categoryIds} />
        <InputWrapper>
          <StyledLable htmlFor="title">제목</StyledLable>
          <StyledInput
            type="text"
            id="title"
            name="title"
            value={title}
            width="100%"
            height="50px"
            onChange={handleValue}
            placeholder="제목을 입력해주세요."
          />
        </InputWrapper>
        <InputWrapper>
          <StyledLable htmlFor="content">내용</StyledLable>
          <StyledTextArea
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

const EditorWrapper = styled.article``;

const StyledH2 = styled.h2`
  margin-top: 40px;
  font-size: var(--font-size-xxl);
  margin-bottom: ${props => (props.isPost ? '8px' : '30px')};
  font-weight: bold;
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

const StyledLable = styled.label``;

export default Editors;
