-- Crear tabla USERS
DROP TABLE IF EXISTS AUTOR;
CREATE TABLE AUTOR (
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(255) NOT NULL,
EMAIL VARCHAR(255) NOT NULL,
PHONE VARCHAR(255) NOT NULL,
PRIMARY KEY (ID)
);