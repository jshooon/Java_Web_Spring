CREATE TABLE users (
	uid VARCHAR(30) NOT NULL,
	pwd VARCHAR(30) NOT NULL,
	name VARCHAR(30) NOT NULL,
	phone VARCHAR(30),
	PRIMARY KEY(uid));
    SELECT * FROM users;
    
    INSERT INTO users VALUES
	('koon', 'koonpwd', 'smith','010-0000-0000'),
    ('rim', 'rimpwd', 'elise','010-1111-1111'),
    ('gu', 'blakepwd', 'blake','010-2222-2222');
    