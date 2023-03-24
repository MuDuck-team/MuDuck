
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
VALUES (1, 10753713,'http://place.map.kakao.com/10753713', '오둥이식당1', 126.97607241059578, 37.57286713479182, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 10753713,'http://place.map.kakao.com/10753713', '오둥이식당2', 126.97607241059578, 37.57286713479182, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 10753713,'http://place.map.kakao.com/10753713', '오둥이식당3', 126.97607241059578, 37.57286713479182, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 10753713,'http://place.map.kakao.com/10753713', '오둥이식당4', 126.97607241059578, 37.57286713479182, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 10753713,'http://place.map.kakao.com/10753713', '오둥이식당5', 126.97607241059578, 37.57286713479182, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());


INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1934809750,'http://place.map.kakao.com/1934809750', '오둥이카페1', 126.973265, 37.572695, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1934809750,'http://place.map.kakao.com/1934809750', '오둥이카페2', 126.973265, 37.572695, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1934809750,'http://place.map.kakao.com/1934809750', '오둥이카페3', 126.973265, 37.572695, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1934809750,'http://place.map.kakao.com/1934809750', '오둥이카페4', 126.973265, 37.572695, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1934809750,'http://place.map.kakao.com/1934809750', '오둥이카페5', 126.973265, 37.572695, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1624572111,'http://place.map.kakao.com/1624572111', '오둥이주차장1', 126.97607241059578, 37.57286713479182, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1624572111,'http://place.map.kakao.com/1624572111', '오둥이주차장2', 126.97607241059578, 37.57286713479182, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1624572111,'http://place.map.kakao.com/1624572111', '오둥이주차장3', 126.97607241059578, 37.57286713479182, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1624572111,'http://place.map.kakao.com/1624572111', '오둥이주차장4', 126.97607241059578, 37.57286713479182, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1624572111,'http://place.map.kakao.com/1624572111', '오둥이주차장5', 126.97607241059578, 37.57286713479182, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 10753713,'http://place.map.kakao.com/10753713', '오둥이식당6', 126.97607241059578, 37.57286713479182, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 10753713,'http://place.map.kakao.com/10753713', '오둥이식당7', 126.97607241059578, 37.57286713479182, 'FD6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1934809750,'http://place.map.kakao.com/1934809750', '오둥이카페6', 126.973265, 37.572695, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1934809750,'http://place.map.kakao.com/1934809750', '오둥이카페7', 126.973265, 37.572695, 'CE7', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1624572111,'http://place.map.kakao.com/1624572111', '오둥이주차장6', 126.97607241059578, 37.57286713479182, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 1624572111,'http://place.map.kakao.com/1624572111', '오둥이주차장7', 126.97607241059578, 37.57286713479182, 'PK6', '02-123-4567', '서울시 어쩌구', '도로명주소입니다.', NOW(), NOW());

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

-- MUSICAL 테이블 생성 코드
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views,theater_id)
VALUES (1, '레드북', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20,1);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (2, '웃는남자', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_YET', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (3, '레드북1', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (4, '웃는남자1', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_FINISH', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (5, '레드북2', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (6, '웃는남자2', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_YET', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (7, '레드북3', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_FINISH', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (8, '웃는남자3', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_FINISH', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (9, '레드북4', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,7);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (10, '웃는남자4', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_YET', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (11, '레드북5', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (12, '웃는남자5', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_YET', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (13, '레드북6', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_FINISH', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (14, '웃는남자6', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_YET', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 12);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (15, '레드북7', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (16, '웃는남자7', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_FINISH', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (17, '레드북8', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (18, '웃는남자8', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_YET', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (19, '레드북9', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (20, '웃는남자9', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_YET', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 453);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (21, '레드북10', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_FINISH', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (22, '웃는남자10', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_YET', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (23, '레드북11', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,73);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (24, '웃는남자11', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_YET', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (25, '레드북12', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (26, '웃는남자12', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_YET', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (27, '레드북13', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_FINISH', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,124);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (28, '웃는남자13', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_YET', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 100);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (29, '레드북14', 'RedBook', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_CREATED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 배경으로 한 레드북은 여자들이 쓰는 소설이 담긴 잡지가 사회적 비난에 부딪히면서 시작되는 이야기이다.', 'MUSICAL_ONAIR', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20,20);
INSERT INTO MUSICALS (musical_id, musical_kor_name, musical_eng_name, poster,  genre, musical_info, musical_state, open_date, close_date, age, running_time, intermission, views)
VALUES (30, '웃는남자14', 'The Man Who Laughs', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_3sIuFymBnqVMUCgT8kS5-wXzRYt9gBqsla2S0LGD&s', 'GENRE_LICENSED', '중세에서 근대로 바뀌는 빅토리아 시대에 영국 런던시를 ', 'MUSICAL_FINISH', '2023.03.14', '2023.05.28', 'AGE_19', 165, 20, 123);


-- ACTOR 테이블 생성코드
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES (1, '차지연', 'http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/07/01/0400040701_4131_012.jpg','ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES (2, '아이비', 'http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/07/01/0400040701_4131_013.jpg','ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES (3, '김세정', 'http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/07/01/0400040701_4131_015.jpg','ACTOR_DELETE');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES (4, '송원근', 'http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/07/01/0400040701_4131_016.jpg','ACTOR_CONFIRM');

-- ACTOR_MUSICAL 테이블 생성 코드
INSERT INTO ACTORMUSICALS (musical_actor_id, actor_id, musical_id, role) VALUES (1, 1, 1,'안나');
INSERT INTO ACTORMUSICALS (musical_actor_id, actor_id, musical_id, role) VALUES (2, 2, 1,'안나');
INSERT INTO ACTORMUSICALS (musical_actor_id, actor_id, musical_id, role) VALUES (3, 3, 1,'안나');
INSERT INTO ACTORMUSICALS (musical_actor_id, actor_id, musical_id, role) VALUES (4, 4, 2,'브라운');