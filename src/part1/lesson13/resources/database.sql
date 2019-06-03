USE `database`;

CREATE TABLE `USER`
(id INT NOT NULL PRIMARY KEY,
name VARCHAR(45) NOT NULL,


birthday DATE,
login_ID INT,
city VARCHAR(15),
email VARCHAR(15),

description VARCHAR(255));

CREATE TABLE `ROLE` (id INT NOT NULL PRIMARY KEY,
name ENUM ('Administration', 'Clients', 'Billing') NOT NULL,


description VARCHAR(255));

CREATE TABLE `USER_ROLE`
(id INT NOT NULL PRIMARY KEY,
user_id  INT NOT NULL,
role_id INT NOT NULL);

CREATE TABLE `LOGS`
(id INT NOT NULL PRIMARY KEY auto_increment,
_date  DATETIME NOT NULL,
log_level varchar(15) NOT NULL,
message varchar(255),
exception varchar(255));