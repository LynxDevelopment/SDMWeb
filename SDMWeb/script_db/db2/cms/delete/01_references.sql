ALTER TABLE TB_ANSWERS DROP CONSTRAINT FK_TB_POLL;

ALTER TABLE TB_NEWS DROP CONSTRAINT FK_NEWS_CONTENT;

ALTER TABLE TB_NEWS_LINK DROP CONSTRAINT FK_TB_NEWS;

ALTER TABLE TB_NEWS_PICTURE DROP CONSTRAINT FK_TB_NEWS_PIC;

ALTER TABLE TB_POLL DROP CONSTRAINT FK_POLL_CONTENT;

ALTER TABLE TB_POLL_USER DROP CONSTRAINT FK_POLL_POLL_USER;

ALTER TABLE TB_CONTENT DROP CONSTRAINT FK_CONTENT_STATUS;

ALTER TABLE TB_CONTENT_PERMISSION DROP CONSTRAINT FK_CNT_PERMISSION;

ALTER TABLE TB_DIARY DROP CONSTRAINT FK_DIARY_CONTENT;

ALTER TABLE TB_MESSAGE DROP CONSTRAINT FK_MESSAGE_CONTENT;

ALTER TABLE TB_CONTAINER_SCHEDULE DROP CONSTRAINT FK_SCHED_CONTAINER;
                             
ALTER TABLE TB_CONTAINER_SCHEDULE DROP CONSTRAINT FK_SCHED_HTML;                                                  
	
ALTER TABLE TB_POPUP_SCHEDULE DROP CONSTRAINT FK_POPUP_CNT_SCHED;
	
ALTER TABLE TB_HTML DROP CONSTRAINT FK_HTML_CONTENT;