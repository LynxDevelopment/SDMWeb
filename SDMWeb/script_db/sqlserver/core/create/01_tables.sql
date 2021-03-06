CREATE TABLE TB_EVENT_TYPE (
       TYPE_ID              INTEGER NOT NULL,
       DESCRIPTION          VARCHAR(250) NULL,
       CONSTRAINT PK_TAB_ENV_EVENT_TYPE
              PRIMARY KEY NONCLUSTERED (TYPE_ID)
);


CREATE TABLE TB_EVENTS (
       EVENT_TIME           DATETIME NOT NULL,
       USER_ID              VARCHAR(20) NOT NULL,
       TYPE_ID              INTEGER NULL,
       DESCRIPTION          VARCHAR(250) NULL,
       CONSTRAINT PK_TB_ENV_EVENTS
              PRIMARY KEY NONCLUSTERED (EVENT_TIME, USER_ID)
);

CREATE TABLE TB_FUNCTION_GROUPS (
       GROUP_ID             VARCHAR(50) NOT NULL,
       FUNCTION_ID          VARCHAR(50) NOT NULL,
       MODULE_ID            VARCHAR(50) NOT NULL,
       CONSTRAINT PK_TB_ENV_FUNCTION_PER_GROUP
              PRIMARY KEY NONCLUSTERED (GROUP_ID, FUNCTION_ID, MODULE_ID)
);


CREATE TABLE TB_FUNCTIONS (
       FUNCTION_ID          VARCHAR(50) NOT NULL,
       MODULE_ID            VARCHAR(50) NOT NULL,
       FUNCTION_URI         VARCHAR(250) NULL,
       FUNCTION_TITLE       VARCHAR(250) NULL,
       WF_MGR_AUTO          INTEGER NULL,
       CONSTRAINT PK_TB_ENV_FUNCTIONS
              PRIMARY KEY NONCLUSTERED (FUNCTION_ID, MODULE_ID)
);

CREATE TABLE TB_GROUP_TYPES (
       GROUP_TYPE           INTEGER NOT NULL,
       DESCRIPTION          VARCHAR(200) NULL,
       ADMIN_PAGE_URL		VARCHAR(100) NOT NULL,
       CLASSNAME			VARCHAR(100) NULL,
       CONSTRAINT PK_TB_GROUP_TYPES
              PRIMARY KEY NONCLUSTERED (GROUP_TYPE)
);


CREATE TABLE TB_GROUPS (
       GROUP_ID             VARCHAR(50) NOT NULL,
       GROUP_TYPE           INTEGER NULL,
       DESCRIPTION          VARCHAR(200) NULL,
       [RULE]	            VARCHAR(500) NULL,
       CONSTRAINT PK_TB_GROUPS
              PRIMARY KEY NONCLUSTERED (GROUP_ID)
);


CREATE TABLE TB_MENU_ITEM_GROUP (
       MENU_ITEM_ID         VARCHAR(256) NOT NULL,
       GROUP_ID             VARCHAR(50) NOT NULL,
       CONSTRAINT PK_TB_MENU_ITEM_GROUP
              PRIMARY KEY NONCLUSTERED (MENU_ITEM_ID, GROUP_ID)
);


CREATE TABLE TB_MENU_ITEMS (
       MENU_ITEM_ID         VARCHAR(256) NOT NULL,
       LANGUAGE_ID          VARCHAR(2) NOT NULL,
       PARENT               VARCHAR(256) NULL,
       POSITION             INTEGER DEFAULT 1 NULL,
       MENU_ITEM_TITLE      VARCHAR(250) NULL,
       URL                  VARCHAR(250) NULL,
       ENABLED              VARCHAR(1) DEFAULT 'T' NOT NULL
                                   CONSTRAINT CHK_MARKED
                                          CHECK (ENABLED IN ('T','F')),
       MARKED               VARCHAR(1) DEFAULT 'F' NOT NULL
                                   CONSTRAINT CHK_ENABLED
                                          CHECK (MARKED IN ('T','F')),
       ICON1                VARCHAR(250) NULL,
       ICON2                VARCHAR(250) NULL,
       ITEM_TYPE			VARCHAR(20) NULL,
       OBJ_TYPE             INTEGER DEFAULT 0 NOT NULL,
       CONSTRAINT PK_TB_MENU_ITEMS
              PRIMARY KEY NONCLUSTERED (MENU_ITEM_ID)
);


