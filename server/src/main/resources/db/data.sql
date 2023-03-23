
--Member 테이블의 Stub 데이터를 생성
INSERT INTO MEMBER (created_at, last_modified_at, email, member_role, member_status, nick_name, picture, refresh_token)
VALUES (NOW(), NOW(), 'wth0086@naver.com', 'USER', 'MEMBER_ACTIVE', 'VIP좌석은전동석', 'http://k.kakaocdn.net/dn/bBVgYE/btqBEDuAYhw/kHtB5rTFXn2ZUubKUXxPFK/img_640x640.jpg', '1234');

INSERT INTO MEMBER (created_at, last_modified_at, email, member_role, member_status, nick_name, picture, refresh_token)
VALUES (NOW(), NOW(), 'GodKwanwoo@kakao.com', 'USER', 'MEMBER_ACTIVE', '쥴리어스11세', 'http://k.kakaocdn.net/dn/uGDxN/btrNu67LG5T/tlsvzNzHBY0Ly9kbJ3IYOk/img_640x640.jpg', '1234');

INSERT INTO MEMBER (created_at, last_modified_at, email, member_role, member_status, nick_name, picture, refresh_token)
VALUES (NOW(), NOW(), 'Clerk6@kakao.com', 'USER', 'MEMBER_ACTIVE', '로또아빠', 'http://k.kakaocdn.net/dn/b4u0oh/btr0BDZkfjs/mA86GXvrlkuzv37iF9ud71/img_640x640.jpg', '1234');

-- Board 테이블 생성 코드
--Member가 생성되고 Board가 생성되어야합니다.
INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '90' DAY, NOW(), 'BOARD_POST', '내용입니다.', 30, '제목입니다', 982, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '75' DAY, NOW(), 'BOARD_POST', '내용입니다222.', 27, '제목입니다2', 1762, 2);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '60' DAY, NOW(), 'BOARD_POST', '내용입니다333', 45, '제목입니다3', 2819, 3);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '55' DAY, NOW(), 'BOARD_DELETE', '삭제된 내용입니다444.', 31, '삭제된 제목입니다4', 983, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '30' DAY, NOW(), 'BOARD_POST', '내용입니다.', 32, '제목입니다', 984, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '25' DAY, NOW(), 'BOARD_POST', '내용입니다222.', 29, '제목입니다2', 1763, 2);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '3' DAY, NOW(), 'BOARD_POST', '내용입니다333', 46, '제목입니다3', 2820, 3);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '1' DAY, NOW(), 'BOARD_DELETE', '삭제된 내용입니다444.', 35, '삭제된 제목입니다4', 980, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '30' HOUR, NOW(), 'BOARD_POST', '내용입니다.', 34, '제목입니다', 979, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '25' HOUR, NOW(), 'BOARD_POST', '내용입니다222.', 28, '제목입니다2', 1760, 2);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '3' HOUR, NOW(), 'BOARD_POST', '내용입니다333', 192, '제목입니다3', 2815, 3);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '1' HOUR, NOW(), 'BOARD_DELETE', '삭제된 내용입니다444.', 181, '삭제된 제목입니다4', 959, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP, NOW(), 'BOARD_POST', '내용입니다333', 10, '제목입니다3', 2930, 3);



-- NoticeBoard 테이블 생성 코드
INSERT INTO NOTICE_BOARD (title, body, views, created_at, last_modified_at, notice_board_status)
VALUES ('로그인 시 공지사항', '카카오 로그인 시 이메일 허용을 반드시 진행해주세요.', 0, NOW(), NOW(), 'NOTICE_POST');

INSERT INTO NOTICE_BOARD (title, body, views, created_at, last_modified_at, notice_board_status)
VALUES ('커뮤니티 이용 공지사항', '커뮤니티 글 작성 시 허위사실유포 및 지나친 비방은 영구밴이 될 수 있습니다.', 0, NOW(), NOW(), 'NOTICE_POST');

