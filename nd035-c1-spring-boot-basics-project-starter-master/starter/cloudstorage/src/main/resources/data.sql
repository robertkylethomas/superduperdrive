CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

INSERT INTO
  USERS (
    `username`,
    `salt`,
    `password`,
    `firstname`,
    `lastname`
  )
VALUES
  (
    'user',
    'salt',
    'user',
    'user',
    'user'
  )
