-- TB_GROUPS.SQL_FILTER rinominato a RULE
alter table TB_GROUPS rename column SQL_FILTER to RULE;

-- aggiunta della colonna ADMIN_PAGE_URL alla tabella TB_GROUP_TYPES.
-- Al momento nel file di creazione è NOT NULL, ma non è possibile aggiungere una 
-- colonna se la tabella non è vuota, quindi deve essere aggiunta come NULL
alter table TB_GROUP_TYPES add (ADMIN_PAGE_URL VARCHAR2(100) NULL);

update TB_MODULES set ADMIN_URI='/xweb-res/modules/modulelist.xwb' where ADMIN_URI='/xweb-res/modules/filterModules.do';
update TB_FUNCTIONS set FUNCTION_URI='/xweb-res/modules/modulelist.xwb' where FUNCTION_URI='/xweb-res/modules/filterModules.do';

UPDATE TB_MODULES SET ADMIN_URI = '/xweb-res/groups/grouptypeslist.xwb' WHERE ADMIN_URI = '/xweb-res/groups/filterGroup.do';
UPDATE TB_FUNCTIONS SET FUNCTION_URI = '/xweb-res/groups/grouptypeslist.xwb' WHERE FUNCTION_URI = '/xweb-res/groups/filterGroup.do';

update TB_GROUP_TYPES set ADMIN_PAGE_URL = '/xweb-res/groups/dynamicgroups/grouplist.xwb' where ADMIN_PAGE_URL = 'dynamicadmingroup.xwb';
update TB_GROUP_TYPES set ADMIN_PAGE_URL = '/xweb-res/groups/newslettergroups/grouplist.xwb' where ADMIN_PAGE_URL = 'news_letteradmingroup.xwb';
update TB_GROUP_TYPES set ADMIN_PAGE_URL = '/xweb-res/groups/systemgroups/grouplist.xwb' where ADMIN_PAGE_URL = 'systemadmingroup.xwb';
update TB_GROUP_TYPES set ADMIN_PAGE_URL = '/xweb-res/groups/usergroups/grouplist.xwb' where ADMIN_PAGE_URL = 'useradmingroup.xwb';

UPDATE TB_MODULES SET ADMIN_URI = '/xweb-res/menus/menulist.xwb' WHERE ADMIN_URI = '/xweb-res/menus/filterMenu.do';

UPDATE TB_MODULES SET ADMIN_URI = '/xweb-res/users/userlist.xwb' WHERE ADMIN_URI = '/xweb-res/users/filterUser.do';
UPDATE TB_FUNCTIONS SET FUNCTION_URI = '/xweb-res/users/userlist.xwb' WHERE FUNCTION_URI = '/xweb-res/users/filterUser.do';

UPDATE TB_MODULES SET ADMIN_URI = '/xweb-res/news/newslist.xwb' WHERE ADMIN_URI = '/xweb-res/news/filterNews.do';
UPDATE TB_FUNCTIONS SET FUNCTION_URI = '/xweb-res/news/newslist.xwb' WHERE FUNCTION_URI = '/xweb-res/news/filterNews.do';

UPDATE TB_MODULES SET ADMIN_URI = '/xweb-res/diary/diarylist.xwb' WHERE ADMIN_URI = '/xweb-res/diary/filterDiary.do';

UPDATE TB_MODULES SET ADMIN_URI = '/xweb-res/newsletter/newsletterlist.xwb' WHERE ADMIN_URI = '/xweb-res/newsletter/filterNewsletter.do';