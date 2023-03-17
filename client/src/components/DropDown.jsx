import { useState } from 'react';
import styled from 'styled-components';

function Dropdown({ options, onClick, width, height, maxHeight }) {
  const [selectedOption, setSelectedOption] = useState(null);
  const [isOpen, setIsOpen] = useState(false);

  const handleOptionClick = option => {
    setSelectedOption(option);
    setIsOpen(false);
    onClick(option.id);
  };

  return (
    <DropdownWrapper width={width}>
      <DropdownButton
        width={width}
        height={height}
        onClick={() => setIsOpen(!isOpen)}
      >
        {selectedOption ? selectedOption.categoryName : '카테고리 선택'}
      </DropdownButton>
      {isOpen && (
        <DropdownOptions width={width} maxHeight={maxHeight}>
          {options.map(option => (
            <DropdownOption
              key={option.id}
              onClick={() => handleOptionClick(option)}
              width={width}
            >
              {option.categoryName}
            </DropdownOption>
          ))}
        </DropdownOptions>
      )}
    </DropdownWrapper>
  );
}

const DropdownWrapper = styled.div`
  position: relative;
  background-color: rgba(255, 255, 255, 0.1);
  width: ${props => props.width || '200px'};
`;

const DropdownButton = styled.button`
  padding: 10px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  font-size: var(--font-size-sm);
  cursor: pointer;
  background-color: var(--main-002);
  text-align: left;
  width: ${props => props.width || '200px'};
  height: ${props => props.height || 'auto'};
  color: var(--font-color);
  height: ${props => props.height || 'auto'};
`;

const DropdownOptions = styled.ul`
  position: absolute;
  z-index: 3;
  top: 100%;
  left: 0;
  right: 0;
  padding: 0;
  margin: 0;
  list-style: none;
  background-color: var(--main-002);
  border: 1px solid var(--border-color);
  border-bottom: none;
  border-radius: 0 0 4px 4px;
  max-height: 200px;
  overflow-y: auto;
  max-height: ${props => props.maxHeight || 'auto'};
  width: ${props => props.width || '200px'};
  height: ${props => props.height || 'auto'};
`;

const DropdownOption = styled.li`
  padding: 10px;
  font-size: var(--font-size-sm);
  border-bottom: 1px solid var(--border-color);
  background-color: rgba(0, 0, 0, 0.695);
  cursor: pointer;
  &:hover {
    opacity: 0.5;
  }
`;

export default Dropdown;
