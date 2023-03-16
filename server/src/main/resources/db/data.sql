
--Member 테이블의 Stub 데이터를 생성
INSERT INTO MEMBER (created_at, last_modified_at, email, member_role, member_status, nick_name, picture)
VALUES (NOW(), NOW(), 'wth0086@kakao.com', 'USER', 'MEMBER_ACTIVE', 'VIP좌석은전동석', 'http://k.kakaocdn.net/dn/bBVgYE/btqBEDuAYhw/kHtB5rTFXn2ZUubKUXxPFK/img_640x640.jpg');

INSERT INTO MEMBER (created_at, last_modified_at, email, member_role, member_status, nick_name, picture)
VALUES (NOW(), NOW(), 'GodKwanwoo@kakao.com', 'USER', 'MEMBER_ACTIVE', '쥴리어스11세', 'http://k.kakaocdn.net/dn/uGDxN/btrNu67LG5T/tlsvzNzHBY0Ly9kbJ3IYOk/img_640x640.jpg');

INSERT INTO MEMBER (created_at, last_modified_at, email, member_role, member_status, nick_name, picture)
VALUES (NOW(), NOW(), 'Clerk6@kakao.com', 'USER', 'MEMBER_ACTIVE', '로또아빠', 'http://k.kakaocdn.net/dn/b4u0oh/btr0BDZkfjs/mA86GXvrlkuzv37iF9ud71/img_640x640.jpg');

-- Board 테이블 생성 코드
--Member가 생성되고 Board가 생성되어야합니다.
INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '90' DAY, NOW(), 'BOARD_POST', '내용입니다.', 30, '제목입니다', 982, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '75' DAY, NOW(), 'BOARD_POST', '내용입니다222.', 27, '제목입니다2', 1762, 2);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '60' DAY, NOW(), 'BOARD_POST', '내용입니다333', 45, '제목입니다3', 2819, 3);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '55' DAY, NOW(), 'BOARD_DELETE', '내용입니다444.', 30, '제목입니다4', 982, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '30' DAY, NOW(), 'BOARD_POST', '내용입니다.', 30, '제목입니다', 982, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '25' DAY, NOW(), 'BOARD_POST', '내용입니다222.', 27, '제목입니다2', 1762, 2);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '3' DAY, NOW(), 'BOARD_POST', '내용입니다333', 45, '제목입니다3', 2819, 3);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '1' DAY, NOW(), 'BOARD_DELETE', '내용입니다444.', 30, '제목입니다4', 982, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '30' HOUR, NOW(), 'BOARD_POST', '내용입니다.', 30, '제목입니다', 982, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '25' HOUR, NOW(), 'BOARD_POST', '내용입니다222.', 27, '제목입니다2', 1762, 2);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '3' HOUR, NOW(), 'BOARD_POST', '내용입니다333', 45, '제목입니다3', 2819, 3);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '1' HOUR, NOW(), 'BOARD_DELETE', '내용입니다444.', 30, '제목입니다4', 982, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP, NOW(), 'BOARD_POST', '내용입니다333', 45, '제목입니다3', 2819, 3);



-- NoticeBoard 테이블 생성 코드
INSERT INTO NOTICE_BOARD (title, body, views, created_at, last_modified_at, notice_board_status)
VALUES ('로그인 시 공지사항', '카카오 로그인 시 이메일 허용을 반드시 진행해주세요.', 0, NOW(), NOW(), 'NOTICE_POST');

INSERT INTO NOTICE_BOARD (title, body, views, created_at, last_modified_at, notice_board_status)
VALUES ('커뮤니티 이용 공지사항', '커뮤니티 글 작성 시 허위사실유포 및 지나친 비방은 영구밴이 될 수 있습니다.', 0, NOW(), NOW(), 'NOTICE_POST');
