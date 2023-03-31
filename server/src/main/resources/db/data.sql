
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
VALUES ('예술의 전당', 127.0139129, 37.4792683, '02-555-5555', '서울 서초구 서초동 700', '서울 서초구 남부순환로 2406', NOW(), NOW());

INSERT INTO THEATER (place_Name, longitude, latitude, phone, address, road_Address, created_at, last_modified_at)
VALUES ('샤롯데씨어터', 127.0998494, 37.5107077, '02-555-5555', '서울특별시 송파구 잠실동 40-1번지 샤롯데씨어터', '서울특별시 송파구 올림픽로 240(잠실동) 샤롯데씨어터', NOW(), NOW());

INSERT INTO THEATER (place_Name, longitude, latitude, phone, address, road_Address, created_at, last_modified_at)
VALUES ('충무아트센터', 127.0148131, 37.5660139, '02-555-5555', '서울 중구 흥인동 131', '서울 중구 퇴계로 387', NOW(), NOW());

-- Map 테이블 생성 코드
INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 7885551, 'http://place.map.kakao.com/7885551', '백년옥 본관', 127.01377086131225, 37.48118504695074, 'FD6', '02-523-2860', '서울 서초구 서초동 1450-6', '서울 서초구 남부순환로 2407', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 982112444, 'http://place.map.kakao.com/982112444', '카페모차르트502', 127.01219547315105, 37.47844795409167, 'CE7', '02-522-4916', '서울 서초구 서초동 산 130-9', '서울 서초구 남부순환로 2406', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (1, 98733515, 'http://place.map.kakao.com/98733515', '서초센트럴주차장', 127.011058140081, 37.4862670205377, 'PK6', '02-6218-1582', '서울 서초구 서초동 1582-25', '서울 서초구 반포대로16길 7', NOW(), NOW());

-- theaterId 가 2인 경우

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 13279465, 'http://place.map.kakao.com/13279465', '롯데호텔월드 라세느', 127.10002034592019, 37.511399307126595, 'FD6', '02-411-7811', '서울 송파구 잠실동 40-1', '서울 송파구 올림픽로 240', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (2, 21547548, 'http://place.map.kakao.com/21547548', '엔제리너스 석촌호수DI점', 127.101885393436, 37.5071774088431, 'CE7', '02-3431-7590', '서울 송파구 석촌동 158-10', '서울 송파구 석촌호수로 224', NOW(), NOW());

-- INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
-- VALUES (2, 98733515, 'http://place.map.kakao.com/98733515', '서초센트럴주차장', 127.011058140081, 37.4862670205377, 'PK6', '02-6218-1582', '서울 서초구 서초동 1582-25', '서울 서초구 반포대로16길 7', NOW(), NOW());

