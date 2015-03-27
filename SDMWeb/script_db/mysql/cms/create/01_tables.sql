-- I vari indici devono essere creati per poter creare le relative chiavi esterne

SET sql_mode='ANSI_QUOTES';

CREATE TABLE TB_ANSWERS (
  	   ANSWERS_ID   	INTEGER        NOT NULL,
  	   POLL_ID      	INTEGER        NOT NULL,
  	   ANSWER_TEXT  	VARCHAR (200),
  	   HIT          	INTEGER,
  	   INITIAL_HIT  	INTEGER,  	   
  	   CONSTRAINT PK_ANSWER PRIMARY KEY ( ANSWERS_ID, POLL_ID )  	   
)  ENGINE=INNODB;


CREATE TABLE TB_CUSTOM_QUERY (
       QUERY_ID             VARCHAR(20) NOT NULL,
       QUERY_TXT            VARCHAR(200) NOT NULL,
       CONSTRAINT PK_TB_CUSTOM_QUERY PRIMARY KEY (QUERY_ID)
) ENGINE=INNODB;


CREATE TABLE TB_NEWS (
	   CONTENT_ID        	INTEGER NOT NULL ,
       TITLE                VARCHAR(255) NULL,
       SUBTITLE             VARCHAR(255) NULL,
       BODY                 BLOB NULL,
       CONSTRAINT PK_TB_NEWS PRIMARY KEY (CONTENT_ID)
)  ENGINE=INNODB;


CREATE TABLE TB_NEWS_LINK (
       LINK_ID              INTEGER NOT NULL,
       NEWS_ID              INTEGER NOT NULL,
       URL                  VARCHAR(255) NULL,
       NEWS_LINK_TITLE      VARCHAR(255) NULL,
       ALT                  VARCHAR(255) NULL,
       CONSTRAINT PK_TB_NEWS_LINK PRIMARY KEY (LINK_ID, NEWS_ID)
) ENGINE=INNODB;


CREATE TABLE TB_NEWS_PICTURE (
       PICTURE_ID           INTEGER NOT NULL,
       NEWS_ID              INTEGER NOT NULL,
       URL                  VARCHAR(255) NULL,
       ALT                  VARCHAR(255) NULL,
       PICTURE_NAME         VARCHAR(255) NULL,
       CONSTRAINT PK_TB_NEWS_PICTURE PRIMARY KEY (PICTURE_ID, NEWS_ID)
) ENGINE=INNODB;

CREATE TABLE TB_POLL (
       CONTENT_ID           INTEGER NOT NULL,
       DESCRIPTION          VARCHAR(100) NULL,
       QUESTION             VARCHAR(200) NULL,
       QUESTION_TYPE        INTEGER NULL,
       CONSTRAINT PK_TB_POLL PRIMARY KEY (CONTENT_ID)
) ENGINE=INNODB;


CREATE TABLE TB_POLL_USER (
       POLL_ID              INTEGER NOT NULL,
       USER_ID              VARCHAR(20) NOT NULL,
       CONSTRAINT PK_TB_POLL_USER PRIMARY KEY (USER_ID, POLL_ID)
) ENGINE=INNODB;

CREATE TABLE TB_CONTENT (
       CONTENT_ID        INTEGER NOT NULL,
       STATUS_ID         INTEGER NOT NULL,
       CREATION_DATE        DATE NOT NULL,
       RELEASE_DATE         DATE NULL,
       APPROVAL_DATE        DATE NULL,
       PUBLISH_DATE         DATE NULL,
       EXPIRATION_DATE      DATE NULL,
       DRAFT_DATE           DATE NULL,
       DRAFT_USER_ID        VARCHAR(20) NULL,
       RELEASE_USER_ID      VARCHAR(20) NULL,
       APPROVE_USER_ID      VARCHAR(20) NULL,
       MODULE_ID            VARCHAR(50) NULL,
       CONSTRAINT PK_TB_CONTENT PRIMARY KEY (CONTENT_ID)
) ENGINE=INNODB;

