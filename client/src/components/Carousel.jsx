import { useEffect, useState } from 'react';
import styled, { css } from 'styled-components';
import { RiArrowDropLeftLine, RiArrowDropRightLine } from 'react-icons/ri';

function Carousel({ banners }) {
  const [activeIndex, setActiveIndex] = useState(0);
  const handlePrev = () =>
    setActiveIndex(index => (index - 1 + banners.length) % banners.length);
  const handleNext = () =>
    setActiveIndex(index => (index + 1) % banners.length);
  const handleGoTo = index => setActiveIndex(index);

  useEffect(() => {
    const intervalId = setInterval(handleNext, 3000);

    return () => {
      clearInterval(intervalId);
    };
  }, []);

  return (
    <Container>
      {banners.length > 1 && (
        <ArrowButton pos="left" onClick={handlePrev}>
          <RiArrowDropLeftLine />
        </ArrowButton>
      )}
      <CarouselList>
        {banners.map(banner => (
          <CarouselItem activeIndex={activeIndex} key={banner.id}>
            <img src={banner.url} alt={banner.alt} />
          </CarouselItem>
        ))}
      </CarouselList>
      {banners.length > 1 && (
        <ArrowButton pos="right" onClick={handleNext}>
          <RiArrowDropRightLine />
        </ArrowButton>
      )}
      {banners.length > 1 && (
        <Nav>
          {Array.from({ length: banners.length }).map((_, index) => (
            <NavItem key={index}>
              <NavButton
                isActive={activeIndex === index}
                onClick={() => handleGoTo(index)}
              />
            </NavItem>
          ))}
        </Nav>
      )}
    </Container>
  );
}

const Container = styled.div`
  position: relative;
`;

const ArrowButton = styled.button`
  position: absolute;
  top: 50%;
  z-index: 1;
  transform: translateY(-50%);
  padding: 0;
  border: none;
  background-color: transparent;
  color: var(--font-color);
  font-size: 48px;
  ${({ pos }) =>
    pos === 'left'
      ? css`
          left: 0;
        `
      : css`
          right: 0;
        `};
`;

const CarouselList = styled.ul`
  display: flex;
  width: 100%;
  height: 30vh;
  overflow: hidden;
  border-radius: 8px;

  @media screen and (max-width: 1024px) {
    border-radius: 0;
  }
`;

const CarouselItem = styled.li`
  width: 100%;
  flex: 1 0 100%;
  transform: translateX(-${({ activeIndex }) => activeIndex * 100}%);
  transition: 800ms ease;
  > img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: center;
  }
`;

const NavItem = styled.li`
  display: inline-block;
  margin: 8px 4px;
`;

const Nav = styled.ul`
  display: flex;
  position: absolute;
  bottom: 0%;
  left: 50%;
  z-index: 1;
  transform: translateX(-50%);
`;

const NavButton = styled.button`
  width: 12px;
  height: 12px;
  padding: 0;
  border: 1px solid var(--main-003);
  border-radius: 50%;
  background-color: ${({ isActive }) =>
    isActive ? 'var(--button-color)' : 'var(--font-color)'};
`;

export default Carousel;
