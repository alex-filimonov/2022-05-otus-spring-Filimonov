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
                         BOOK_ID BIGINT NOT NULL,
                         DATA VARCHAR(255),
                         foreign key (BOOK_ID) references BOOKS(ID)
);
