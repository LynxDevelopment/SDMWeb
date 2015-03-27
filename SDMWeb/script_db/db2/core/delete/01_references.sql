ALTER TABLE TB_EVENTS DROP CONSTRAINT FK_TB_EVENTS;

ALTER TABLE TB_FUNCTION_GROUPS DROP CONSTRAINT FK_GROUPS_FUNCTION;

ALTER TABLE TB_FUNCTION_GROUPS DROP CONSTRAINT FK_TB_FUNCTIONS;

ALTER TABLE TB_FUNCTIONS DROP CONSTRAINT FK_TB_MODULES;

ALTER TABLE TB_GROUPS DROP CONSTRAINT FK_TB_GROUP_TYPES;

ALTER TABLE TB_MENU_ITEM_GROUP DROP CONSTRAINT FK_GROUP_ITEM;

ALTER TABLE TB_MENU_ITEM_GROUP DROP CONSTRAINT FK_ITEM_GROUP;

ALTER TABLE TB_MENU_ITEMS DROP CONSTRAINT FK_PARENT_CHECK;

ALTER TABLE TB_MODULES DROP CONSTRAINT FK_MODULES_TYPES;

ALTER TABLE TB_PAGES DROP CONSTRAINT FK_PAGES_TRACKING;

ALTER TABLE TB_USER_GROUP DROP CONSTRAINT FK_USER_PROFILES;

ALTER TABLE TB_USER_GROUP DROP CONSTRAINT FK_TB_GROUP_ID;

ALTER TABLE TB_USER_PROFILES DROP CONSTRAINT FK_USER_TYPE;

ALTER TABLE TB_USER_PROFILES DROP CONSTRAINT FK_TB_USER_STATUS;

ALTER TABLE TB_SPECIAL_DATES DROP CONSTRAINT FK_SPEC_DATES_CALS;

ALTER TABLE TB_SECURITY_CONSTRAINT DROP CONSTRAINT FK_RES_COLLECTION;

ALTER TABLE TB_SECURITY_CONSTRAINT DROP CONSTRAINT FK_SEC_GROUP;