INSERT INTO NOTICE_BOARD (title, body, views, created_at, last_modified_at, notice_board_status)
VALUES ('옛날 로그인 시 공지사항', '카카오 로그인 시 이메일 허용을 반드시 진행해주세요.', 0, CURRENT_TIMESTAMP - INTERVAL '1' HOUR, NOW(), 'NOTICE_POST');

INSERT INTO NOTICE_BOARD (title, body, views, created_at, last_modified_at, notice_board_status)
VALUES ('옛날 커뮤니티 이용 공지사항', '커뮤니티 글 작성 시 허위사실유포 및 지나친 비방은 영구밴이 될 수 있습니다.', 0, CURRENT_TIMESTAMP - INTERVAL '1' HOUR, NOW(), 'NOTICE_POST');

-- Theater 테이블 생성 코드
INSERT INTO THEATER (place_Name, longitude, latitude, phone, address, road_Address, created_at, last_modified_at)
VALUES ('오둥이 소극장', 126.978891, 37.5709794, '02-555-5555', '서울특별시 송파구 잠실동 40-1번지 샤롯데씨어터', '서울특별시 송파구 올림픽로 240(잠실동) 샤롯데씨어터', NOW(), NOW());

INSERT INTO THEATER (place_Name, longitude, latitude, phone, address, road_Address, created_at, last_modified_at)
VALUES ('오둥이 중극장', 126.978891, 37.5709794, '02-555-5555', '서울특별시 송파구 잠실동 40-1번지 샤롯데씨어터', '서울특별시 송파구 올림픽로 240(잠실동) 샤롯데씨어터', NOW(), NOW());

INSERT INTO THEATER (place_Name, longitude, latitude, phone, address, road_Address, created_at, last_modified_at)
VALUES ('오둥이 대극장', 126.978891, 37.5709794, '02-555-5555', '서울특별시 송파구 잠실동 40-1번지 샤롯데씨어터', '서울특별시 송파구 올림픽로 240(잠실동) 샤롯데씨어터', NOW(), NOW());

-- Map 테이블 생성 코드
INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 10753713,'http://place.map.kakao.com/10753713', '오둥이식당', 126.97607241059578, 37.57286713479182, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1934809750,'http://place.map.kakao.com/1934809750', '오둥이카페', 126.973265, 37.572695, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1624572111,'http://place.map.kakao.com/1624572111', '오둥이주차장', 126.97607241059578, 37.57286713479182, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

-- Category 테이블 생성 코드
INSERT INTO Category (category_name, parent_id) VALUES ('자유주제', NULL);
INSERT INTO Category (category_name, parent_id) VALUES ('공연정보/후기', NULL);
INSERT INTO Category (category_name, parent_id) VALUES ('시설정보', NULL);

INSERT INTO Category (category_name, parent_id) VALUES ('2014 레베카', 2);
INSERT INTO Category (category_name, parent_id) VALUES ('2017 레베카', 2);
INSERT INTO Category (category_name, parent_id) VALUES ('2019 헤드윅', 2);

-- BOARD_CATEGORY 테이블 생성 코드
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (1, 1);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (2, 2);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (2, 4);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (3, 3);

-- Comment 테이블 생성 코드
INSERT INTO COMMENT (created_at, body, comment_status, member_id, board_id, parent_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '1' HOUR,'댓글입니다1', 'COMMENT_POST', 2, 1, null);

INSERT INTO COMMENT (created_at, body, comment_status, member_id, board_id, parent_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '30' MINUTE,'대댓글입니다1', 'COMMENT_POST', 1, 1, 1);

INSERT INTO COMMENT (created_at, body, comment_status, member_id, board_id, parent_id)
VALUES (CURRENT_TIMESTAMP,'대댓글입니다2', 'COMMENT_POST', 3, 1, 1);

-- BOARD_LIKE 테이블 생성 코드
INSERT INTO BOARD_LIKE (BOARD_ID, MEMBER_ID) VALUES (1, 1);




