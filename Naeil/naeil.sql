drop table reservation cascade constraint;
drop table menu cascade constraint;
drop table store cascade constraint;
drop table users cascade constraint;

--users

create table users (
    users_signum NUMBER(7) PRIMARY KEY,
    users_name VARCHAR2(20) not null,
    users_id VARCHAR2(12) UNIQUE,
    users_password VARCHAR2(100) not null,
    users_img VARCHAR2(200),
    users_phone VARCHAR2(20) not null,
    users_email VARCHAR2(50) not null,
    users_type NUMBER(1) not null --1사업자 / 2 유저
);


--store
create table store(
store_id number(5) primary key,
store_name varchar2(30) not null,
store_img varchar2(40),
category varchar2(100) not null,
location varchar2(50) unique not null,
seolmyung varchar2(700),
store_ph varchar2(13),
users_signum number(5),
constraint fk_store_users foreign key (users_signum) references users(users_signum) ON DELETE CASCADE);



--아직 관리자 테이블 만들기 전이라 나중에 수정만 해줄 예정

--menu
create table menu
(store_id number(5),
menu_id number(5) primary key,
menu_name varchar2(30) not null,
menu_img varchar2(50),
price number(10) not null,
seolmyung varchar2(400),
constraint fk_menu_store foreign key(store_id) references store(store_id) ON DELETE CASCADE );



--reservation //예약번호가 메뉴아이디를 복합키로 설정
CREATE TABLE reservation (
  rv_no       NUMBER(5),              -- 예약번호 (PK)
  menu_id     NUMBER(5),
  suryang     NUMBER(3),
  users_signum number(5),
  rv_date     DATE,                               -- 예약 날짜
  rv_time     VARCHAR2(10),                       -- 예약 시간 (HH:MI)
  inwonsu     NUMBER(3),
  yochung     VARCHAR2(100),

constraint pk_reservation primary key(rv_no,menu_id),

CONSTRAINT fk_reservation_menu FOREIGN KEY (menu_id) REFERENCES menu(menu_id) ON DELETE CASCADE,

CONSTRAINT fk_reservation_users FOREIGN KEY (users_signum) REFERENCES users(users_signum) ON DELETE CASCADE


);



commit;

desc store;

desc menu;

desc reservation;

desc users;




--sequence
--메뉴 번호 
DROP SEQUENCE menu_id_seq;
CREATE SEQUENCE menu_id_seq;

--예약 번호
DROP SEQUENCE rv_no_seq;
CREATE SEQUENCE rv_no_seq;

--사업자 번호
DROP SEQUENCE admin_signum_seq;
CREATE SEQUENCE admin_signum_seq
START WITH 1001;

--유저 번호
DROP SEQUENCE users_signum_seq;
CREATE SEQUENCE users_signum_seq
START WITH 2001;

--가게 번호
DROP SEQUENCE store_id_seq;
CREATE SEQUENCE store_id_seq;