CREATE TABLE TB_MODULE_TYPES (
       MODULE_TYPE          INTEGER NOT NULL,
       DESCRIPTION          VARCHAR(200) NULL,
       CONSTRAINT PK_TB_MODULE_TYPE
              PRIMARY KEY NONCLUSTERED (MODULE_TYPE)
);


CREATE TABLE TB_MODULES (
       MODULE_TYPE          INTEGER NULL,
       ADMIN_URI            VARCHAR(200) NULL,
       MODULE_TITLE         VARCHAR(250) NULL,
       MODULE_ID            VARCHAR(50) NOT NULL,
       CONTENT_LOCATION     VARCHAR(100) NULL,
       DESCRIPTION          VARCHAR(200) NULL,
       ICON1				VARCHAR(250) NULL,
       ICON2				VARCHAR(250) NULL,
       CONSTRAINT PK_TB_MODULES
              PRIMARY KEY NONCLUSTERED (MODULE_ID)
);

CREATE TABLE TB_PAGES (
       ACCESS_TIME          DATETIME NOT NULL,
       PAGE_NAME            VARCHAR(80) NOT NULL,
       SEQUENCE_NUMBER      INTEGER NOT NULL,
       TRACKING_ID          INTEGER NOT NULL,
       REFERRER             VARCHAR(80) NULL,
       QUERY_STRING         VARCHAR(80) NULL,
       CONSTRAINT PK_TB_PAGES
              PRIMARY KEY NONCLUSTERED (SEQUENCE_NUMBER, TRACKING_ID)
);

CREATE TABLE TB_USER_GROUP (
       GROUP_ID             VARCHAR(50) NOT NULL,
       USER_ID              VARCHAR(20) NOT NULL,
       CONSTRAINT PK_TB_ENV_USER_PER_GROUP
              PRIMARY KEY NONCLUSTERED (GROUP_ID, USER_ID)
);

CREATE TABLE TB_USER_PROFILES (
       USER_ID              VARCHAR(20) NOT NULL,
       PASSWORD             VARCHAR(50) NULL,
       FIRSTNAME            VARCHAR(50) NULL,
       LASTNAME             VARCHAR(50) NULL,
       EMAIL                VARCHAR(40) NULL,
       LANGUAGE				CHAR(2) DEFAULT 'it' NULL,
	   COUNTRY 				CHAR(2) DEFAULT 'IT' NULL,
       PHONE                VARCHAR(20) NULL,
       ADDRESS              VARCHAR(50) NULL,
       CITY                 VARCHAR(50) NULL,
       PROV                 VARCHAR(4) NULL,
       ZIPCODE              VARCHAR(10) NULL,
       STATE          		VARCHAR(20) NULL,
       MOBILE               VARCHAR(20) NULL,
       FAX                  VARCHAR(20) NULL,
       BIRTHDATE            DATETIME NULL,
       GENDER               CHAR(1) NULL,
       EXPIRATION_DATE      DATETIME NULL,
       USER_TABLE_NAME      VARCHAR(50) NULL,
       ID_USER_STATUS       INTEGER NULL,
       HOMEPAGE             VARCHAR(255) NULL,
       USER_TYPE            VARCHAR(20) DEFAULT 'XWEB_USER' NULL,
	   PASSWORD_EXPIRATION_DATE DATETIME NULL,
       CONSTRAINT PK_TB_ENV_USER_PROFILES
              PRIMARY KEY NONCLUSTERED (USER_ID)
);


CREATE TABLE TB_USER_STATUS (
       ID_USER_STATUS       INTEGER NOT NULL,
       DESCRIPTION          VARCHAR(200) NOT NULL,
       CONSTRAINT PK_TB_USER_STATUS
              PRIMARY KEY NONCLUSTERED (ID_USER_STATUS)
);


