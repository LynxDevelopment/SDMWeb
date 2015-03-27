-- aggiunta colonne ICON1 e ICON2
alter table TB_MODULES add ICON1 VARCHAR2(250) NULL;
alter table TB_MODULES add ICON2 VARCHAR2(250) NULL;

-- aggiunta modulo FILEMANAGER
INSERT INTO TB_MODULES ( MODULE_TYPE, ADMIN_URI, MODULE_TITLE, MODULE_ID, CONTENT_LOCATION, DESCRIPTION ) VALUES (1, '/xweb-res/filemanager/file_browser.xwb', 'FileManager', 'FILEMANAGER', NULL, 'Permette di navigare nel File System');
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('BROWSE', 'FILEMANAGER', NULL, 'Naviga', NULL);
INSERT INTO TB_FUNCTION_GROUPS (GROUP_ID, FUNCTION_ID, MODULE_ID) values ('XWEB_ADMIN', 'BROWSE', 'FILEMANAGER');
commit;

-- aggiunta del modulo ICONS
INSERT INTO TB_MODULES ( MODULE_TYPE, ADMIN_URI, MODULE_TITLE, MODULE_ID, CONTENT_LOCATION, DESCRIPTION ) VALUES (1, '/xweb-res/icons/iconlist.xwb', 'Icons', 'ICONS', NULL, 'Elenco di icone utilizzabili');
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('SHOW', 'ICONS', NULL, 'Icons', NULL);
INSERT INTO TB_FUNCTION_GROUPS (GROUP_ID, FUNCTION_ID, MODULE_ID) VALUES ('XWEB_ADMIN','SHOW','ICONS');
commit;

-- aggiunta del modulo DEPLOYER
INSERT INTO TB_MODULES ( MODULE_TYPE, ADMIN_URI, MODULE_TITLE, MODULE_ID, CONTENT_LOCATION, DESCRIPTION ) VALUES (1, '/xweb-res/deployer/package_builder.xwb', 'Deployer', 'DEPLOYER', NULL, 'Invia aggiornamenti di XWeb');
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('DEPLOY', 'DEPLOYER', NULL, 'Deploy', NULL);
INSERT INTO TB_FUNCTION_GROUPS (GROUP_ID, FUNCTION_ID, MODULE_ID) VALUES ('XWEB_ADMIN','DEPLOY','DEPLOYER');
commit;

update TB_MODULES set icon1='/images/icons/docs_folders_and_files/folder_open_oblique/folder_open_oblique_32_h_g.gif' where MODULE_ID='FILEMANAGER';
update TB_MODULES set icon1='/images/icons/users/user_group_1/user_group_1_32_h_g.gif' where MODULE_ID='GROUPS';
update TB_MODULES set icon1='/images/icons/docs_folders_and_files/list/list_32_h_g.gif' where MODULE_ID='MENU';
update TB_MODULES set icon1='/images/icons/users/user_3/user_3_32_h_g.gif' where MODULE_ID='USER';
update TB_MODULES set icon1='/images/icons/windows_and_views/cascade_windows/cascade_windows_32_h_g.gif' where MODULE_ID='MODULES';
update TB_MODULES set icon1='/images/icons/charts_and_reports/charts_and_graphs/charts_and_graphs_32_h_g.gif' where MODULE_ID='STATISTICS';
update TB_MODULES set icon1='/images/icons/databases/database_check_in/database_check_in_32_h_g.gif' where MODULE_ID='CACHE';
update TB_MODULES set icon1='/images/icons/users/view_user_3/view_user_3_32_h_g.gif' where MODULE_ID='TRACKING';
update TB_MODULES set icon1='/images/icons/common_toolbar/options_3/options_3_32_h_g.gif' where MODULE_ID='TUNING';
update TB_MODULES set icon1='/images/icons/security/locked/locked_32_h_g.gif' where MODULE_ID='AUTH';
update TB_MODULES set icon1='/images/icons/miscellaneous/smiley/smiley_32_h_g.gif' where MODULE_ID='ICONS';
update TB_MODULES set icon1='/images/icons/search_and_find/filter_down/filter_down_32_h_g.gif' where MODULE_ID='DEPLOYER';
commit;

-- modifica della descrizione del MODULE_TYPE WORKFLOW
UPDATE TB_MODULE_TYPES SET DESCRIPTION='CMS' WHERE DESCRIPTION='WORKFLOW';
commit;

-- eliminazione della parola XWeb dai titoli dei Moduli
UPDATE TB_MODULES SET MODULE_TITLE='Gruppi' WHERE MODULE_ID='GROUPS';
UPDATE TB_MODULES SET MODULE_TITLE='Moduli' WHERE MODULE_ID='MODULES';
UPDATE TB_MODULES SET MODULE_TITLE='Statistiche' WHERE MODULE_ID='STATISTICS';
UPDATE TB_MODULES SET MODULE_TITLE='Utenti' WHERE MODULE_ID='USER';
commit;

-- creazione utente developer
INSERT INTO TB_USER_PROFILES ( USER_ID, PASSWORD, FIRSTNAME, LASTNAME, PHONE, EMAIL, ADDRESS, CITY,
PROV, ZIPCODE, STATE, MOBILE, FAX, BIRTHDATE, GENDER, EXPIRATION_DATE, 
ID_USER_STATUS,HOMEPAGE, USER_TYPE) VALUES ( 
'xweb.dev', 'admin', 'Amministratore', 'Xweb Developer', ' ', 'xweb.dev@localhost.localdomain', ' ', ' ', ' '
, ' ', ' ', ' ', ' ', NULL, 'M', NULL, 1, '/xweb-res/main.xwb', 'XWEB_USER'); 

insert all into TB_USER_GROUP (GROUP_ID, USER_ID) values (GROUP_ID, 'xweb.dev') 
	select GROUP_ID from TB_GROUPS;	
	
delete from TB_USER_GROUP where GROUP_ID='DEBUG' and USER_ID='xweb.admin';	
delete from TB_FUNCTION_GROUPS where GROUP_ID='XWEB_ADMIN' and FUNCTION_ID='MONITOR' and MODULE_ID='TUNING';
commit;

ALTER TABLE TB_USER_PROFILES MODIFY HOMEPAGE VARCHAR2(255);