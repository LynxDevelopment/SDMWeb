/*************************************************************************************
 * ATTENZIONE: le ultime 5 righe sono delle DROP TABLE. Se non si vogliono cancellare
 *			   eliminare le FK verso la tabella TB_WF_CONTENT_STATUS	
 *
 * ATTENZIONE: trigger commentato, leggere i commenti.
 ****************************************************************************/


-- eliminazione della parola XWeb dai titoli dei Moduli
UPDATE TB_MODULES SET MODULE_TITLE='Agenda' WHERE MODULE_ID='DIARY';
UPDATE TB_MODULES SET MODULE_TITLE='Sondaggi' WHERE MODULE_ID='POOL';
commit;

UPDATE TB_MODULES SET ADMIN_URI='/xweb-res/popup/popuplist.xwb' WHERE MODULE_ID='POPUP';
UPDATE TB_MODULES SET MODULE_TYPE=2 WHERE MODULE_ID='POPUP';
UPDATE TB_MODULES SET MODULE_TYPE=2 WHERE MODULE_ID='BOXLET';

update TB_MODULES set icon1='/images/icons/common_toolbar/edit/edit_32_h_g.gif' where MODULE_ID='NEWS';
update TB_MODULES set icon1='/images/icons/calendars_and_clocks/calendar_2_month/calendar_2_month_32_h_g.gif' where MODULE_ID='DIARY';
update TB_MODULES set icon1='/images/icons/common_toolbar/options_1/options_1_32_h_g.gif' where MODULE_ID='POOL';
update TB_MODULES set icon1='/images/icons/docs_folders_and_files/web_document/web_document_32_h_g.gif' where MODULE_ID='POPUP';
update TB_MODULES set icon1='/images/icons/zoom_pan_and_select/add_selection/add_selection_32_h_g.gif' where MODULE_ID='BOXLET';
update TB_MODULES set icon1='/images/icons/common_toolbar/send_mail/send_mail_32_h_g.gif' where MODULE_ID='USERMESSAGE';

commit;

update tb_wf_content_status set module_id = 'DIARY' where wf_content_id in
(select content_id from TB_DIARY);

update tb_wf_content_status set module_id = 'NEWS' where wf_content_id in
(select content_id from TB_NEWS);

update tb_wf_content_status set module_id = 'USERMESSAGE' where wf_content_id in
(select content_id from TB_MESSAGE);

update tb_wf_content_status set module_id = 'POLL' where wf_content_id in
(select content_id from TB_POOL);

commit;

-- nuove tabelle per popup e boxlet
CREATE TABLE TB_CONTAINER_SCHEDULE (
	SCHEDULE_ID 			NUMBER(*, 0) NOT NULL ,
	SCHEDULE_NAME	 		VARCHAR2(250),
	CONTAINER_NAME			VARCHAR2(30),
	POSITION 				NUMBER(*, 0),
	URL 					VARCHAR2(250),
	PUBLISH_DATE        	DATE NULL,
    EXPIRATION_DATE      	DATE NULL,
	STATUS               	NUMBER(*, 0) DEFAULT 1 NULL,
	CONSTRAINT PK_TB_CONTAINER_SCHEDULE
              PRIMARY KEY (SCHEDULE_ID)
);

CREATE TABLE TB_POPUP_SCHEDULE (
	SCHEDULE_ID 			NUMBER(*, 0) NOT NULL ,
	HEIGHT 					NUMBER(*, 0),
	WIDTH 					NUMBER(*, 0),
	LEFT_POS 				NUMBER(*, 0),
	TOP_POS 				NUMBER(*, 0),
	TIMER 					NUMBER(*, 0),
	VIEW_TIMES 				NUMBER(*, 0),

	CONSTRAINT PK_TB_POPUP_SCHEDULE
              PRIMARY KEY (SCHEDULE_ID)
);

CREATE TABLE TB_CONTAINER (
	NAME		VARCHAR2(30),
	TYPE		VARCHAR2(20),
	CONSTRAINT PK_TB_CONTAINER PRIMARY KEY (NAME)
);

CREATE TABLE TB_HTML (
	SCHEDULE_ID 			NUMBER(*, 0) NOT NULL ,
	BODY					CLOB,
	CONSTRAINT PK_TB_HTML PRIMARY KEY (SCHEDULE_ID)
);

ALTER TABLE TB_CONTAINER_SCHEDULE ADD CONSTRAINT FK_SCHED_CONTAINER
	FOREIGN KEY (CONTAINER_NAME) REFERENCES TB_CONTAINER ON DELETE CASCADE;

ALTER TABLE TB_POPUP_SCHEDULE ADD CONSTRAINT FK_POPUP_CONT_SCHED
	FOREIGN KEY (SCHEDULE_ID) REFERENCES TB_CONTAINER_SCHEDULE ON DELETE CASCADE;

ALTER TABLE TB_HTML	ADD CONSTRAINT FK_HTML_SCHEDULE
	FOREIGN KEY (SCHEDULE_ID) REFERENCES TB_CONTAINER_SCHEDULE ON DELETE CASCADE;

-- passaggio dati popup dalle tabelle vecchie a quelle nuove
insert all into TB_CONTAINER (NAME, TYPE) values (NAME, 'POPUP') select NAME from TB_POPUP;

