insert into genres (id, `name`) values (0,'fantastic');
insert into genres (id, `name`) values (1,'detective');

insert into authors (id, `name`) values (0,'Agatha Christie');

insert into books ( `name`, author_id, genre_id) values ( 'The Mysterious Affair at Styles', 0, 1);

insert into comments (book_id, `data`) values (1, 'comment 1');
insert into comments (book_id, `data`) values (1, 'comment 2');