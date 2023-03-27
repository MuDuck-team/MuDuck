
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
VALUES (CURRENT_TIMESTAMP - INTERVAL '30' DAY, NOW(), 'BOARD_POST', '내용입니다.', 333, '제목입니다', 984, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '25' DAY, NOW(), 'BOARD_POST', '내용입니다222.', 233, '제목입니다2', 1763, 2);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '3' DAY, NOW(), 'BOARD_POST', '내용입니다333', 9872, '제목입니다3', 12901, 3);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '1' DAY, NOW(), 'BOARD_DELETE', '삭제된 내용입니다444.', 35, '삭제된 제목입니다4', 980, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '30' HOUR, NOW(), 'BOARD_POST', '내용입니다.', 34, '제목입니다', 979, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '25' HOUR, NOW(), 'BOARD_POST', '내용입니다222.', 20, '제목입니다2', 1755, 2);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '22' HOUR, NOW(), 'BOARD_POST', '내용입니다222.', 22, '제목입니다1212', 1760, 2);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '15' HOUR, NOW(), 'BOARD_POST', '내용입니다.', 38, '제목입니다', 940, 1);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '3' HOUR, NOW(), 'BOARD_POST', '내용입니다333', 192, '제목입니다3', 2817, 3);

INSERT INTO BOARD (created_at, last_modified_at, board_status, content, likes, title, views, member_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '2' HOUR, NOW(), 'BOARD_POST', '내용입니다333', 192, '제목입니다4', 2815, 3);

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
VALUES ('예술의 전당', 126.978891, 37.5709794, '02-555-5555', '서울 서초구 서초동 700', '서울 서초구 남부순환로 2406', NOW(), NOW());

INSERT INTO THEATER (place_Name, longitude, latitude, phone, address, road_Address, created_at, last_modified_at)
VALUES ('샤롯데씨어터', 127.0998494, 37.5107077, '02-555-5555', '서울특별시 송파구 잠실동 40-1번지 샤롯데씨어터', '서울특별시 송파구 올림픽로 240(잠실동) 샤롯데씨어터', NOW(), NOW());

INSERT INTO THEATER (place_Name, longitude, latitude, phone, address, road_Address, created_at, last_modified_at)
VALUES ('충무아트센터', 127.0148131, 37.5660139, '02-555-5555', '서울 중구 흥인동 131', '서울 중구 퇴계로 387', NOW(), NOW());

-- Map 테이블 생성 코드
INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1, 'http://place.map.kakao.com/10753713', '오둥이식당1', 126.97607241059578, 37.57286713479182, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 2, 'http://place.map.kakao.com/10753713', '오둥이식당2', 126.97607241059579, 37.57286713479183, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 3, 'http://place.map.kakao.com/10753713', '오둥이식당3', 126.97607241059580, 37.57286713479184, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 4, 'http://place.map.kakao.com/10753713', '오둥이식당4', 126.97607241059581, 37.57286713479185, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 5, 'http://place.map.kakao.com/10753713', '오둥이식당5', 126.97607241059582, 37.57286713479186, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());


INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 6, 'http://place.map.kakao.com/1934809750', '오둥이카페1', 126.973265, 37.572695, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 7, 'http://place.map.kakao.com/1934809750', '오둥이카페2', 126.973266, 37.572696, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 8, 'http://place.map.kakao.com/1934809750', '오둥이카페3', 126.973267, 37.572697, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 9, 'http://place.map.kakao.com/1934809750', '오둥이카페4', 126.973268, 37.572698, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 10, 'http://place.map.kakao.com/1934809750', '오둥이카페5', 126.973269, 37.572699, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 11, 'http://place.map.kakao.com/1624572111', '오둥이주차장1', 126.97607241059578, 37.57286713479182, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 12, 'http://place.map.kakao.com/1624572111', '오둥이주차장2', 126.97607241059579, 37.57286713479183, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 13, 'http://place.map.kakao.com/1624572111', '오둥이주차장3', 126.97607241059580, 37.57286713479184, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 14, 'http://place.map.kakao.com/1624572111', '오둥이주차장4', 126.97607241059581, 37.57286713479185, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 15, 'http://place.map.kakao.com/1624572111', '오둥이주차장5', 126.97607241059582, 37.57286713479186, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 16, 'http://place.map.kakao.com/10753713', '오둥이식당6', 126.97607241059583, 37.57286713479187, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 17, 'http://place.map.kakao.com/10753713', '오둥이식당7', 126.97607241059584, 37.57286713479188, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 18, 'http://place.map.kakao.com/1934809750', '오둥이카페6', 126.973270, 37.572700, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 19, 'http://place.map.kakao.com/1934809750', '오둥이카페7', 126.973271, 37.572701, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 20, 'http://place.map.kakao.com/1624572111', '오둥이주차장6', 126.97607241059578, 37.57286713479182, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 21, 'http://place.map.kakao.com/1624572111', '오둥이주차장7', 126.97607241059585, 37.57286713479189, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 22, 'http://place.map.kakao.com/10753713', '오둥이식당22', 126.97607241059586, 37.57286713479190, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 23, 'http://place.map.kakao.com/10753713', '오둥이식당23', 126.97607241059587, 37.57286713479191, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 24, 'http://place.map.kakao.com/10753713', '오둥이식당24', 126.97607241059588, 37.57286713479192, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 25, 'http://place.map.kakao.com/10753713', '오둥이식당25', 126.97607241059589, 37.57286713479193, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 26, 'http://place.map.kakao.com/10753713', '오둥이식당26', 126.97607241059590, 37.57286713479194, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

-- theaterId 가 2인 경우

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 27, 'http://place.map.kakao.com/1934809750', '2오둥이카페1', 126.973265, 37.572695, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 28, 'http://place.map.kakao.com/1934809750', '2오둥이카페2', 126.973266, 37.572696, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 29, 'http://place.map.kakao.com/1934809750', '2오둥이카페3', 126.973267, 37.572697, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 30, 'http://place.map.kakao.com/1934809750', '2오둥이카페4', 126.973268, 37.572698, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 31, 'http://place.map.kakao.com/1934809750', '2오둥이카페5', 126.973269, 37.572699, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 32, 'http://place.map.kakao.com/1624572111', '2오둥이주차장1', 126.97607241059578, 37.57286713479182, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 33, 'http://place.map.kakao.com/1624572111', '2오둥이주차장2', 126.97607241059579, 37.57286713479183, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 34, 'http://place.map.kakao.com/1624572111', '2오둥이주차장3', 126.97607241059580, 37.57286713479184, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 35, 'http://place.map.kakao.com/1624572111', '2오둥이주차장4', 126.97607241059581, 37.57286713479185, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 36, 'http://place.map.kakao.com/1624572111', '2오둥이주차장5', 126.97607241059582, 37.57286713479186, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 37, 'http://place.map.kakao.com/10753713', '2오둥이식당6', 126.97607241059583, 37.57286713479187, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 38, 'http://place.map.kakao.com/10753713', '2오둥이식당7', 126.97607241059584, 37.57286713479188, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 39, 'http://place.map.kakao.com/1934809750', '2오둥이식당8', 126.973270, 37.572700, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 40, 'http://place.map.kakao.com/1934809750', '2오둥이식당9', 126.973271, 37.572701, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 41, 'http://place.map.kakao.com/1624572111', '2오둥이식당10', 126.97607241059585, 37.57286713479189, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 42, 'http://place.map.kakao.com/1624572111', '2오둥이주차장7', 126.97607241059586, 37.57286713479190, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

-- Comment 테이블 생성 코드
INSERT INTO COMMENT (created_at, body, comment_status, member_id, board_id, parent_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '1' HOUR, '댓글입니다1', 'COMMENT_POST', 2, 1, null);