CREATE TABLE TB_USER_TYPE (
       TYPE                 VARCHAR(20) NOT NULL,
       CLASSNAME            VARCHAR(100) NULL,
       CONSTRAINT PK_TB_USER_TYPE
              PRIMARY KEY NONCLUSTERED (TYPE)
);

CREATE TABLE TB_TRACKING (
       USER_ID              VARCHAR(20) NULL,
       SESSION_ID           VARCHAR(100) NOT NULL,
       SESSION_START        DATETIME NOT NULL,
       SESSION_END          DATETIME NULL,
       TRACKING_ID          INTEGER NOT NULL,
       UNIQUE_IP            VARCHAR(15) NULL,
       PLATFORM             VARCHAR(50) NULL,
       SCREEN_SIZE          VARCHAR(15) NULL,
       BROWSER              VARCHAR(50) NULL,
       USER_AGENT           VARCHAR(150) NULL,
       BROWSER_NAME         VARCHAR(30) NULL,
       BROWSER_VERSION      VARCHAR(5) NULL,
       CONSTRAINT PK_TB_TRACKING PRIMARY KEY NONCLUSTERED (TRACKING_ID)
);

/*** Tabelle per statistiche ***/
CREATE TABLE TB_CALENDAR (
	CALENDAR_DATE 			DATETIME NOT NULL ,
	DAY 					INTEGER NOT NULL ,
	MONTH 					INTEGER NOT NULL ,
	YEAR 					INTEGER NOT NULL ,
	NOTE 					VARCHAR(100),
	CONSTRAINT PK_TB_CALENDAR PRIMARY KEY NONCLUSTERED (CALENDAR_DATE)
);

CREATE TABLE TB_CALENDARS (
	CALENDAR_ID 			VARCHAR(30) NOT NULL ,
	DESCRIPTION 			VARCHAR(255),
	CONSTRAINT PK_TB_CALENDARS PRIMARY KEY NONCLUSTERED (CALENDAR_ID)
);

CREATE TABLE TB_SPECIAL_DATES (
	CALENDAR_DATE 			DATETIME NOT NULL ,
	CALENDAR_ID 			VARCHAR(30) NOT NULL ,
	NOTE 					VARCHAR(255),
	CONSTRAINT PK_TB_SPECIAL_DATES PRIMARY KEY NONCLUSTERED (CALENDAR_DATE, CALENDAR_ID)
);

CREATE TABLE TB_HOURS_OF_DAY (
	HOUR 					VARCHAR(2),
	MINUTE 					VARCHAR(2)
);

CREATE TABLE TB_USER_PROPERTY (
	USER_ID					VARCHAR(20) NOT NULL,
	PROPERTY_NAME			VARCHAR(20) NOT NULL,
	VALUE					VARCHAR(255) NULL,
	CONSTRAINT PK_TB_USER_PROPERTY PRIMARY KEY NONCLUSTERED (USER_ID, PROPERTY_NAME)
);

CREATE TABLE TB_RESOURCE_COLLECTION (
		COLLECTION_NAME			VARCHAR(20) NOT NULL,
		DESCRIPTION				VARCHAR(200) NULL,
		URL_PATTERN				VARCHAR(200) NOT NULL,
		CONSTRAINTS_ENABLED		INTEGER	DEFAULT 0 NOT NULL,
		CONSTRAINT PK_TB_RESOURCE_COLLECTIONS PRIMARY KEY NONCLUSTERED (COLLECTION_NAME)
);

CREATE TABLE TB_SECURITY_CONSTRAINT (
		COLLECTION_NAME			VARCHAR(20) NOT NULL,
		GROUP_ID				VARCHAR(50) NOT NULL,
		CONSTRAINT PK_TB_SECURITY_CONSTRAINTS PRIMARY KEY NONCLUSTERED (COLLECTION_NAME, GROUP_ID)
);

CREATE TABLE TB_OLD_PASSWORDS (
		USER_ID					VARCHAR(20) NOT NULL,
		PASSWORD				VARCHAR(50) NOT NULL,
		PASSWORD_TIMESTAMP		DATETIME NOT NULL,
		CONSTRAINT PK_TB_OLD_PASSWORDS PRIMARY KEY (USER_ID, PASSWORD, PASSWORD_TIMESTAMP)	
);