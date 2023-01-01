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

INSERT INTO conversation (id, conversation_name) VALUES (0, 'Chat with friends');
INSERT INTO conversation (id, conversation_name) VALUES (1, 'Chat with family');
INSERT INTO conversation (id, conversation_name) VALUES (2, 'Chat with random');
INSERT INTO conversation (id, conversation_name) VALUES (3, 'A big chat chat');

INSERT INTO user_info (id, user_email, unlocked, locked, password, username, fk_id_profile_picture, fk_id_role)
VALUES (1, 'AnEmail@gamil.com', true, false, 'qwerty', 'Han', 1, 0);
INSERT INTO user_info (id, user_email, unlocked, locked, password, username, fk_id_profile_picture, fk_id_role)
VALUES (2, 'AnEmail@gamil.com', true, false, 'qwerty', 'Luke', 2, 0);
INSERT INTO user_info (id, user_email, unlocked, locked, password, username, fk_id_profile_picture, fk_id_role)
VALUES (3, 'AnEmail@gamil.com', true, false, 'qwerty', 'Leia', 3, 0);

INSERT INTO group_member (id_conversation, id_user_info) VALUES (0,1);
INSERT INTO group_member (id_conversation, id_user_info) VALUES (0,2);
INSERT INTO group_member (id_conversation, id_user_info) VALUES (1,2);
INSERT INTO group_member (id_conversation, id_user_info) VALUES (1,3);

