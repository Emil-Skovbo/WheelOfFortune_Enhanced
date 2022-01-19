drop database  worldchampion;
CREATE Database worldchampion;
use worldchampion;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS Teachers_classes,Student_Class, Student_Checkin,Course,Lecture,student,teacher,Classes,Checkin;
SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE student
(   id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE teacher
(
    id INT AUTO_INCREMENT PRIMARY KEY ,
    name varchar(255)
);

CREATE TABLE Lecture
(
    id   INT AUTO_INCREMENT PRIMARY KEY ,
    dato Date
);

CREATE TABLE Checkin
(
    id         INT AUTO_INCREMENT PRIMARY KEY ,
    createtime timestamp,
    code       int
);

CREATE TABLE Course
(
    id      INT AUTO_INCREMENT PRIMARY KEY ,
    FOREIGN KEY (id) REFERENCES Checkin (id),
    subject varchar(255)

);


CREATE TABLE Student_Class
(
    student_id INT NOT NULL,
    class_id   INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (class_id) REFERENCES Course (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (student_id, class_id)
);


CREATE TABLE Teachers_classes
(
    teacher_id INT NOT NULL,
    class_id   INT NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES teacher (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (class_id) REFERENCES Course (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (teacher_id, class_id)
);


CREATE TABLE Student_Checkin
(
    student_id INT NOT NULL,
    checkin_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (checkin_id) REFERENCES Checkin (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (student_id, checkin_id)
);