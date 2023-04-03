import styled from 'styled-components';
import { ReactComponent as Logo } from '../assets/logo.svg';

function Footer() {
  return (
    <FooterLayout>
      <MuduckLogo />
      <MemberContainer>
        <MemberLink
          href="https://github.com/TaeheeWoo94"
          target="_blank"
          rel="noopener noreferrer"
        >
          우태희
        </MemberLink>
        <MemberLink
          href="https://github.com/kkte02"
          target="_blank"
          rel="noopener noreferrer"
        >
          김관우
        </MemberLink>
        <MemberLink
          href="https://github.com/DerekYook"
          target="_blank"
          rel="noopener noreferrer"
        >
          육경득
        </MemberLink>
        <MemberLink
          href="https://github.com/Paksubeen"
          target="_blank"
          rel="noopener noreferrer"
        >
          박수빈
        </MemberLink>
        <MemberLink
          href="https://github.com/sleepy-joyy"
          target="_blank"
          rel="noopener noreferrer"
        >
          이승연
        </MemberLink>
        <MemberLink
          href="https://github.com/LeeHyoGeun-create"
          target="_blank"
          rel="noopener noreferrer"
        >
          이효근
        </MemberLink>
      </MemberContainer>
      <CopyrightText>© Copyright ⓒ 2023 MuDuck</CopyrightText>
    </FooterLayout>
  );
}

const FooterLayout = styled.footer`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 160px;
  background: var(--main-001);
`;

const MuduckLogo = styled(Logo)`
  width: 160px;
  height: 40px;
`;

const MemberContainer = styled.div`
  display: flex;
  margin-top: 24px;
`;

const MemberLink = styled.a`
  padding: 0 8px;
  color: var(--font-color);
  font-size: var(--font-size-xs);
  letter-spacing: 2px;
  border-right: 1px solid var(--line-color);
  &:last-child {
    border: none;
  }
`;

const CopyrightText = styled.p`
  margin-top: 12px;
  color: var(--font-color);
  font-size: var(--font-size-xs);
`;

export default Footer;
