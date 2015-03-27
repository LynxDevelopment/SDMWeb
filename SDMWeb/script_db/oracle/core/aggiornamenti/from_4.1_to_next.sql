/********************************************************************************************
 * ATTENZIONE, lo script ristruttura la tabella TB_MENU_ITEMS ed elimina la tabella TB_MENU *
 ********************************************************************************************/


/*********** NUOVA STRUTTURA MENU *************/
alter table TB_MENU_ITEMS drop constraint FK_TB_MENU;
alter table TB_MENU_ITEMS drop constraint FK_PARENT_CHECK; 
alter table TB_MENU_ITEM_GROUP drop constraint FK_GROUP_ITEM;
alter table TB_MENU_ITEM_GROUP drop constraints PK_TB_MENU_ITEM_GROUP;
alter table TB_MENU_ITEMS drop constraint PK_TB_MENU_ITEMS;

alter table TB_MENU_ITEMS add ID_TEMP varchar2(256);
alter table TB_MENU_ITEMS add PARENT_TEMP varchar2(256);
alter table TB_MENU_ITEM_GROUP add ID_TEMP varchar2(256);

-- inserimento radice
delete from TB_MENU_ITEMS where PARENT is null;	
insert into TB_MENU_ITEMS (ID_TEMP, LANGUAGE_ID, PARENT, POSITION, MENU_ITEM_TITLE, URL, ENABLED, MARKED, MENU_ITEM_ID, MENU_ID) 
	values ('/', 'it', NULL, 1, 'Root node', '#', 'T', 'F', -1, -1);
	
-- inserimento menu	 
insert all into TB_MENU_ITEMS (ID_TEMP, LANGUAGE_ID, PARENT_TEMP, POSITION, MENU_ITEM_TITLE, URL, ENABLED, MARKED, OBJ_TYPE, MENU_ITEM_ID, MENU_ID) 
	values (ID, 'it', '/', ROWNUM, MENU_TITLE, '#', 'T', 'F', OBJ_TYPE, -1, -1) select concat('/', MENU_ID) ID, ROWNUM, MENU_TITLE, OBJ_TYPE from TB_MENU;
	
update TB_MENU_ITEMS set ID_TEMP = concat('/', concat(MENU_ID, concat('/', MENU_ITEM_ID))) where PARENT = 0;	
update TB_MENU_ITEMS set PARENT_TEMP = concat('/', MENU_ID) where PARENT = 0;
	
create or replace procedure PR_UPDATE_PARENT
is
CURSOR c1
    IS select distinct(parent), MENU_ID from TB_MENU_ITEMS where PARENT>0 order by parent;
actualParent number;
actualMenu number;
id varchar2(256);
begin
  open c1;
  fetch c1 into actualParent, actualMenu;
  if c1%found then
	  loop
	  	  select ID_TEMP into id from TB_MENU_ITEMS where MENU_ITEM_ID=actualParent and MENU_ID=actualMenu; 	
	  	  update TB_MENU_ITEMS set PARENT_TEMP=id where PARENT=actualParent and MENU_ID=actualMenu;
	  	  update TB_MENU_ITEMS set ID_TEMP=concat(PARENT_TEMP, concat('/', MENU_ITEM_ID)) where PARENT=actualParent and MENU_ID=actualMenu;
		  fetch c1 into actualParent, actualMenu;
	  exit when c1%notfound;
	  end loop;
  end if;
  close c1;
end;
/
	
exec PR_UPDATE_PARENT;	

drop procedure PR_UPDATE_PARENT;

update TB_MENU_ITEM_GROUP mig set ID_TEMP=(select ID_TEMP from TB_MENU_ITEMS mi where mi.MENU_ITEM_ID=mig.MENU_ITEM_ID and mi.MENU_ID=mig.MENU_ID);	
update TB_MENU_ITEM_GROUP mig set ID_TEMP=concat('/', MENU_ID) where MENU_ITEM_ID=0;	
	
-- eliminazione riferimenti a TB_MENU	
drop table TB_MENU;	
alter table TB_MENU_ITEMS drop column PARENT;
alter table TB_MENU_ITEMS drop column MENU_ITEM_ID;
alter table TB_MENU_ITEMS drop column MENU_ID;
alter table TB_MENU_ITEM_GROUP drop column MENU_ID;
alter table TB_MENU_ITEM_GROUP drop column MENU_ITEM_ID;

alter table TB_MENU_ITEMS rename column ID_TEMP to MENU_ITEM_ID;
alter table TB_MENU_ITEMS rename column PARENT_TEMP to PARENT;
alter table TB_MENU_ITEM_GROUP rename column ID_TEMP to MENU_ITEM_ID;

alter table TB_MENU_ITEMS modify MENU_ITEM_ID NOT NULL;
alter table TB_MENU_ITEM_GROUP modify MENU_ITEM_ID NOT NULL;

insert into TB_MENU_ITEM_GROUP (MENU_ITEM_ID, GROUP_ID) values ('/', 'EVERYONE');

alter table TB_MENU_ITEMS add constraint PK_TB_MENU_ITEMS primary key (MENU_ITEM_ID);
alter table TB_MENU_ITEMS add constraint FK_PARENT_CHECK FOREIGN KEY (PARENT) REFERENCES TB_MENU_ITEMS (MENU_ITEM_ID); 
alter table TB_MENU_ITEM_GROUP add constraint FK_GROUP_ITEM FOREIGN KEY (MENU_ITEM_ID) REFERENCES TB_MENU_ITEMS (MENU_ITEM_ID) ON DELETE CASCADE;
alter table TB_MENU_ITEM_GROUP add constraint PK_TB_MENU_ITEM_GROUP PRIMARY KEY (MENU_ITEM_ID, GROUP_ID);
