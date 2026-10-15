show databases;
use mysql;

# create account
use mysql;
-- only can create accounts on mysql db
-- user account, authorizations, timezone, log, etc. including databases

-- create user '사용자이름'@'접속가능한 위치' identified by 'password'
-- 접속가능한 위치: ip 지정, 와일드카드(%): 어느위치에서든 접속을 허용
create user 'yuny'@'%' identified by 'yuny';

-- Database(Schema) 생성
create database menudb;

show databases ;

-- 권한 부여 Grant privileges
-- grant all privileges on (db to accesses).(authorities to give) to (account to give auth)@scope
grant all privileges on menudb.* to 'yuny'@'%';


show grants for 'yuny'@'%'; -- 해당 계정이 가지고 있는 권한 목록 출력


-- revoke all privileges on menudb.* from 'yuny'@'%';
-- grant select on menudb.* to 'yuny'@'%';
-- show grants for 'yuny'@'%';


