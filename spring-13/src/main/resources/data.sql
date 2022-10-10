insert into genres (id, `name`) values (0,'fantastic');
insert into genres (id, `name`) values (1,'detective');

insert into authors (id, `name`) values (0,'Agatha Christie');
insert into authors (id, `name`) values (1,'Robert Anson Heinlein');
insert into authors (id, `name`) values (2,'Any');

insert into books ( `name`, author_id, genre_id) values ( 'The Mysterious Affair at Styles', 0, 1);
insert into books ( `name`, author_id, genre_id) values ( 'Super Secret Book', 1, 0);

insert into comments (book_id, `data`) values (1, 'comment 1');
insert into comments (book_id, `data`) values (1, 'comment 2');


INSERT INTO acl_sid (id, principal, sid) VALUES
                                             (1, 0, 'ROLE_ADMIN'),
                                             (2, 0, 'ROLE_USER');

INSERT INTO acl_class (id, class) VALUES
    (1, 'ru.otus.spring.spring13.domain.Book');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
    (1, 1, 1, NULL, 1, 0),
    (2, 1, 2, NULL, 1, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask,
                       granting, audit_success, audit_failure) VALUES
                                                                   (1, 1, 1, 1, 1, 1, 1, 1),
                                                                   (2, 1, 2, 1, 2, 1, 1, 1),
                                                                   (3, 2, 1, 1, 1, 1, 1, 1)
                                                                   ;

