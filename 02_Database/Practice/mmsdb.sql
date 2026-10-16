CREATE DATABASE IF NOT EXISTS mmsdb;

USE mmsdb;

DROP TABLE IF EXISTS membership_tbl;
DROP TABLE IF EXISTS member_tbl;

CREATE TABLE IF NOT EXISTS member_tbl (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) not NULL ,
    age int not NULL CHECK ( age>=0 AND age <130 ),
    phone VARCHAR(255) NOT NULL UNIQUE CHECK ( phone REGEXP '^[0-9]{3}-[0-9]{4}-[0-9]{4}$')

);


DESCRIBE member_tbl;

CREATE TABLE IF NOT EXISTS membership_tbl (
    membership_id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT not null,
    ott_type VARCHAR(255) NOT NULL,
    plan_name VARCHAR(255) NOT NULL,
    enroll_date DATETIME DEFAULT NOW() NOT NULL ,
    expiry_date DATETIME,
    active BOOLEAN DEFAULT TRUE NOT NULL ,
    CONSTRAINT FOREIGN KEY (member_id)
        REFERENCES member_tbl (member_id)
                        on UPDATE CASCADE
                        on DELETE CASCADE
);
DESCRIBE membership_tbl;



SHOW CREATE TABLE member_tbl;
-- INSERT INTO member_tbl VALUES (NULL, '홍길동', 30, 'abc-defg-hijk');


-- check
/*
Say a code must look like nnn-nnn (3 digits, a hyphen, 3 digits):

CHECK ( code REGEXP '^[0-9]{3}-[0-9]{3}$' )

Read it left to right:

┌──────────┬────────────────────────────────────────────────┐
│   Part   │                    Meaning                     │
├──────────┼────────────────────────────────────────────────┤
│ ^        │ the string must start here                     │
├──────────┼────────────────────────────────────────────────┤
│ [0-9]{3} │ exactly 3 digits                               │
├──────────┼────────────────────────────────────────────────┤
│ -        │ a literal hyphen (outside [ ] it just means -) │
├──────────┼────────────────────────────────────────────────┤
│ [0-9]{3} │ exactly 3 more digits                          │
├──────────┼────────────────────────────────────────────────┤
│ $        │ the string must end here                       │
└──────────┴────────────────────────────────────────────────┘

So '123-456' passes, while '12a-456' (a letter) and '123-4567' (too long) are rejected.


 */