INSERT INTO COMMENT (created_at, body, comment_status, member_id, board_id, parent_id)
VALUES (CURRENT_TIMESTAMP - INTERVAL '30' MINUTE, '대댓글입니다1', 'COMMENT_POST', 1, 1, 1);

INSERT INTO COMMENT (created_at, body, comment_status, member_id, board_id, parent_id)
VALUES (CURRENT_TIMESTAMP, '대댓글입니다2', 'COMMENT_POST', 3, 1, 1);

-- BOARD_LIKE 테이블 생성 코드
INSERT INTO BOARD_LIKE (BOARD_ID, MEMBER_ID) VALUES (1, 1);
INSERT INTO BOARD_LIKE (BOARD_ID, MEMBER_ID) VALUES (4, 1);
INSERT INTO BOARD_LIKE (BOARD_ID, MEMBER_ID) VALUES (6, 1);
INSERT INTO BOARD_LIKE (BOARD_ID, MEMBER_ID) VALUES (7, 1);

-- RecommendPlace 테이블 더미데이터 생성 코드
INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 1, 4.2, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 1, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 2, 4.4, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 2, 4.0, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 3, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 3, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 4, 4.7, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 4, 4.6, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 5, 4.8, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 5, 4.9, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 6, 4.8, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 6, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 7, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 7, 4.1, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 8, 4.4, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 8, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 9, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 9, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 10, 4.2, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 10, 4.9, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 11, 4.8, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 11, 4.9, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 12, 4.8, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 12, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 13, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 13, 4.1, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 14, 4.4, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 14, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 15, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 15, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 16, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 16, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 17, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 17, 4.0, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 18, 4.2, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 18, 4.0, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 19, 4.2, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 19, 4.8, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 20, 4.9, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 20, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 21, 4.0, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 21, 4.0, '한줄평이지롱');

