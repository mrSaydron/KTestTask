DROP DATABASE IF EXISTS test;

CREATE DATABASE test DEFAULT CHARACTER SET 'utf8';

USE test;

create table User
(
	id int(8) NOT NULL PRIMARY KEY auto_increment,
	name varchar(25),
	age int,
	isAdmin bit,
	createdDate timestamp
);

INSERT INTO test.User (name, age, isAdmin, createdDate) values('Oleg', 35,  0, now());
INSERT INTO test.User (name, age, isAdmin, createdDate) values('Andrey', 30,  0, now());
INSERT INTO test.User (name, age, isAdmin, createdDate) values('Vova', 36,  1, now());
INSERT INTO test.User (name, age, isAdmin, createdDate) values('Max', 31,  0, now());
INSERT INTO test.User (name, age, isAdmin, createdDate) values('Eugenia', 30,  0, now());
INSERT INTO test.User (name, age, isAdmin, createdDate) values('Aleksey', 31,  1, now());
INSERT INTO test.User (name, age, isAdmin, createdDate) values('Dmitriy', 27,  0, now());
INSERT INTO test.User (name, age, isAdmin, createdDate) values('Marat', 31,  0, now());
INSERT INTO test.User (name, age, isAdmin, createdDate) values('Olga', 28,  0, now());
INSERT INTO test.User (name, age, isAdmin, createdDate) values('Victor', 35,  1, now());