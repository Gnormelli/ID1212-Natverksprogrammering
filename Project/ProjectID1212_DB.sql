CREATE TABLE conversation (
 id CHAR(10) NOT NULL,
 conversation_name CHAR(10)
);

ALTER TABLE conversation ADD CONSTRAINT PK_conversation PRIMARY KEY (id);


CREATE TABLE message (
 id INT NOT NULL,
 from_user CHAR(10),
 message_text CHAR(10),
 sent_datetime CHAR(10),
 id_group_chat CHAR(10) NOT NULL
);

ALTER TABLE message ADD CONSTRAINT PK_message PRIMARY KEY (id);


CREATE TABLE user_info (
 id CHAR(10) NOT NULL,
 user_name CHAR(10),
 profile_photo CHAR(10),
 email_address CHAR(10)
);

ALTER TABLE user_info ADD CONSTRAINT PK_user_info PRIMARY KEY (id);


CREATE TABLE group_member (
 id_conversation CHAR(10) NOT NULL,
 id_user_info CHAR(10) NOT NULL,
 joined_datetime CHAR(10),
 left_datetime CHAR(10)
);

ALTER TABLE group_member ADD CONSTRAINT PK_group_member PRIMARY KEY (id_conversation,id_user_info);


ALTER TABLE message ADD CONSTRAINT FK_message_0 FOREIGN KEY (id_group_chat) REFERENCES conversation (id);


ALTER TABLE group_member ADD CONSTRAINT FK_group_member_0 FOREIGN KEY (id_conversation) REFERENCES conversation (id);
ALTER TABLE group_member ADD CONSTRAINT FK_group_member_1 FOREIGN KEY (id_user_info) REFERENCES user_info (id);


