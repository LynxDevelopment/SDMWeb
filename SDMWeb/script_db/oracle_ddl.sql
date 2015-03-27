
    create table TB_ANSWERS (
        POLL_ID number(10,0) not null,
        ANSWER_TEXT varchar2(200 char),
        HIT number(19,0),
        INITIAL_HIT number(19,0),
        ANSWERS_ID number(10,0) not null,
        primary key (POLL_ID, ANSWERS_ID)
    );

    create table TB_CONTAINER (
        NAME varchar2(100 char) not null,
        TYPE varchar2(15 char) not null,
        primary key (NAME, TYPE)
    );

    create table TB_CONTAINER_SCHEDULE (
        SCHEDULE_ID number(10,0) not null,
        SCHEDULE_NAME varchar2(100 char),
        CONTAINER_NAME varchar2(100 char),
        CONTAINER_TYPE varchar2(15 char),
        PUBLISH_DATE timestamp,
        EXPIRATION_DATE timestamp,
        STATUS number(1,0),
        CONTENT_ID number(10,0),
        URL varchar2(200 char),
        POSITION number(10,0),
        primary key (SCHEDULE_ID)
    );

    create table TB_CONTENT (
        CONTENT_ID number(10,0) not null,
        MODULE_ID varchar2(50 char) not null,
        STATUS_ID number(10,0),
        CREATION_DATE timestamp,
        RELEASE_DATE timestamp,
        DRAFT_DATE timestamp,
        APPROVAL_DATE timestamp,
        PUBLISH_DATE timestamp,
        EXPIRATION_DATE timestamp,
        DRAFT_USER_ID varchar2(20 char),
        RELEASE_USER_ID varchar2(20 char),
        APPROVE_USER_ID varchar2(20 char),
        primary key (CONTENT_ID)
    );

    create table TB_CONTENT_PERMISSION (
        CONTENT_ID number(10,0) not null,
        PRINCIPAL varchar2(50 char),
        PRINCIPAL_TYPE varchar2(20 char),
        READ_PERMISSION number(1,0),
        WRITE_PERMISSION number(1,0)
    );

    create table TB_CONTENT_STATUS (
        STATUS_ID number(10,0) not null,
        DESCRIPTION varchar2(20 char),
        primary key (STATUS_ID)
    );

    create table TB_DIARY (
        CONTENT_ID number(10,0) not null,
        SUBJECT varchar2(100 char),
        MESSAGE_DATE timestamp,
        MESSAGE clob,
        primary key (CONTENT_ID)
    );

    create table TB_FUNCTIONS (
        FUNCTION_ID varchar2(30 char) not null,
        MODULE_ID varchar2(30 char) not null,
        FUNCTION_URI varchar2(255 char),
        FUNCTION_TITLE varchar2(100 char),
        primary key (FUNCTION_ID, MODULE_ID)
    );

    create table TB_FUNCTION_GROUPS (
        GROUP_ID varchar2(30 char) not null,
        FUNCTION_ID varchar2(30 char) not null,
        MODULE_ID varchar2(30 char) not null,
        primary key (GROUP_ID, FUNCTION_ID, MODULE_ID)
    );

    create table TB_GROUPS (
        GROUP_ID varchar2(30 char) not null,
        GROUP_TYPE number(10,0) not null,
        DESCRIPTION varchar2(255 char),
        "RULE" varchar2(255 char),
        primary key (GROUP_ID)
    );

    create table TB_GROUP_TYPES (
        GROUP_TYPE number(10,0) not null,
        DESCRIPTION varchar2(255 char),
        ADMIN_PAGE_URL varchar2(255 char),
        primary key (GROUP_TYPE)
    );

    create table TB_HTML (
        CONTENT_ID number(10,0) not null,
        TITLE varchar2(100 char),
        BODY clob,
        primary key (CONTENT_ID)
    );

    create table TB_MENU_ITEMS (
        MENU_ITEM_ID varchar2(255 char) not null,
        ITEM_TYPE varchar2(15 char) not null,
        MENU_ITEM_TITLE varchar2(100 char),
        URL varchar2(255 char),
        LANGUAGE_ID varchar2(10 char),
        MARKED char(1 char),
        ENABLED char(1 char),
        ICON1 varchar2(255 char),
        ICON2 varchar2(255 char),
        PARENT varchar2(255 char),
        POSITION number(10,0),
        primary key (MENU_ITEM_ID)
    );

    create table TB_MENU_ITEM_GROUP (
        MENU_ITEM_ID varchar2(255 char) not null,
        GROUP_ID varchar2(30 char) not null,
        primary key (MENU_ITEM_ID, GROUP_ID)
    );

    create table TB_MESSAGE (
        CONTENT_ID number(10,0) not null,
        SUBJECT varchar2(100 char),
        MESSAGE_DATE timestamp,
        MESSAGE clob,
        primary key (CONTENT_ID)
    );

    create table TB_MODULES (
        MODULE_ID varchar2(30 char) not null,
        ADMIN_URI varchar2(200 char),
        MODULE_TITLE varchar2(100 char),
        CONTENT_LOCATION varchar2(100 char),
        DESCRIPTION varchar2(200 char),
        ICON1 varchar2(250 char),
        ICON2 varchar2(250 char),
        MODULE_TYPE number(10,0),
        primary key (MODULE_ID)
    );

    create table TB_MODULE_TYPES (
        MODULE_TYPE number(10,0) not null,
        DESCRIPTION varchar2(200 char),
        primary key (MODULE_TYPE)
    );

    create table TB_NEWS (
        CONTENT_ID number(10,0) not null,
        TITLE varchar2(255 char),
        SUBTITLE varchar2(255 char),
        BODY clob,
        primary key (CONTENT_ID)
    );

    create table TB_NEWS_LINK (
        NEWS_ID number(10,0) not null,
        URL varchar2(255 char),
        NEWS_LINK_TITLE varchar2(255 char),
        ALT varchar2(255 char),
        LINK_ID number(10,0) not null,
        primary key (NEWS_ID, LINK_ID)
    );

    create table TB_NEWS_PICTURE (
        NEWS_ID number(10,0) not null,
        URL varchar2(255 char),
        ALT varchar2(255 char),
        PICTURE_NAME varchar2(255 char),
        PICTURE_ID number(10,0) not null,
        primary key (NEWS_ID, PICTURE_ID)
    );

    create table TB_OLD_PASSWORDS (
        USER_ID varchar2(30 char) not null,
        PASSWORD varchar2(20 char),
        PASSWORD_TIMESTAMP timestamp
    );

    create table TB_PAGES (
        SEQUENCE_NUMBER number(19,0) not null,
        TRACKING_ID number(19,0) not null,
        ACCESS_TIME timestamp,
        PAGE_NAME varchar2(80 char),
        REFERRER varchar2(80 char),
        primary key (SEQUENCE_NUMBER, TRACKING_ID)
    );

    create table TB_POLL (
        CONTENT_ID number(10,0) not null,
        DESCRIPTION varchar2(100 char),
        QUESTION varchar2(200 char),
        QUESTION_TYPE number(10,0),
        primary key (CONTENT_ID)
    );

    create table TB_POLL_USER (
        POLL_ID number(10,0) not null,
        USER_ID varchar2(30 char)
    );

    create table TB_POPUP_SCHEDULE (
        SCHEDULE_ID number(10,0) not null,
        HEIGHT number(19,0),
        WIDTH number(19,0),
        LEFT_POS number(19,0),
        TOP_POS number(19,0),
        TIMER number(19,0),
        VIEW_TIMES number(19,0),
        primary key (SCHEDULE_ID)
    );

    create table TB_RESOURCE_COLLECTION (
        COLLECTION_NAME varchar2(20 char) not null,
        DESCRIPTION varchar2(200 char),
        URL_PATTERN varchar2(200 char),
        CONSTRAINTS_ENABLED number(1,0),
        primary key (COLLECTION_NAME)
    );

    create table TB_SECURITY_CONSTRAINT (
        COLLECTION_NAME varchar2(20 char) not null,
        GROUP_ID varchar2(30 char) not null,
        primary key (COLLECTION_NAME, GROUP_ID)
    );

    create table TB_TRACKING (
        TRACKING_ID number(19,0) not null,
        USER_ID varchar2(20 char),
        SESSION_ID varchar2(255 char),
        SESSION_START timestamp,
        SESSION_END timestamp,
        UNIQUE_IP varchar2(15 char),
        PLATFORM varchar2(50 char),
        SCREEN_SIZE varchar2(15 char),
        BROWSER varchar2(50 char),
        USER_AGENT varchar2(150 char),
        BROWSER_NAME varchar2(30 char),
        BROWSER_VERSION varchar2(5 char),
        primary key (TRACKING_ID)
    );

    create table TB_USER_GROUP (
        USER_ID varchar2(30 char) not null,
        GROUP_ID varchar2(30 char)
    );

    create table TB_USER_PROFILES (
        USER_ID varchar2(30 char) not null,
        USER_TYPE varchar2(20 char) not null,
        PASSWORD clob,
        PASSWORD_EXPIRATION_DATE date,
        FIRSTNAME varchar2(50 char),
        LASTNAME varchar2(50 char),
        LANGUAGE varchar2(2 char),
        COUNTRY varchar2(2 char),
        EMAIL varchar2(50 char),
        GENDER varchar2(1 char),
        BIRTHDATE date,
        HOMEPAGE varchar2(255 char),
        EXPIRATION_DATE date,
        ADDRESS varchar2(255 char),
        CITY varchar2(50 char),
        PROV varchar2(20 char),
        ZIPCODE varchar2(20 char),
        STATE varchar2(20 char),
        PHONE varchar2(20 char),
        MOBILE varchar2(20 char),
        FAX varchar2(20 char),
        ID_USER_STATUS number(10,0),
        primary key (USER_ID)
    );

    create table TB_USER_TYPE (
        TYPE varchar2(20 char) not null,
        CLASSNAME varchar2(100 char),
        primary key (TYPE)
    );

    alter table TB_ANSWERS 
        add constraint FK_POLL_ANSWER 
        foreign key (POLL_ID) 
        references TB_POLL;

    alter table TB_CONTAINER_SCHEDULE 
        add constraint FK_SCHEDULE_HTML 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_CONTAINER_SCHEDULE 
        add constraint FK_CONTAINER_SCHEDULE 
        foreign key (CONTAINER_NAME, CONTAINER_TYPE) 
        references TB_CONTAINER;

    alter table TB_CONTENT 
        add constraint FK_CONTENT_STATUS 
        foreign key (STATUS_ID) 
        references TB_CONTENT_STATUS;

    alter table TB_CONTENT_PERMISSION 
        add constraint FK_CONTENT_PERMISSION 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_DIARY 
        add constraint FK_DIARY_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_FUNCTIONS 
        add constraint FK_FUNCTION_MODULE 
        foreign key (MODULE_ID) 
        references TB_MODULES;

    alter table TB_FUNCTION_GROUPS 
        add constraint FK_GROUP_FUNCTION 
        foreign key (FUNCTION_ID, MODULE_ID) 
        references TB_FUNCTIONS;

    alter table TB_FUNCTION_GROUPS 
        add constraint FK_FUNCTION_GROUP 
        foreign key (GROUP_ID) 
        references TB_GROUPS;

    alter table TB_GROUPS 
        add constraint FK_GROUP_TYPE 
        foreign key (GROUP_TYPE) 
        references TB_GROUP_TYPES;

    alter table TB_HTML 
        add constraint FK_HTML_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_MENU_ITEMS 
        add constraint FK_MENUITEM_PARENT 
        foreign key (PARENT) 
        references TB_MENU_ITEMS;

    alter table TB_MENU_ITEM_GROUP 
        add constraint FK_GROUP_MENUITEM 
        foreign key (MENU_ITEM_ID) 
        references TB_MENU_ITEMS;

    alter table TB_MENU_ITEM_GROUP 
        add constraint FK_MENUITEM_GROUP 
        foreign key (GROUP_ID) 
        references TB_GROUPS;

    alter table TB_MESSAGE 
        add constraint FK_USER_MSG_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_MODULES 
        add constraint FK_MODULE_TYPE 
        foreign key (MODULE_TYPE) 
        references TB_MODULE_TYPES;

    alter table TB_NEWS 
        add constraint FK_NEWS_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_NEWS_LINK 
        add constraint FK_NEWS_LINK 
        foreign key (NEWS_ID) 
        references TB_NEWS;

    alter table TB_NEWS_PICTURE 
        add constraint FK_NEWS_PICTURE 
        foreign key (NEWS_ID) 
        references TB_NEWS;

    alter table TB_OLD_PASSWORDS 
        add constraint FK_USER_OLD_PSWD 
        foreign key (USER_ID) 
        references TB_USER_PROFILES;

    alter table TB_PAGES 
        add constraint FK_SESSION_PAGES 
        foreign key (TRACKING_ID) 
        references TB_TRACKING;

    alter table TB_POLL 
        add constraint FK_POLL_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_POLL_USER 
        add constraint FK_POLL_USER 
        foreign key (POLL_ID) 
        references TB_POLL;

    alter table TB_POPUP_SCHEDULE 
        add constraint FK_POPUP_SCHEDULE 
        foreign key (SCHEDULE_ID) 
        references TB_CONTAINER_SCHEDULE;

    alter table TB_SECURITY_CONSTRAINT 
        add constraint FK_GROUP_RES_COLL 
        foreign key (COLLECTION_NAME) 
        references TB_RESOURCE_COLLECTION;

    alter table TB_SECURITY_CONSTRAINT 
        add constraint FK_RES_COLL_GROUP 
        foreign key (GROUP_ID) 
        references TB_GROUPS;

    alter table TB_USER_GROUP 
        add constraint FK_USER_GROUP 
        foreign key (USER_ID) 
        references TB_USER_PROFILES;

    alter table TB_USER_PROFILES 
        add constraint FK_USER_TYPE 
        foreign key (USER_TYPE) 
        references TB_USER_TYPE;
