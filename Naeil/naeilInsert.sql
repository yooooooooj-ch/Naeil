-- 사업자 (users_type = 1, signum 1001 부터)
INSERT INTO users (users_signum, users_name, users_id, users_password, users_phone, users_email, users_type) VALUES (1, '김사업', 'bizkim01', STANDARD_HASH('pass1234','SHA256'), '01012345678', 'bizkim@example.com', 1);
INSERT INTO users (users_signum, users_name, users_id, users_password, users_phone, users_email, users_type) VALUES (2, '이사장', 'leeowner', STANDARD_HASH('pass1234','SHA256'), '01087654321', 'leeowner@example.com', 1);

-- 일반 사용자 (users_type = 2, signum 2001부터)
INSERT INTO users (users_signum, users_name, users_id, users_password, users_phone, users_email, users_type) VALUES (3, '홍길동', 'honggd', STANDARD_HASH('pass1234','SHA256'), '01011112222', 'honggd@example.com', 2);
INSERT INTO users (users_signum, users_name, users_id, users_password, users_phone, users_email, users_type) VALUES (4, '신사임당', 'shinsa', STANDARD_HASH('pass1234','SHA256'), '01033334444', 'shinsa@example.com', 2);
INSERT INTO users (users_signum, users_name, users_id, users_password, users_phone, users_email, users_type) VALUES (5, '유관순', 'yugwansun', STANDARD_HASH('pass1234','SHA256'), '01055556666', 'yugwan@example.com', 2);




INSERT INTO store VALUES (1, '육즙 좔좔 스테이크', '/steak/0_st.jpg', '스테이크 전문점', '서울 강남구 논현로 150길', '저희 ''육즙 좔좔 스테이크'' 는 최고급 등심, 안심, 채끝살으로 딱 한 입 먹었을때 육즙이 좔좔 나온다고 자부 할 수 있습니다', '02-1111-2222', 101);
INSERT INTO store VALUES (2, '얌얌 파스타', '/pasta/0_spaghetti.jpg', '파스타 전문점', '서울 강남구 강남대로 98길', '저희 ''얌얌 파스타''의 대표 메뉴는 아라비아따로 토마토소스와 페페로치노라는 이탈리아의 매운 붉은 고추를 넣어 만든겁니다 아주 탄력적인 면발과 획기적인 맛을 내 최상의 맛으로 보답합니다', '02-3333-4444', 102);
INSERT INTO store VALUES (3, '1988 떡볶이', '/ddeokbokki/0_tteokbokki.jpg', '분식 전문점', '서울 중구 신당동 241-137', '저희 ''1988 떡볶이''는 레트로에 초점을 맞춰 소스부터 익힘까지 옛날 시장 떡볶이로 맛을 내고 있습니다<br>(◍´͈ ᵕ `͈ ◍)', '02-3333-4444', 102);
INSERT INTO store VALUES (4, '스시 한 판 어때?', '/sushi/0_sushi.jpg', '초밥 전문점', '서울 송파구 올림픽로 300', '저희 ''스시 한 판 어때?''는 회전초밥 집으로 골라 먹는 재미가 있고 일본에서 직수입 해 아주 싱싱한 스시로 내어드립니다 <br>(՞⸝⸝ᵒ̴̶̷᷄꒳ᵒ̴̶̷᷅⸝⸝՞)💕', '02-1111-2222', 101);
INSERT INTO store VALUES (5, '베리 굿데이', '/berry/0_mixed-fruits.jpg', '빙수 전문점', '서울 마포구 와우산로 29길', '저희 ''베리굿데이''는!!!! 신선하고 당도 높은 과일을 사용해 <br>빙수랑 여러가지 디저트류를 만드는 분위기 디저트 카페입니다', '02-1111-2222', 101);
INSERT INTO store VALUES (6, '빵집에서 만나요', '/bread/0_bread.jpg', '베이커리 카페', '서울 성동구 성수이로 7가길 9', '저희 ''빵 집에서 만나요''는 수제로 빵을 만들어 갓 구워내서 내놓기 때문에 아주 부드럽고 맛 좋은 빵을 맛 보실 수 있답니당~!~!~!','02-1111-2222', 101);

