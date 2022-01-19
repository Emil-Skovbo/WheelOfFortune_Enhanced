CREATE Database wheelOfFortune;
use wheelOfFortune;

CREATE TABLE wheel_of_fortune
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE field
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    fieldValue int,
    wheel_id int,
    FOREIGN KEY (wheel_id) REFERENCES wheel_of_fortune (id)
);

INSERT INTO wheel_of_fortune (name) value ('Wheel of Fortune');
INSERT INTO field (name, fieldValue, wheel_id) VALUES ('Washing Machine', 3500, 1);
INSERT INTO field (name, fieldValue, wheel_id) VALUES ('Security Camera', 1500, 1);
INSERT INTO field (name, fieldValue, wheel_id) VALUES ('Electric Shaver', 2000, 1);
INSERT INTO field (name, fieldValue, wheel_id) VALUES ('PC Monitor', 2500, 1);
INSERT INTO field (name, fieldValue, wheel_id) VALUES ('Fridge', 3000, 1);
INSERT INTO field (name, fieldValue, wheel_id) VALUES ('Dud', 0, 1);