CREATE TABLE TB_CONTENT_PERMISSION (
       CONTENT_ID               INTEGER NOT NULL,
       PRINCIPAL            	VARCHAR(50) NOT NULL,
       PRINCIPAL_TYPE		    VARCHAR(20) NOT NULL,
       READ_PERMISSION			INTEGER NOT NULL,
       WRITE_PERMISSION			INTEGER NOT NULL,
       CONSTRAINT PK_TB_CONTENT_PERMISSION PRIMARY KEY (PRINCIPAL, CONTENT_ID, PRINCIPAL_TYPE),
       CONSTRAINT CONTENT_READ_PERMISSION_CHECK CHECK (READ_PERMISSION in (0, 1)),
       CONSTRAINT CONTENT_WRITE_PERMISSION_CHECK CHECK (WRITE_PERMISSION in (0, 1)),
       CONSTRAINT CONTENT_PRINCIPAL_TYPE_CHECK CHECK (PRINCIPAL_TYPE in ('USER', 'GROUP'))
) ENGINE=INNODB;


CREATE TABLE TB_CONTENT_STATUS (
       STATUS_ID         INTEGER NOT NULL,
       DESCRIPTION          VARCHAR(20) NOT NULL,
       CONSTRAINT PK_TB_CONTENT_STATUS PRIMARY KEY (STATUS_ID)       
) ENGINE=INNODB;

CREATE TABLE TB_DIARY (
       CONTENT_ID           INTEGER NOT NULL,
       SUBJECT              VARCHAR(100) NULL,
       MESSAGE_DATE         DATE NULL,
       MESSAGE              BLOB NULL,
       CONSTRAINT PK_TB_DIARY PRIMARY KEY (CONTENT_ID)
) ENGINE=INNODB;

CREATE TABLE TB_MESSAGE (
       CONTENT_ID        	INTEGER NOT NULL,
       SUBJECT              VARCHAR(100) NULL,
       MESSAGE_DATE         DATE NULL,
       MESSAGE              BLOB NULL,
       CONSTRAINT PK_TB_MESSAGE PRIMARY KEY (CONTENT_ID)
) ENGINE=INNODB;

CREATE TABLE TB_CONTAINER_SCHEDULE (
	SCHEDULE_ID 			INTEGER NOT NULL ,
	SCHEDULE_NAME	 		VARCHAR(250),
	CONTAINER_NAME			VARCHAR(30),
	CONTAINER_TYPE			VARCHAR(20),
	POSITION 				INTEGER,
	URL 					VARCHAR(250),
	CONTENT_ID				INTEGER,
	PUBLISH_DATE        	DATE NULL,
    EXPIRATION_DATE      	DATE NULL,
	STATUS               	INTEGER DEFAULT 1 NULL,
	CONSTRAINT PK_TB_CONTAINER_SCHEDULE PRIMARY KEY (SCHEDULE_ID),
	INDEX(CONTAINER_NAME),
	INDEX(CONTAINER_TYPE),
	INDEX(CONTENT_ID)
) ENGINE=INNODB;
                             
CREATE TABLE TB_POPUP_SCHEDULE (
	SCHEDULE_ID 			INTEGER NOT NULL ,
	HEIGHT 					INTEGER,
	WIDTH 					INTEGER,
	LEFT_POS 				INTEGER,
	TOP_POS 				INTEGER,
	TIMER 					INTEGER,
	VIEW_TIMES 				INTEGER,
	CONSTRAINT PK_TB_POPUP_SCHEDULE PRIMARY KEY (SCHEDULE_ID)
) ENGINE=INNODB;

CREATE TABLE TB_CONTAINER (
	NAME		VARCHAR(30) NOT NULL,
	TYPE		VARCHAR(20) NOT NULL,
	CONSTRAINT PK_TB_CONTAINER  PRIMARY KEY (NAME, TYPE)
) ENGINE=INNODB;

CREATE TABLE TB_HTML (
	CONTENT_ID          INTEGER NOT NULL,
	TITLE				VARCHAR(50),
	BODY				BLOB,
	CONSTRAINT PK_TB_HTML PRIMARY KEY (CONTENT_ID)
) ENGINE=INNODB;