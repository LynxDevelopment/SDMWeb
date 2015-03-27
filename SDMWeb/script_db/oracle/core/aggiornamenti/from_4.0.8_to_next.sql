-- aggiunta del gruppo DEBUG: se il grupppo è già stato inserito, cancellare questa riga
INSERT INTO TB_GROUPS ( GROUP_ID, GROUP_TYPE, DESCRIPTION, RULE ) VALUES ('DEBUG', 0, 'Gruppo per accedere alle funzioni di debug', NULL);

-- aggiunta del gruppo EVERYONE
insert into TB_GROUPS ( GROUP_ID, GROUP_TYPE, DESCRIPTION, RULE ) values ('EVERYONE', 0, 'Gruppo di default per tutti gli utenti', NULL);

insert all into TB_USER_GROUP (GROUP_ID, USER_ID) values ('EVERYONE', USER_ID) select USER_ID from TB_USER_PROFILES where USER_ID not in (select USER_ID from TB_USER_GROUP where GROUP_ID='EVERYONE'); 

insert all into TB_MENU_ITEM_GROUP (MENU_ITEM_ID, GROUP_ID, MENU_ID) values (MENU_ITEM_ID, 'EVERYONE', MENU_ID) select MENU_ITEM_ID, MENU_ID from TB_MENU_ITEMS where MENU_ITEM_ID>0 and not (MENU_ITEM_ID in (select distinct(MENU_ITEM_ID) from TB_MENU_ITEM_GROUP where GROUP_ID='EVERYONE') and MENU_ID in (select distinct(MENU_ID) from TB_MENU_ITEM_GROUP where GROUP_ID='EVERYONE'));

-- aggiunta function per monitoraggio
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('MONITOR', 'TUNING', '#', 'Developer Tools', NULL);
INSERT INTO TB_FUNCTION_GROUPS ( GROUP_ID, FUNCTION_ID, MODULE_ID ) VALUES ('DEBUG', 'MONITOR', 'TUNING');

/******* Introduzione del LOCALE *******/
-- colonna COUNTRY (intesa come indirizzo) rinominata in STATE
alter table TB_USER_PROFILES rename column COUNTRY to STATE;

-- colonne per l'oggetto LOCALE
alter table TB_USER_PROFILES add LANGUAGE CHAR(2) DEFAULT 'it' NULL;
alter table TB_USER_PROFILES add COUNTRY CHAR(2) DEFAULT 'IT' NULL;