import { useEffect, useState } from 'react';
import styled from 'styled-components';

function StarRating({
  defaultValue = 3,
  readonly = false,
  onClick,
  size,
  width,
}) {
  const [rating, setRating] = useState(Math.round(defaultValue));
  const [hover, setHover] = useState(0);

  useEffect(() => {
    setRating(defaultValue);
  }, [defaultValue]);
  return (
    <Wrapper className="star-rating" size={size} width={width}>
      {[...Array(5)].map((star, i) => {
        let index = i;
        index += 1;
        return (
          <Button
            type="button"
            readonly={readonly}
            key={index}
            className={index <= (hover || rating) ? 'on' : 'off'}
            onClick={() => {
              if (readonly) return;
              setRating(index);
              onClick(index);
            }}
            onMouseEnter={() => {
              if (readonly) return;
              setHover(index);
            }}
            onMouseLeave={() => {
              if (readonly) return;
              setHover(rating);
            }}
          >
            <span className="star">&#9733;</span>
          </Button>
        );
      })}
    </Wrapper>
  );
}

const Button = styled.button`
  display: flex;
  align-items: center;
  background-color: transparent;
  border: none;
  outline: none;
  cursor: ${props => (props.readonly ? 'unset' : 'pointer')};

  span {
    padding-bottom: 5px;
  }
`;

const Wrapper = styled.section`
  display: flex;
  width: ${props => props.width || '130px'};
  span {
    display: inline-block;
    font-size: ${props => props.size || '--font-size-md'};
  }
  .on {
    color: #ffd300;
  }
  .off {
    color: #ccc;
  }

  margin-top: 3px;
`;

export default StarRating;