-- theaterId 가 3인 경우
INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (3, 954591175, 'http://place.map.kakao.com/954591175', '동대문매운김밥', 127.015909856096, 37.5658357671463, 'FD6', '02-2231-0582', '서울 중구 흥인동 111', '서울 중구 다산로 255', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (3, 38432219, 'http://place.map.kakao.com/38432219', '러시아 케이크', 127.005885831317, 37.5658294844621, 'CE7', '02-6053-4079', '서울 중구 광희동1가 134', '서울 중구 을지로42길 7', NOW(), NOW());

INSERT INTO MAP (theater_id, place_id, place_url, place_name, longitude, latitude, category_group_code, phone, address, road_address, created_at, last_modified_at)
VALUES (3, 26879266, 'http://place.map.kakao.com/26879266', '동대문공영주차장', 127.01211146254752, 37.56726699946685, 'PK6', '02-6053-4079', '서울 중구 신당동 251-7', '서울 중구 마장로 22', NOW(), NOW());

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
VALUES (3, 1, 4.0, '맛이 변해버렸구만유...');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (3, 2, 4.0, '이름이 먼가 맘에 들어유');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (3, 3, 4.0, '센트럴!');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (3, 4, 4.0, '비싸지만 낫베드');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (3, 5, 3.0, '프렌차이즈는 중간!');

-- INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
-- VALUES (3, 6, 4.4, '한줄평이지롱');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (3, 6, 5.0, '오둥이도 반한 맛집이무니다.');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (3, 7, 4.0, '오둥이도 반한 카페 츄라이츄라이');

INSERT INTO RECOMMEND_PLACE(member_id, map_id, score, one_line)
VALUES (3, 8, 3.0, '주차비 너무 비싸!!!');



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
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 1, '옥주현', 'https://images2.imgbox.com/57/f4/8Lhd0e4M_o.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 2, '박진주', 'http://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/01/28/31c47138-f149-4753-9499-a9c22206061f.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 3, '민경아', 'https://isplus.com/data/isp/image/2022/06/29/isp522999b7-a0b1-4be7-ba1b-3f96908e2c6c.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 4, '송원근', 'https://pbs.twimg.com/media/E0m5TdzUUAUoxJ7.jpg:large', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 5, '신성민', 'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Player/Image/20200331115558a836f28821f24a9293c951f50297d561.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 6, '김성규', 'https://static.wixstatic.com/media/0366e2_f502c5579f944bca9341b1fae59bfff1~mv2.jpg/v1/fill/w_602, h_784, al_c, q_85, usm_0.66_1.00_0.01, enc_auto/1%20(4).jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 7, '정성화', 'https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosunbiz/CHLY7GPUJRN44PU34EMYLVWYBI.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 8, '양준모', 'https://www.kukinews.com/data/kuk/image/2022/10/21/kuk202210210239.680x.0.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 9, '민우혁', 'https://img6.yna.co.kr/etc/inner/KR/2020/10/12/AKR20201012169900005_03_i_P2.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 10, '연지현', 'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Player/Image/20191107040015547addc0ff13432f9cedb4343465b36a.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 11, '홍광호', 'https://entertainimg.kbsmedia.co.kr/cms/uploads/PERSON_20220330132407_3184805703c0ee3fd1ddd22b578854db.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 12, '고은성', 'https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/2X4MEXNWPBD3NHSVAQ25PC2JYI.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 13, '최재웅', 'https://pbs.twimg.com/media/FCB-AG4VcAsdcWT.jpg:large', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 14, '김성철', 'https://entertainimg.kbsmedia.co.kr/cms/uploads/PERSON_20211201154636_0d197ae1d1d439902f74f7868470e1d2.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 15, '전동석', 'https://newsimg.hankookilbo.com/cms/articlerelease/2021/05/10/71e70cb9-26e4-4a60-a368-a25b52eb741a.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 16, '박은태', 'https://image.xportsnews.com/contents/images/upload/article/2022/0111/mb_1641867033910945.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 17, '우태희', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 18, '육경득', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 19, '로또', 'https://cdn.discordapp.com/attachments/1082696590764032182/1089586710687514654/IMG_0731.PNG', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 20, '반달이', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 21, '이승연', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 22, '오둥이', 'https://item.kakaocdn.net/do/5b34b1daf86560a096706d550eaa2a28d0bbab1214a29e381afae56101ded106', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 23, '김관우', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 24, '박수빈', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 25, '이효근', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 26, '신영숙', 'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Player/Image/20160630064202T8M7075718L8T560.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 27, '유리아', 'https://pbs.twimg.com/media/FW3KAkgVsAALCj9.jpg:large', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 28, '조승우', 'https://pbs.twimg.com/media/FMz-V6YacAM5hJB.jpg:large', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 29, '박효신', 'https://file2.nocutnews.co.kr/newsroom/image/2013/07/25/20130725152430687362.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 30, '최재림', 'https://image.yes24.com/themusical/upFiles/StageTalkV2/Editor/2019/3/8/20190308040625d7d3171ffde14c9790647526ee6a2bc0.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 31, '한지상', 'https://img.wowtv.co.kr/wowtv_news/dnrs/20220404/2022040410412609647d3244b4fed58141237161.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 32, '안유진', 'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Player/Image/201607230939103Q544XXBRQNU1XJW.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 33, '차지연', 'https://www.m-i.kr/news/photo/202207/932602_696864_3856.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 34, '이건명', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3Z7KyfJg1wdynKLLQOPbEdekhwnOmx58WPZbJ8nzlJe60_okzZt3ZHfWJl28xOufeMTI&usqp=CAU', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 35, '오만석', 'https://pbs.twimg.com/profile_images/1150923799954505729/0UTiXNIo_400x400.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 36, '박건형', 'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Player/Image/20200925015456c765c56092514a318be918b4d433ab7f.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 37, '이영미', 'https://img.khan.co.kr/news/2020/06/12/2020060901001130600085741.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 38, '김선영', 'http://image.newsis.com/2021/12/31/NISI20211231_0000904537_web.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 39, '윤공주', 'https://yaksonhouse.com/kor/data/cheditor4/1901/6ed0c3024bc9afbd6881f79c7f6b3aec_20190114173142_tuslfmrc.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 40, '옥주현', 'https://images2.imgbox.com/57/f4/8Lhd0e4M_o.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 41, '박진주', 'http://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/01/28/31c47138-f149-4753-9499-a9c22206061f.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 42, '민경아', 'https://isplus.com/data/isp/image/2022/06/29/isp522999b7-a0b1-4be7-ba1b-3f96908e2c6c.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 43, '송원근', 'https://pbs.twimg.com/media/E0m5TdzUUAUoxJ7.jpg:large', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 44, '신성민', 'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Player/Image/20200331115558a836f28821f24a9293c951f50297d561.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 45, '김성규', 'https://static.wixstatic.com/media/0366e2_f502c5579f944bca9341b1fae59bfff1~mv2.jpg/v1/fill/w_602, h_784, al_c, q_85, usm_0.66_1.00_0.01, enc_auto/1%20(4).jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 46, '정성화', 'https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosunbiz/CHLY7GPUJRN44PU34EMYLVWYBI.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 47, '양준모', 'https://www.kukinews.com/data/kuk/image/2022/10/21/kuk202210210239.680x.0.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 48, '민우혁', 'https://img6.yna.co.kr/etc/inner/KR/2020/10/12/AKR20201012169900005_03_i_P2.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 49, '연지현', 'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Player/Image/20191107040015547addc0ff13432f9cedb4343465b36a.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 50, '홍광호', 'https://entertainimg.kbsmedia.co.kr/cms/uploads/PERSON_20220330132407_3184805703c0ee3fd1ddd22b578854db.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 51, '고은성', 'https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/2X4MEXNWPBD3NHSVAQ25PC2JYI.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 52, '최재웅', 'https://pbs.twimg.com/media/FCB-AG4VcAsdcWT.jpg:large', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 53, '김성철', 'https://entertainimg.kbsmedia.co.kr/cms/uploads/PERSON_20211201154636_0d197ae1d1d439902f74f7868470e1d2.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 54, '전동석', 'https://newsimg.hankookilbo.com/cms/articlerelease/2021/05/10/71e70cb9-26e4-4a60-a368-a25b52eb741a.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 55, '박은태', 'https://image.xportsnews.com/contents/images/upload/article/2022/0111/mb_1641867033910945.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 56, '우태희', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 57, '육경득', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 58, '로또', 'https://cdn.discordapp.com/attachments/1082696590764032182/1089586710687514654/IMG_0731.PNG', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 59, '반달이', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 60, '이승연', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 61, '오둥이', 'https://item.kakaocdn.net/do/5b34b1daf86560a096706d550eaa2a28d0bbab1214a29e381afae56101ded106', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 62, '김관우', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 63, '박수빈', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 64, '이효근', 'https://cdn.discordapp.com/attachments/1080995426485948426/1089878561889976390/image.png', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 65, '신영숙', 'https://image.yes24.com/themusical/fileStorage/ThemusicalAdmin/Player/Image/20160630064202T8M7075718L8T560.jpg', 'ACTOR_CONFIRM');
INSERT INTO ACTORS (actor_id, actor_name, picture, actor_state) VALUES ( 66, '유리아', 'https://pbs.twimg.com/media/FW3KAkgVsAALCj9.jpg:large', 'ACTOR_CONFIRM');

-- ACTOR_MUSICAL 테이블 생성 코드
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 1, 55, 5, '안나');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 2, 55, 4, '안나');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 3, 37, 19, '안중근');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 4, 4, 12, '안중근');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 5, 59, 16, '드 윈터 부인');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 6, 40, 5, '드 윈터 부인');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 7, 35, 20, '브라운');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 8, 42, 22, '브라운');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 9, 50, 25, '오페라의 유령');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 10, 22, 12, '오페라의 유령');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 11, 61, 18, '크리스틴');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 12, 13, 13, '크리스틴');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 13, 32, 13, '막심 드 윈터');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 14, 18, 9, '막심 드 윈터');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 15, 52, 8, '비아트리세');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 16, 14, 11, '비아트리세');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 17, 62, 10, '헤드윅 로빈슨');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 18, 51, 1, '헤드윅 로빈슨');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 19, 5, 7, '이츠학');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 20, 22, 7, '이츠학');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 21, 48, 21, '드라큘라 백작');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 22, 47, 4, '드라큘라 백작');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 23, 15, 7, '아브라함 반 헬싱');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 24, 63, 4, '아브라함 반 헬싱');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 25, 37, 5, '미나 머레이');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 26, 22, 18, '미나 머레이');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 27, 63, 10, '서나영');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 28, 6, 6, '서나영');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 29, 58, 11, '솔롱고');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 30, 55, 17, '솔롱고');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 31, 31, 17, '도나 세리던');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 32, 45, 13, '도나 세리던');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 33, 1, 3, '소피 세리던');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 34, 11, 10, '소피 세리던');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 35, 22, 6, '로지 밀리건');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 36, 19, 10, '로지 밀리건');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 37, 57, 8, '싱클레어');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 38, 62, 26, '싱클레어');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 39, 49, 14, '데미안');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 40, 7, 5, '데미안');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 41, 30, 20, '크로머');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 42, 20, 18, '크로머');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 43, 32, 11, '에이미');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 44, 54, 17, '에이미');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 45, 21, 17, '메리 레녹스');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 46, 25, 13, '메리 레녹스');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 47, 63, 8, '찰리');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 48, 12, 22, '찰리');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 49, 51, 2, '콜린 크레이븐');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 50, 39, 6, '콜린 크레이븐');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 51, 54, 2, '비글');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 52, 54, 11, '비글');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 53, 54, 17, '디콘 소어비');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 54, 4, 7, '디콘 소어비');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 55, 9, 13, '데보라');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 56, 58, 14, '데보라');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 57, 29, 14, '마사 소어비');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 58, 24, 4, '마사 소어비');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 59, 18, 10, 'J');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 60, 18, 16, 'J');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 61, 22, 8, 'S');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 62, 40, 1, 'S');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 63, 62, 19, 'K');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 64, 1, 17, 'K');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 65, 31, 14, 'L');
INSERT INTO ACTORMUSICALS (actor_musical_id, actor_id, musical_id, role) VALUES ( 66, 52, 8, 'L');

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