/******************************************************************************
 * SCRIPT DA LANCIARE PER ULTIMO, DOPO AVER LANCIATO ANCHE CMS!               *
 ******************************************************************************/

insert all into TB_USER_GROUP (GROUP_ID, USER_ID) values ('EVERYONE', USER_ID) 
	select USER_ID from TB_USER_PROFILES;
	
insert all into TB_USER_GROUP (GROUP_ID, USER_ID) values (GROUP_ID, 'xweb.admin') 
	select GROUP_ID from TB_GROUPS where GROUP_ID<>'EVERYONE' and GROUP_ID<>'DEBUG';
	
insert all into TB_USER_GROUP (GROUP_ID, USER_ID) values (GROUP_ID, 'xweb.dev') 
	select GROUP_ID from TB_GROUPS where GROUP_ID<>'EVERYONE';		
	
insert all into TB_FUNCTION_GROUPS (GROUP_ID, FUNCTION_ID, MODULE_ID) values ('XWEB_ADMIN', FUNCTION_ID, MODULE_ID) 
	select FUNCTION_ID, MODULE_ID from TB_FUNCTIONS where FUNCTION_ID<>'MONITOR' and MODULE_ID<>'TUNING';