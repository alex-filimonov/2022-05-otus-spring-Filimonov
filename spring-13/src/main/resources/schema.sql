DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(ID INT AUTO_INCREMENT PRIMARY KEY , NAME VARCHAR(255));

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(ID INT AUTO_INCREMENT PRIMARY KEY , NAME VARCHAR(255));

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(
                      ID INT AUTO_INCREMENT PRIMARY KEY,
                      NAME VARCHAR(255),
                      AUTHOR_ID BIGINT NOT NULL,
                      GENRE_ID BIGINT NOT NULL,
                      foreign key (AUTHOR_ID) references AUTHORS(ID),
                      foreign key (GENRE_ID) references GENRES(ID)
);

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS(
                         ID INT AUTO_INCREMENT PRIMARY KEY,
                         BOOK_ID BIGINT,
                         DATA VARCHAR(255),
                         foreign key (BOOK_ID) references BOOKS(ID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS APP_USER;
create table APP_USER
(
    ID                INT AUTO_INCREMENT PRIMARY KEY,
    LOGIN             VARCHAR(255) not null,
    PASSWORD          VARCHAR(255) not null,
    USER_ROLE         VARCHAR(255) not null
);
