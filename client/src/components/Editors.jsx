import { useState } from 'react';
import { Form, useNavigate, useSubmit } from 'react-router-dom';
import styled from 'styled-components';
import Select from 'react-select';
import { toast } from 'react-toastify';
import Button from './Button';
import Dropdown from './DropDown';
import { StyledInput, StyledTextArea } from './Input';

// DropDownSearch 스타일
const customStyles = {
  control: baseStyles => ({
    ...baseStyles,
    backgroundColor: 'var(--main-002)',
    fontSize: 'var(--font-size-sm)',
    width: '280px',
    height: '40px',
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
    fontSize: 'var(--font-size-sm)',
    color: 'var(--font-color)',
  }),
  menu: provided => ({
    ...provided,
    backgroundColor: 'var(--main-002)',
  }),
  singleValue: provided => ({
    ...provided,
    color: 'var(--font-color)',
  }),
};

function Editors({
  pathname,
  defaultTitle = '',
  defalutContent = '',
  category = [],
  mentionedMusical = [],
}) {
  const navigate = useNavigate();
  const [categoryIds, setIds] = useState([1]);
  const submit = useSubmit();
  const [title, setTitle] = useState(defaultTitle);
  const [content, setContent] = useState(defalutContent);
  const [selectedOptions, setSelectedOptions] = useState([]);
  const isPost = pathname.includes('post');
  const isAdd = pathname.includes('add');
  const openCategory = isPost && isAdd;

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
  const handleDropDown = dropDownObj => {
    const idsArray = categoryIds.map((id, idx) =>
      idx === 0 ? dropDownObj.id : id,
    );
    setIds(idsArray);
  };

  const maxLengthCheck = e => {
    const { length } = e.target.value;
    if (length > 50) {
      e.target.value = e.target.value.slice(0, 50);
      toast.error('제목은 최대 50자까지 작성할 수 있습니다.');
    }
  };

  const handleValue = e => {
    const { name, value } = e.target;
    if (name === 'title') {
      setTitle(value);
    } else {
      setContent(value);
    }
  };

  const isEmpty = str => {
    return str.length === 0;
  };

  const handleCancel = () => {
    navigate(-1);
  };

  const handleSubmit = e => {
    e.preventDefault();
    if (isEmpty(title) && isEmpty(content)) {
      toast.error('글자 수가 한 글자 이상이여야 합니다');
      return;
    }

    submit(e.currentTarget.form);
  };

  const changeKey = obj => {
    const { categoryName: label, id: value } = obj;
    return { label, value };
  };

  const changeSearchInputObj = arr => {
    const searchInputArr = arr.map(changeKey);
    return searchInputArr;
  };

  return (
    <EditorLayout>
      {isAdd ? (
        <StyledH2 openCategory={openCategory}>
          {isPost ? '게시글' : '공지사항'} 작성하기
        </StyledH2>
      ) : (
        <StyledH2 openCategory={openCategory}>
          {isPost ? '게시글' : '공지사항'} 수정하기
        </StyledH2>
      )}
      {openCategory && (
        <CategoryWrapper>
          <CategoryContent>
            <p>카테고리</p>
            <Dropdown
              width="280px"
              height="40px"
              options={category}
              onClick={handleDropDown}
              defaultValue={category[0]}
              selectedValue={category[0]}
            />
          </CategoryContent>
          <CategoryContent>
            <p>뮤지컬</p>
            <Select
              options={changeSearchInputObj(mentionedMusical)}
              value={selectedOptions}
              onChange={handleChange}
              styles={customStyles}
              isClearable="true"
              isSearchable="true"
            />
          </CategoryContent>
        </CategoryWrapper>
      )}
      <Form method="post">
        <input type="hidden" name="categoryIds" value={categoryIds} />
        <InputWrapper>
          <StyledLabel htmlFor="title">제목</StyledLabel>
          <StyledInput
            type="text"
            id="title"
            name="title"
            value={title}
            width="100%"
            height="50px"
            onChange={handleValue}
            placeholder="제목을 입력해주세요."
            onInput={maxLengthCheck}
          />
        </InputWrapper>
        <InputWrapper>
          <StyledLabel htmlFor="content">내용</StyledLabel>
          <StyledTextArea
            id="content"
            name="content"
            value={content}
            width="100%"
            height="400px"
            minHeight="400px"
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
            onClick={handleCancel}
          />
          <Button type="submit" text="등록하기" onClick={handleSubmit} />
        </ButtonWrapper>
      </Form>
    </EditorLayout>
  );
}

const EditorLayout = styled.main`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;
  @media screen and (max-width: 1024px) {
    width: 90%;
  }
`;

const StyledH2 = styled.h2`
  margin-top: 40px;
  font-size: var(--font-size-xxl);
  margin-bottom: ${props => (props.openCategory ? '8px' : '30px')};
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

const StyledLabel = styled.label``;

export default Editors;
