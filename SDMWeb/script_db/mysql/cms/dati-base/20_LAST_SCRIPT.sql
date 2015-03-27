/******************************************************************************
 * SCRIPT DA LANCIARE PER ULTIMO, DOPO AVER LANCIATO ANCHE CMS!               *
 ******************************************************************************/

insert into TB_USER_GROUP (GROUP_ID, USER_ID) select 'EVERYONE', USER_ID from TB_USER_PROFILES;

insert into TB_USER_GROUP (GROUP_ID, USER_ID) select GROUP_ID, 'xweb.admin' from TB_GROUPS where GROUP_ID<>'EVERYONE' and GROUP_ID<>'DEBUG';

insert into TB_USER_GROUP (GROUP_ID, USER_ID) select GROUP_ID, 'xweb.dev' from TB_GROUPS where GROUP_ID<>'EVERYONE';

insert into TB_FUNCTION_GROUPS (GROUP_ID, FUNCTION_ID, MODULE_ID) select 'XWEB_ADMIN', FUNCTION_ID, MODULE_ID from TB_FUNCTIONS
	 where FUNCTION_ID<>'MONITOR' and MODULE_ID<>'TUNING';