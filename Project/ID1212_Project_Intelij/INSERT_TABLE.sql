INSERT INTO profile_picture (id, picture, title) VALUES
    (1 , 'https://i.pinimg.com/474x/98/51/1e/98511ee98a1930b8938e42caf0904d2d.jpg', 'A normal guy');
INSERT INTO profile_picture (id, picture, title) VALUES
    (2 , 'https://media.istockphoto.com/id/1344327532/photo/studio-portrait-of-attractive-19-year-old-woman-with-brown-hair.jpg?b=1&s=170667a&w=0&k=20&c=qPtuG5hKcC8vva8y_HC9Xm5z31DeQG4LTI6RuSXJ6wE=', 'A normal girl');
INSERT INTO profile_picture (id, picture, title) VALUES
    (3 , 'https://people.com/thmb/SL7_3mF5irtEm4Kz8f63FWDrmPA=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():focal(999x0:1001x2)/dog-dating-1-a1a34ab3445740fcadf8699850c8333b.jpg', 'Cute dog');
INSERT INTO profile_picture (id, picture, title) VALUES
    (4,'https://thumbs.dreamstime.com/b/closeup-bengal-cat-profile-view-black-background-50946262.jpg','Cool cat');

INSERT INTO role (id, role_name) VALUES (0 , 'USER');
INSERT INTO role (id, role_name) VALUES (1 , 'ADMIN');

INSERT INTO conversation (id, conversation_name) VALUES (1, 'Chat with friends');
INSERT INTO conversation (id, conversation_name) VALUES (2, 'Chat with family');
INSERT INTO conversation (id, conversation_name) VALUES (3, 'Chat with random');
INSERT INTO conversation (id, conversation_name) VALUES (4, 'A big chat chat');

INSERT INTO user_info (id, user_email, unlocked, locked, password, username, fk_id_profile_picture, fk_id_role)
VALUES (1, 'AnEmail@gamil.com', true, false, 'qwerty', 'Han', 1, 1);
INSERT INTO user_info (id, user_email, unlocked, locked, password, username, fk_id_profile_picture, fk_id_role)
VALUES (2, 'AnEmail@gamil.com', true, false, 'qwerty', 'Luke', 2, 0);
INSERT INTO user_info (id, user_email, unlocked, locked, password, username, fk_id_profile_picture, fk_id_role)
VALUES (3, 'AnEmail@gamil.com', true, false, 'qwerty', 'Leia', 3, 0);
INSERT INTO user_info (id, user_email, unlocked, locked, password, username, fk_id_profile_picture, fk_id_role)
VALUES (4, 'AnEmail@gamil.com', true, false, 'rw', 'rw', 4, 0);

INSERT INTO group_member (id_conversation, id_user_info) VALUES (1,1);
INSERT INTO group_member (id_conversation, id_user_info) VALUES (1,2);
INSERT INTO group_member (id_conversation, id_user_info) VALUES (2,2);
INSERT INTO group_member (id_conversation, id_user_info) VALUES (2,3);

INSERT INTO queue_post (id, from_user, post_text, posted_date_time) VALUES
        (1, 'Han', 'The Millennium Falcon needs service, sigh!!', '2023-01-01T12:12:08.111606600');
INSERT INTO queue_post (id, from_user, post_text, posted_date_time) VALUES
    (2, 'Luke', 'Yoda is a bitch!!!', '2023-01-01T11:12:08.111606600');
INSERT INTO queue_post (id, from_user, post_text, posted_date_time) VALUES
    (3, 'Han', 'Chewbacca mother is so hairy....really hairy', '2023-01-01T10:12:08.111606600');
INSERT INTO queue_post (id, from_user, post_text, posted_date_time) VALUES
    (4, 'Leia', 'I forgot my blaster', '2023-01-01T09:12:08.111606600');