insert all into TB_CONTAINER_SCHEDULE (SCHEDULE_ID, CONTAINER_NAME, SCHEDULE_NAME, POSITION, URL, PUBLISH_DATE, EXPIRATION_DATE, STATUS)
	values (CONTENT_ID, POPUP_NAME, CONFIGURATION_NAME, POSITION, URL, PUBLISH_DATE, EXPIRATION_DATE, STATUS)
	select CONTENT_ID, POPUP_NAME, CONFIGURATION_NAME, POSITION, URL, PUBLISH_DATE, EXPIRATION_DATE, STATUS from TB_POPUP_CONFIGURATION left join TB_WF_CONTENT_STATUS on CONTENT_ID=WF_CONTENT_ID;

insert all into TB_POPUP_SCHEDULE (SCHEDULE_ID, HEIGHT, WIDTH, LEFT_POS, TOP_POS, TIMER, VIEW_TIMES)
	values (SCHEDULE_ID, HEIGHT, WIDTH, LEFT, TOP, TIMER, VIEW_TIMES)
	select SCHEDULE_ID, p.HEIGHT, p.WIDTH, LEFT, TOP, p.TIMER, p.VIEW_TIMES from TB_POPUP_CONFIGURATION p left join TB_CONTAINER_SCHEDULE on CONTENT_ID=SCHEDULE_ID;

CREATE OR REPLACE TRIGGER TR_COPY_CLOB_TO_TB_HTML
    BEFORE INSERT ON TB_HTML FOR EACH ROW
DECLARE
 l_clob clob;
BEGIN
	 select body into l_clob from tb_popup_configuration where content_id=:new.schedule_id;
	 dbms_lob.append (:new.body, l_clob);

END TR_COPY_CLOB_TO_TB_HTML;
/

alter trigger TR_COPY_CLOB_TO_TB_HTML compile;

insert all into TB_HTML (SCHEDULE_ID, BODY) values (CONTENT_ID, empty_clob()) select CONTENT_ID from TB_POPUP_CONFIGURATION where BODY is not NULL;

DROP TRIGGER TR_COPY_CLOB_TO_TB_HTML;
commit;

/***************************************************************************************
-- Trigger per ordinare gli SCHEDULE_ID partendo da 1. Decommentare se non si vogliono
-- avere buchi.
CREATE OR REPLACE TRIGGER TR_RESET_SCHEDULE_ID
    BEFORE UPDATE ON TB_CONTAINER_SCHEDULE FOR EACH ROW
DECLARE
 l_clob clob;
BEGIN
	 update TB_HTML set schedule_id=:new.schedule_id where schedule_id=:old.schedule_id;
	 update TB_POPUP_SCHEDULE set schedule_id=:new.schedule_id where schedule_id=:old.schedule_id;
END TR_RESET_SCHEDULE_ID;
/

alter trigger TR_RESET_SCHEDULE_ID compile;

alter table tb_html drop constraint PK_TB_HTML;
alter table tb_popup_schedule drop constraint PK_TB_POPUP_SCHEDULE;
alter table tb_html drop constraint FK_HTML_SCHEDULE;
alter table tb_popup_schedule drop constraint FK_POPUP_CONT_SCHED;
alter table tb_container_schedule drop constraint PK_TB_CONTAINER_SCHEDULE;
update tb_container_schedule set schedule_id=(select a from (select ROWNUM a, SCHEDULE_ID b from tb_container_schedule) where schedule_id=b);

alter table tb_container_schedule add constraint PK_TB_CONTAINER_SCHEDULE PRIMARY KEY (SCHEDULE_ID);
alter table tb_html add constraint PK_TB_HTML PRIMARY KEY (SCHEDULE_ID);
alter table tb_popup_schedule add constraint PK_TB_POPUP_SCHEDULE PRIMARY KEY (SCHEDULE_ID);
ALTER TABLE TB_POPUP_SCHEDULE ADD CONSTRAINT FK_POPUP_CONT_SCHED
	FOREIGN KEY (SCHEDULE_ID) REFERENCES TB_CONTAINER_SCHEDULE ON DELETE CASCADE;

ALTER TABLE TB_HTML	ADD CONSTRAINT FK_HTML_SCHEDULE
	FOREIGN KEY (SCHEDULE_ID) REFERENCES TB_CONTAINER_SCHEDULE ON DELETE CASCADE;
drop trigger TR_RESET_SCHEDULE_ID;

***************************************************************************************/

-- passaggio dati banner dalle tabelle vecchie a quelle nuove
insert all into TB_CONTAINER (NAME, TYPE) values (BOXLET_NAME, 'BOXLET') select distinct BOXLET_NAME from TB_BOXLET_CONFIGURATIONS;

insert all into TB_CONTAINER_SCHEDULE (SCHEDULE_ID, CONTAINER_NAME, SCHEDULE_NAME, POSITION, URL, PUBLISH_DATE, EXPIRATION_DATE, STATUS)
	values (COUNTER, BOXLET_NAME, CONFIGURATION_NAME, POSITION, URL, PUBLICATION_TIME, EXPIRATION_TIME, STATUS)
	select ((select MAX(SCHEDULE_ID) from tb_container_schedule)+ROWNUM) COUNTER,BOXLET_NAME, CONFIGURATION_NAME, POSITION, URL, PUBLICATION_TIME, EXPIRATION_TIME, STATUS from TB_BOXLET_CONFIGURATIONS;
commit;

-- tabelle non più utilizzate
DROP TABLE TB_BANNER CASCADE CONSTRAINTS;

DROP TABLE TB_POPUP CASCADE CONSTRAINTS;

DROP TABLE TB_POPUP_CONFIGURATION CASCADE CONSTRAINTS;

DROP TABLE TB_BOXLET CASCADE CONSTRAINTS;

DROP TABLE TB_BOXLET_CONFIGURATION CASCADE CONSTRAINTS;