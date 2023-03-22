import { useState } from 'react';
import styled from 'styled-components';

function StarRating() {
  const [rating, setRating] = useState(0);
  const [hover, setHover] = useState(0);
  return (
    <Wrapper className="star-rating">
      {[...Array(5)].map((star, i) => {
        let index = i;
        index += 1;
        return (
          <Button
            type="button"
            key={index}
            className={index <= (hover || rating) ? 'on' : 'off'}
            onClick={() => setRating(index)}
            onMouseEnter={() => setHover(index)}
            onMouseLeave={() => setHover(rating)}
          >
            <span className="star">&#9733;</span>
          </Button>
        );
      })}
    </Wrapper>
  );
}

const Button = styled.button`
  background-color: transparent;
  border: none;
  outline: none;
  cursor: pointer;
`;

const Wrapper = styled.section`
  span {
    display: inline-block;
    font-size: 30px;
  }
  .on {
    color: #ffd300;
  }
  .off {
    color: #ccc;
  }
`;

export default StarRating;