-- theaterId가 2인 경우의 평점 및 한줄평
INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 22, 4.1, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 22, 4.8, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 23, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 23, 4.0, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 24, 4.7, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 24, 4.1, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 25, 4.9, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 25, 4.1, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 26, 4.4, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 26, 4.9, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 27, 4.8, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 27, 4.9, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 28, 4.1, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 28, 4.1, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 29, 4.4, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 29, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 30, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 30, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 31, 4.2, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 31, 4.9, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 32, 4.8, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 32, 4.9, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 33, 4.8, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 33, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 34, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 34, 4.1, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 35, 4.4, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 35, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 36, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 36, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 37, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 37, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 38, 4.5, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 38, 4.0, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 39, 4.2, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 39, 4.0, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 40, 4.2, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 40, 4.8, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 41, 4.9, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 41, 4.3, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (1, 41, 4.0, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (2, 41, 4.0, '한줄평이지롱');

-- MUSICAL 테이블 생성 코드
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (1 , '레드북', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165 , 20 , 20 , 1);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (2 , '영웅', 'Hero', 'https://cdn.mhns.co.kr/news/photo/202210/537323_652071_5036.jpg', 'GENRE_CREATED', '어렵게 구한 브라우닝 권총에 7발의 총알을 장전하고 하얼빈으로 향하는 안중근. 1909년 10월 26일 하얼빈 역, 총성이 울려퍼지는데', 'MUSICAL_ONAIR', '2023.03.17 ', '2023.05.21', 'AGE_12', 165 , 20 , 27 , 3);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (3 , '맘마미아', 'Mammamia', 'https://mblogthumb-phinf.pstatic.net/20141022_260/safari17_1413943258491W1mof_JPEG/20141022_104747.jpg?type=w2', 'GENRE_LICENSED', '도나의 우정과 사랑, 딸 소피의 자아 찾기 그리고 엄마와 딸의 가슴 먹먹한 이야기', 'MUSICAL_ONAIR', '2023.03.24 ', '2023.06.25', 'AGE_12', 165 , 20 , 59 , 2);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (4 , '데스노트', 'DeathNote', 'http://image.toast.com/aaaaab/ticketlink/TKL_8/mdn_main_0227.jpg', 'GENRE_LICENSED', '어느 날 우연히 데스노트를 줍게된 야가미 라이토, 인터폴에서 데스노트에 대한 수사를 하는 탐정 엘의 치열하고 잔인한 두뇌싸움', 'MUSICAL_ONAIR', '2023.03.28', '2023.06.18', 'AGE_12', 165 , 20 , 81 , 2);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (5 , '오페라의유령', 'PhantomOfOpera', 'https://mblogthumb-phinf.pstatic.net/20110818_54/safari17_1313656675363FjsE0_JPEG/evy.jpg?type=w2', 'GENRE_LICENSED', '거부할 수 없는 강렬한 이끌림, 가면 속에 감춰진 아름다운 러브스토리', 'MUSICAL_ONAIR', '2023.03.18', '2023.06.11', 'AGE_15', 165 , 20 , 96 , 2);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (6 , '광염소나타', 'Sonata', 'http://image.newsis.com/2023/02/05/NISI20230205_0001189206_web.jpg', 'GENRE_CREATED', '오랜 친구이자 음악적 뮤즈인 J와 S, J는 어떤 사건으로 인해 엄청난 곡을 써내고 교수 K는 그 사건이 죽음 이란것을 알고 J에게 살인을 부추기는데...', 'MUSICAL_ONAIR', '2023.03.19', '2023.06.04', 'AGE_19', 165 , 20 , 213 , 2);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (7 , '98퍼센트', '98Percent', 'http://www.munhaknews.com/news/photo/202303/71758_22203_5054.jpg', 'GENRE_CREATED', '전쟁과 포스트 아포칼립스 세계관에 놓인 세 인물이 첨예하게 아파하고, 대립하고, 부딪치며 전쟁같은 삶 속에서도 지켜야할 인간다운 삶에 대한 고찰', 'MUSICAL_ONAIR', '2023.03.20', '2023.06.03', 'AGE_15', 165 , 20 , 79 , 2);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (8 , '빨래', 'Laundary', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMTQElKigJq0C-nWx3kb_pcWke06HY14wEy97ic7i60F_d7mmP9V62Bj8wgVuywDFfv30&usqp=CAU', 'GENRE_CREATED', '서울살이 5년차, 나영과 몽골청년 솔롱고. 각자의 마음 속에 아픈 사연을 안고 살아가는 사람들의 이야기', 'MUSICAL_ONAIR', '2023.03.21', '2023.06.10', 'AGE_15', 165 , 20 , 122 , 1);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (9 , '헤드윅', 'Hedwig', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjK9jgTn8_uUmSitZGV5RO3g6rdD3qbZ4Gkw&usqp=CAU', 'GENRE_LICENSED', '트랜스젠더 록 가수의 파란만장한 인생 역정을 그린 록뮤지컬', 'MUSICAL_YET', '2023.05.07', '2023.08.08', 'AGE_19', 165 , 20 , 39 , 1);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (10 , '비밀의화원', 'SecretOfGarden', 'https://cdn.imweb.me/upload/S2020080478ce32d6baa9a/bc62a4d104395.jpg', 'GENRE_CREATED', '1950년대 영국 요크셔의 성 안토니오 보육원. 곧 퇴소를 앞둔 에이미, 비글, 칠리, 데보라. 우연히 비밀의화원이라는 책을 줍고 소설속 등장인물을 따라하자 하는데', 'MUSICAL_YET', '2023.05.01', '2023.07.09', 'AGE_19', 165 , 20 , 83 , 3);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (11 , '레베카', 'Rebecca', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqtvFZKsKjwZJ-X_-0jZYBKP3hOwt66KNhJA&usqp=CAU', 'GENRE_LICENSED', '주인공인 나는 반 호퍼 부인의 말벗이자 시종 역으로 함께 몬테 카를로에 휴양 여행을 오게 된다.', 'MUSICAL_YET', '2023.05.07', '2023.08.31', 'AGE_15', 165 , 20 , 37 , 3);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (12 , '캣츠', 'Cats', 'https://mblogthumb-phinf.pstatic.net/20110819_1/safari17_13137199241153rdrG_JPEG/cats-the-musical-original.jpg?type=w2', 'GENRE_ORIGINAL', '일 년에 한 번 젤리클 달이 뜰 때 젤리클 고양이들이 모여 젤리클 볼 축제를 연다. 거리를 떠돌던 버림받은 고양이 그리자벨라가 이벤트에 뽑혀 새로운삶을 살게 되는데', 'MUSICAL_YET', '2023.04.08', '2023.06.08', 'AGE_15', 165 , 20 , 156 , 1);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (13 , '보이체크인더다크', 'WoyZeckInTheDark', 'https://pbs.twimg.com/media/FmOpSfJaYAIECcI.jpg:large', 'GENRE_CREATED', '뮤덕들이성공하는이야기', 'MUSICAL_ONAIR', '2023.03.07', '2023.06.17', 'AGE_15', 165 , 20 , 270 , 3);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (14 , '이상한나라의엘리스', 'WonderlandOfAlice', 'https://www.mcst.go.kr/attachFiles/cultureInfoCourt/monthServ/1485995755916.jpg', 'GENRE_ORIGINAL', '덕들이성공하는이야기뮤', 'MUSICAL_ONAIR', '2023.03.08', '2023.06.26', 'AGE_0' , 165 , 20 , 223 , 3);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (15 , '윌리엄과윌리엄의윌리엄들', 'WilliamAndWilliamsWillams', 'https://image.yes24.com/images/chyes24/9/1/8/f/918f342093edbc811cf929f6c698fa2c.jpg', 'GENRE_CREATED', '들이성공하는이야기뮤덕', 'MUSICAL_ONAIR', '2023.03.09', '2023.07.05', 'AGE_15', 165 , 20 , 46 , 2);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (16 , '해적', 'Pairots', 'https://pbs.twimg.com/media/E0RUV6BUUAAcaAg.jpg:large', 'GENRE_CREATED', '이성공하는이야기뮤덕들', 'MUSICAL_ONAIR', '2023.03.10', '2023.07.14', 'AGE_15' , 165 , 20 , 187 , 1);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (17 , '인터뷰', 'Interview', 'https://mblogthumb-phinf.pstatic.net/20161013_258/safari17_14763153664130VkJn_JPEG/%C0%CE%C5%CD%BA%E41.jpg?type=w2', 'GENRE_CREATED', '성공하는이야기뮤덕들이', 'MUSICAL_FINISH', '2023.01.01', '2023.03.05', 'AGE_15', 165 , 20 , 186 , 1);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (18 , '식스더뮤지컬', 'SixTheMusical', 'https://image.yes24.com/themusical//fileStorage/ThemusicalAdmin/Editor/2023/1/3/2023010397085951f0b9b821e70eabe1e9b483b1d8c18706.jpg', 'GENRE_LICENSED', '공하는이야기뮤덕들이성', 'MUSICAL_ONAIR', '2023.03.10', '2023.06.03', 'AGE_15', 165 , 20 , 206 , 3);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (19 , '실비아,살다', 'Silvia,Live', 'https://m.segye.com/content/image/2022/05/04/20220504516324.jpg', 'GENRE_CREATED', '하는이야기뮤덕들이성공', 'MUSICAL_ONAIR', '2023.03.11', '2023.06.10', 'AGE_15', 165 , 20 , 45 , 2);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (20 , '로빈', 'Roubbin', 'https://pbs.twimg.com/media/EWCatlFUYAAFpgT?format=jpg&name=large', 'GENRE_CREATED', '는이야기뮤덕들이성공하', 'MUSICAL_ONAIR', '2023.03.12', '2023.08.08', 'AGE_15', 165 , 20 , 2 , 2);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (21 , '데미안', 'Demian', 'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Play/Image/202301063037300249e20b8766c85b92858bdf473be281af.jpg', 'GENRE_CREATED', '이야기뮤덕들이성공하는', 'MUSICAL_FINISH', '2023.02.10', '2023.03.17', 'AGE_12', 165 , 20 , 276 , 3);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (22 , '어린왕자', 'ALittlePrince', 'http://tkfile.yes24.com/upload2/PerfBlog/201904/20190408/20190408-32293_1.jpg', 'GENRE_CREATED', '야기뮤덕들이성공하는이', 'MUSICAL_ONAIR', '2023.03.07', '2023.06.08', 'AGE_12', 165 , 20 , 277 , 3);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (23 , '하트시그널', 'HeartSighnal', 'http://tkfile.yes24.com/upload2/PerfBlog/201907/20190712/20190712-31589.jpg', 'GENRE_CREATED', '기뮤덕들이성공하는이야', 'MUSICAL_ONAIR', '2023.03.08', '2023.06.17', 'AGE_15', 165 , 20 , 53 , 3);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (24 , '일라이', 'ELI', 'https://pbs.twimg.com/media/FlgRvOEagAAgxUY?format=jpg&name=4096x4096', 'GENRE_CREATED', '뮤덕들이', 'MUSICAL_ONAIR', '2023.03.09', '2023.06.26', 'AGE_15' , 165 , 20 , 282 , 1);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (25 , '라흐마나노프', 'Rachmaninoff', 'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Play/Image/20200130060624cc97bab954d349b7beb545ca73fe3338.jpg', 'GENRE_CREATED', '성공하는', 'MUSICAL_ONAIR', '2023.03.10', '2023.07.05', 'AGE_15', 165 , 20 , 276 , 2);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (26 , '써니텐', 'SunnyTen', 'https://mcst.go.kr/attachFiles/cultureInfoCourt/monthServ/1678166738724.jpg', 'GENRE_CREATED', '이야기', 'MUSICAL_FINISH', '2022.12.01', '2023.01.18', 'AGE_15', 165 , 20 , 178 , 3);


-- ACTOR 테이블 생성코드
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES (1, '차지연', 'http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/07/01/0400040701_4131_012.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES (2, '아이비', 'http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/07/01/0400040701_4131_013.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES (3, '김세정', 'http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/07/01/0400040701_4131_015.jpg', 'ACTOR_DELETE');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES (4, '송원근', 'http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/07/01/0400040701_4131_016.jpg', 'ACTOR_CONFIRM');

-- ACTOR_MUSICAL 테이블 생성 코드
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES (1, 1, 1, '안나');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES (2, 2, 1, '안나');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES (3, 3, 1, '안나');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES (4, 4, 2, '브라운');

-- Category 테이블 생성 코드
INSERT INTO Category (category_name, parent_id) VALUES ('자유주제', NULL);
INSERT INTO Category (category_name, parent_id) VALUES ('공연정보/후기', NULL);
INSERT INTO Category (category_name, parent_id) VALUES ('시설정보', NULL);

INSERT INTO Category (category_name, parent_id, musical_id) VALUES ('2014 레베카', 2, 11);
INSERT INTO Category (category_name, parent_id, musical_id) VALUES ('2017 레베카', 2, 12);
INSERT INTO Category (category_name, parent_id, musical_id) VALUES ('2019 헤드윅', 2, 9);

-- BOARD_CATEGORY 테이블 생성 코드
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (1, 1);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (2, 2);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (2, 4);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (3, 3);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (4, 1);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (5, 1);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (6, 2);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (6, 5);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (7, 3);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (8, 1);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (9, 1);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (10, 2);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (10, 4);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (11, 3);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (12, 1);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (13, 1);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (14, 2);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (14, 5);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (15, 3);
INSERT INTO BOARD_CATEGORY (board_id, category_id) VALUES (16, 1);