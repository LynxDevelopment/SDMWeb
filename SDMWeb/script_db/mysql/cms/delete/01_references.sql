SET sql_mode='ANSI_QUOTES';

ALTER TABLE TB_ANSWERS DROP FOREIGN KEY "FK_TB_POLL";

ALTER TABLE TB_NEWS DROP FOREIGN KEY "FK_NEWS_CONTENT";

ALTER TABLE TB_NEWS_LINK DROP FOREIGN KEY "FK_TB_NEWS";

ALTER TABLE TB_NEWS_PICTURE DROP FOREIGN KEY "FK_TB_NEWS_PIC";

ALTER TABLE TB_POLL DROP FOREIGN KEY "FK_POLL_CONTENT";

ALTER TABLE TB_POLL_USER DROP FOREIGN KEY "FK_TB_POLL_TB_POLL_USER";

ALTER TABLE TB_CONTENT DROP FOREIGN KEY "FK_CONTENT_STATUS";

ALTER TABLE TB_CONTENT_PERMISSION DROP FOREIGN KEY "FK_CONTENT_PERMISSION";

ALTER TABLE TB_DIARY DROP FOREIGN KEY "FK_DIARY_CONTENT";

ALTER TABLE TB_MESSAGE DROP FOREIGN KEY "FK_MESSAGE_CONTENT";

ALTER TABLE TB_CONTAINER_SCHEDULE DROP FOREIGN KEY "FK_SCHED_CONTAINER";
                             
ALTER TABLE TB_CONTAINER_SCHEDULE DROP FOREIGN KEY "FK_SCHED_HTML";
	
ALTER TABLE TB_POPUP_SCHEDULE DROP FOREIGN KEY "FK_POPUP_CONT_SCHED"; 
	
ALTER TABLE TB_HTML DROP FOREIGN KEY "FK_HTML_CONTENT";