-- diary function- groups associations
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_EDITOR', 'CREATE', 'DIARY');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_EDITOR', 'DELETE', 'DIARY');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_EDITOR', 'UPDATE', 'DIARY');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_EDITOR', 'FILTER', 'DIARY');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_RELEASE', 'REFUSE', 'DIARY');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_RELEASE', 'RELEASE', 'DIARY');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_RELEASE', 'FILTER', 'DIARY');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_APPROVE', 'OFFLINE', 'DIARY');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_APPROVE', 'APPROVE', 'DIARY');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_APPROVE', 'REFUSE', 'DIARY');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_APPROVE', 'PUBLISH', 'DIARY');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'DIARY_APPROVE', 'FILTER', 'DIARY');


-- News function- groups associations
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_EDITOR', 'CREATE', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_EDITOR', 'DELETE', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_EDITOR', 'UPDATE', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_EDITOR', 'PUBLISH', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_EDITOR', 'ADD_PICTURE', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_EDITOR', 'REMOVE_PICTURE', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_EDITOR', 'FILTER', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_RELEASE', 'REFUSE', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_RELEASE', 'RELEASE', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_RELEASE', 'FILTER', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_APPROVE', 'APPROVE', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_APPROVE', 'REFUSE', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_APPROVE', 'OFFLINE', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_APPROVE', 'PUBLISH', 'NEWS');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'NEWS_APPROVE', 'FILTER', 'NEWS');

-- Polls function- groups associations
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'POLL_EDITOR', 'CREATE', 'POLL');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'POLL_EDITOR', 'DELETE', 'POLL');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'POLL_EDITOR', 'UPDATE', 'POLL');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'POLL_RELEASE', 'RELEASE', 'POLL');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'POLL_APPROVE', 'APPROVE', 'POLL');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'POLL_APPROVE', 'REFUSE', 'POLL');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'POLL_RELEASE', 'REFUSE', 'POLL');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'POLL_APPROVE', 'OFFLINE', 'POLL');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES (
'POLL_APPROVE', 'PUBLISH', 'POLL');


-- userMessage functions - groups associations
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_EDITOR', 'CREATE', 'USERMESSAGE');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_EDITOR', 'DELETE', 'USERMESSAGE');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_EDITOR', 'UPDATE', 'USERMESSAGE');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_EDITOR', 'FILTER', 'USERMESSAGE');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_RELEASE', 'REFUSE', 'USERMESSAGE');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_RELEASE', 'RELEASE', 'USERMESSAGE');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_RELEASE', 'FILTER', 'USERMESSAGE');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_APPROVE', 'OFFLINE', 'USERMESSAGE');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_APPROVE', 'APPROVE', 'USERMESSAGE');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_APPROVE', 'REFUSE', 'USERMESSAGE');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_APPROVE', 'PUBLISH', 'USERMESSAGE');
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('MESSAGE_APPROVE', 'FILTER', 'USERMESSAGE');