-- 9. menu 진짜 데이터 삽입
-- 육즙 좔좔 스테이크 (store_id = 1)
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (1, 1, '로얄 채끝 스테이크', 45000, '/steak/1_royal_starch_steak.jpg', '채끝살은 소의 등심과 안심 사이에 위치한 부위로, 지방이 적당히 분포 되어 부드럽고 풍미가 뛰어나고 저희는 일반적으로 소금과 후추로 간을 한 후 그릴에 구워내는 방식으로 조리합니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (2, 1, '블랙스톤 안심', 79000, '/steak/2_blackstone_steak.jpg', '안심은 기본적으로 부드럽고 육즙이 풍부한 고급 부위로 고온의 스톤 플레이트에 200도 이상까지 뜨겁게 예열해 굽는 방식으로 돌판위에는 올리브오일을 살짝 바른 후 고객님의 굽기에 맞게 제공이 되어 따뜻한 상태를 유지 합니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (3, 1, '불맛 채끝', 50000, '/steak/3_fire_steak.jpg', '불맛을 내기 위하여 시어링 후, 버터와 마늘, 로즈마리를 넣고 고기에 향을 입힌 다음에 토치로 표면을 빠르게 지져서 불향을 추가합니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (4, 1, '갈릭버터 스테이크', 49000, '/steak/4_butter_galic_steak.jpg', '안심으로 만들어지며 겉면이 노릇하게 익었을때 버터 2숟갈을 떠서 고기 위에 반복적으로 끼얹고 마지막으로 슬라이스 마늘 로즈마리를 넣고 향을 내줍니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (5, 1, '트러플 풍미 채끝', 49000, '/steak/5_truffle_steak.jpg', '트러플 소금을 사용하여 + 후추와 같이 밑간을 합니다 고기를 구운 다음 트러플 오일 2방울 정도 해줍니다 그리고 트러플 소금을 살짝 더 뿌려서 트러플 맛이 잘 날 수 있도록 해줍니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (6, 1, '콜라', 5000, '/steak/6_pepsi.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (7, 1, '사이다', 5000, '/steak/7_sprite.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (8, 1, '발렌타인 20년산', 200000, '/steak/8_valentine_wine.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (9, 1, '생맥주', 8000, '/steak/9_beer.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (10, 1, '리슬링', 50000, '/steak/10_riesling_wine.jpg', null);

-- 얌얌 파스타 (store_id = 2)
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (11, 2, '아라비아따 파스타', 13000, '/pasta/1_arabita_pasta.jpg', '면은 국내산으로 사용하고 있어 아라비아따 소스는 토마토를 넣어서 수제로 만들고 조금 매콤하게 하기 위해 페페론치노 갯수로 조절을 합니다 그리고 바질까지 추가해 풍미를 더 확 느끼게 해드립니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (12, 2, '크림파스타', 13000, '/pasta/2_cream_pasta.jpg', '버터를 녹여 다진마늘로 향을 내 생크림과 우유를 넣고 만듭니다 저희는 파르메산 치즈를 넣고 소스에 풍미를 더해줍니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (13, 2, '봉골레 파스타', 15000, '/pasta/3_seafood_pasta.jpg', '조개가 들어간 봉골레 파스타 입니다 마늘 슬라이스와 페페론치노를 넣고 하며 조개와 화이트와인을 추가해 조개를 조리 해줍니다 마지막으론 레몬즙을 살짝 뿌려 상큼함을 더해드립니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (14, 2, '오븐 토마토 파스타', 15000, '/pasta/4_tomato_pasta.jpg', '토마토를 구워내며 올리브유와 허브를 추가해 풍비를 더 하게 만듭니다 그리고 치즈가 어우러져 깊으 맛을 느낄 수 있습니다
오븐을 180도로 예열해 구워서 오븐용 그라탱 용기에 담고 모차렐라 치즈와 파르메산 치즈를 골고루 뿌려 드립니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (15, 2, '알리오 올리오 파스타', 14000, '/pasta/5_alio-olio_pasta.jpg', '올리브유를 두르고 마늘 슬라이스와 건고추를 넣어 소스를 만들어줍니다 파슬리를 넣어 향을 더해주고 고소한 파르메산 치즈를 뿌려서 맛을 더해줍니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (16, 2, '콜라', 2000, '/pasta/6_pepsi.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (17, 2, '사이다', 2000, '/pasta/7_sprite.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (18, 2, '제로콜라', 2000, '/pasta/8_cocacola-zero.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (19, 2, '레몬에이드', 4000, '/pasta/9_lemonade.png', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (20, 2, '청포도 에이드', 4000, '/pasta/10_green_grape_ade.jpg', null);

-- 1988 떡볶이 (store_id = 3)
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (21, 3, '떡볶이', 3000, '/ddeokbokki/1_tteokbokki.jpg', '떡을 직접 생산해 아주 쫄깃하고 탱탱한 떡을 맛 보실 수 있고 갓 해서 나와 따뜻하게 드실 수 있게 만들었습니다 
');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (22, 3, '오뎅', 500, '/ddeokbokki/2_fishcake.jpg', '다시마로 국물을 우려내 깊은 맛을 더하고 마지막으로 간장으로 간을 조절 해 더욱 맛있게 드실 수 있습니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (23, 3, '물떡', 500, '/ddeokbokki/3_rice-cake.jpg', '오뎅 국물에 같이 놔두어 떡 안으로 간이 잘 베이게 하며 드실때는 간장과 함께 찍어 드시면 더욱 맛있게 드실 수 있습니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (24, 3, '순대', 3000, '/ddeokbokki/4_sundae.jpg', '찜기에 찌고 있으며 맛을 느끼실때 더욱 쫀득하게 드실 수 있게 드실때 잘라서 드리고 있습니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (25, 3, '모듬튀김', 5000, '/ddeokbokki/5_fried_food.jpg', '오징어 튀김, 새우튀김, 고구마 튀김, 김말이, 야채까지 모듬튀김으로 나가며 튀김 옷은 얇게 하여 만듭니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (26, 3, '츄러스', 1000, '/ddeokbokki/6_churros.jpg', '밀가루 반죽으로 하여 튀겨내어 튀김같은 바삭함으로 설탕을 묻힙니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (27, 3, '우동', 5000, '/ddeokbokki/7_udon.jpg', '다시마로 1차 육수를 내며 2차로는 가쓰오부시로 육수를 내 더욱 맛에 풍미가 가득할 수 있도록 합니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (28, 3, '콜라', 1000, '/ddeokbokki/8_pepsi.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (29, 3, '사이다', 1000, '/ddeokbokki/9_sprite.jpg', null);

-- 스시 한 판 어때? (store_id = 4)
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (30, 4, '흰색', 1400, '/sushi/1_white_dishes.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (31, 4, '파란색', 2000, '/sushi/2_blue_dishes.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (32, 4, '초록색', 2500, '/sushi/3_red_dishes.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (33, 4, '빨간색', 2000, '/sushi/4_green_dishes.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (34, 4, '검은색', 5000, '/sushi/5_black_dishes.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (35, 4, '은색', 7000, '/sushi/6_silver_dishes.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (36, 4, '금색', 8000, '/sushi/7_glod_dishes.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (37, 4, '모듬 사시미', 25000, '/sushi/8_sashimi.jpg', '참치, 연어, 광어 등이 들어가 참치는 뱃살로 각 생선의 고유한 맛과 질감을 한 접시에서 체험할 수 있게 해드립니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (38, 4, '콜라', 2000, '/sushi/9_pepsi.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (39, 4, '사이다', 2000, '/sushi/10_sprite.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (40, 4, '생맥주', 4000, '/sushi/11_beer.jpg', null);

-- 베리 굿데이 (store_id = 5)
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (41, 5, '생딸기 빙수', 8000, '/berry/1_strawberry_bingsu.jpg', '생딸기빙수는 여름철 인기 디저트로 주 재료로 사용되는 생딸기는 신선하고 상큼한 맛이 특징이며 연유를 추가 해 더욱 달고 맛있게 맛보실 수 있습니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (42, 5, '메론빙수', 10000, '/berry/2_ice-melon-bingsu.jpg', '메론빙수는 여름철에 메론의 신선하고 달콤한 맛이 얼음과 더욱 어우러져 시럽을 추가해 더욱 맛을 잘 느끼실 수 있도록 합니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (43, 5, '망고 빙수', 12000, '/berry/3_mango_bingsu.jpg', '아주 달고 싱싱한 망고로 망고 시럽과 함께 추가 되어 더욱 망고 맛을 잘 느낄 수 있도록 합니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (44, 5, '청포도 빙수', 9000, '/berry/4_green_grape_bingsu.jpg', '청포도의 상큼함과 달콤함으로 얼음과 조화롭게 이루어져 청포도 시럽과 같이 과일도 조금 슬라이스 해 맛을 잘 느낄 수 있도록 합니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (45, 5, '수박 빙수', 10000, '/berry/5_watermelon-bingsu-dessert.jpg', '여름철에 시원하고 단 수박과 같이 수박 시럽을 더 추가해 얼음 사이 사이 잘 스며들 수 있게 했으며 수박을 슬라이스 해 좀 더 맛이 깊어질 수 있도록 합니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (46, 5, '블루베리 빙수', 10000, '/berry/6_blueberry_bingsu.jpg', '얼려놓은 블루베리를 이용해 블루베리 시럽도 함께 만들어 블루베리 빙수를 더욱 느낄 수 있게 해드립니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (47, 5, '아이스아메리카노', 1500, '/berry/7_Americano.jpg', NULL);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (48, 5, '카페라떼', 3000, '/berry/8_cafe-latte.jpg', NULL);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (49, 5, '아이스티', 2000, '/berry/9_ice-peach-tea.jpg', NULL);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (50, 5, '레몬에이드', 2000, '/berry/10_lemonade.png', NULL);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (51, 5, '디카페인', 3300, '/berry/11_coffee-Decaf.jpg', NULL);

-- 빵집에서 만나요 (store_id = 6)
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (52, 6, '식빵', 4000, '/bread/1_toast.jpg', '갓 구어낸 식빵으로 쫄깃하고 찰진 빵을 드셔보실 수 있습니다 니즈에 맞게 컷팅도 해드립니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (53, 6, '크루아상', 2000, '/bread/2_croissants.jpg', '프랑스 산 고급 버터를 사용해 풍미가 진해집니다 겉은 바삭하고 속은 촉촉하게 만들어 속결이 살아있습니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (54, 6, '바게트', 3000, '/bread/3_baguettes.jpg', '70CM의 길이로 고온에서 구워 얇고 딱딱한 껍질이 형성 되며 안은 쫄깃하고 탱글한 식감과 대조 됩니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (55, 6, '몽블랑 베이글', 3000, '/bread/4_bagel.jpg', '겉이 바삭하고 탱탱하게 씹히는 반면 속은 쫀득해서 더욱 맛있는 베이글 입니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (56, 6, '쫀득군', 2000, '/bread/5_chewy_bread.jpg', '안에는 찹쌀떡이 들어가 있으며 겉은 튀김 처럼 바삭하게 되어있는게 특징입니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (57, 6, '초코 퐁당', 3000, '/bread/6_chocolate_bread.jpg', '초콜릿 글레이즈로 덮어져 있으며 부드럽고 쫄깃한 속살과 대비되는 바삭한 겉질감을 느낄 수 있습니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (58, 6, '모짜렐라듬뿍빵', 4000, '/bread/7_pizza_bread.jpg', '피자빵 같이 생겼으며 위에 모짜렐라 치즈가 들어가 쫄깃한 맛을 느낄수가 있고 느끼한걸 잡아주기 위해 토마토와 바질을 추가하여 마르게리타 스타일로 만들었습니다');
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (59, 6, '아이스아메리카노', 2000, '/bread/8_americano.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (60, 6, '카페라떼', 3300, '/bread/9_cafe_latte.jpg', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (61, 6, '자몽에이드', 3000, '/bread/10_lemonade.png', null);
INSERT INTO menu (menu_id, store_id, menu_name, price, menu_img, seolmyung) VALUES (62, 6, '레몬에이드', 3000, '/bread/11_Grapefruit Ade.jpg', null);

commit;

SELECT * FROM users WHERE users_id = 'honggd' AND users_email = 'honggd@example.com';

select * from users;
select * from store;
select * from menu order by menu_id;
select * from reservation order by rv_no;

select * from menu where store_id = 84;
delete from store where store_id = 83;

select rv_no_seq.currval from dual;
SELECT RV_NO_SEQ.CURRVAL FROM DUAL;

SELECT LAST_NUMBER - INCREMENT_BY AS CURRENT_VALUE
FROM USER_SEQUENCES
WHERE SEQUENCE_NAME = 'RV_NO_SEQ';


SELECT menu_img, menu_name, price, m.seolmyung, m.menu_id FROM menu m, store s WHERE m.store_id = s.store_id AND s.store_id = 1 ORDER BY m.menu_id;

Insert into reservation (rv_no, store_id, menu_id, suryang, users_signum, rv_date, rv_time, inwonsu, yochung) values (91, null, 64, 2, 81,  TO_DATE('2025-04-25 19:00', 'YYYY-MM-DD HH24:MI'), '10:00', 55, '55');

INSERT INTO reservation (rv_no, menu_id, suryang, users_signum, rv_date, rv_time, inwonsu, yochung) values (149,1, 1, 103, TO_DATE('2025-05-07', 'YYYY-MM-DD'), '17:00', 3 , '창가자리로 부탁드립니다');
INSERT INTO reservation (rv_no, menu_id, suryang, users_signum, rv_date, rv_time, inwonsu, yochung) values (149,10, 3, 103, TO_DATE('2025-05-07', 'YYYY-MM-DD'), '17:00', 3 , '창가자리로 부탁드립니다');

TRUNCATE TABLE reservation;
TRUNCATE TABLE menu;
TRUNCATE TABLE store;


