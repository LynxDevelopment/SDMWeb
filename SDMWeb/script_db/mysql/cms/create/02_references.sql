SET sql_mode='ANSI_QUOTES';

ALTER TABLE TB_ANSWERS ADD CONSTRAINT "FK_TB_POLL" FOREIGN KEY (POLL_ID) REFERENCES TB_POLL (CONTENT_ID) ON DELETE CASCADE;

ALTER TABLE TB_NEWS ADD CONSTRAINT "FK_NEWS_CONTENT" FOREIGN KEY (CONTENT_ID) REFERENCES TB_CONTENT (CONTENT_ID) ON DELETE CASCADE;

ALTER TABLE TB_NEWS_LINK ADD CONSTRAINT "FK_TB_NEWS" FOREIGN KEY (NEWS_ID) REFERENCES TB_NEWS (CONTENT_ID) ON DELETE CASCADE;

ALTER TABLE TB_NEWS_PICTURE ADD CONSTRAINT "FK_TB_NEWS_PIC" FOREIGN KEY (NEWS_ID) REFERENCES TB_NEWS (CONTENT_ID) ON DELETE CASCADE;

ALTER TABLE TB_POLL ADD CONSTRAINT "FK_POLL_CONTENT" FOREIGN KEY (CONTENT_ID) REFERENCES TB_CONTENT (CONTENT_ID) ON DELETE CASCADE;

ALTER TABLE TB_POLL_USER ADD CONSTRAINT "FK_TB_POLL_TB_POLL_USER" FOREIGN KEY (POLL_ID) REFERENCES TB_POLL (CONTENT_ID) ON DELETE CASCADE;

ALTER TABLE TB_CONTENT ADD CONSTRAINT "FK_CONTENT_STATUS" FOREIGN KEY (STATUS_ID) REFERENCES TB_CONTENT_STATUS (STATUS_ID);

ALTER TABLE TB_CONTENT_PERMISSION ADD CONSTRAINT "FK_CONTENT_PERMISSION" FOREIGN KEY (CONTENT_ID) REFERENCES TB_CONTENT (CONTENT_ID) ON DELETE CASCADE;

ALTER TABLE TB_DIARY ADD CONSTRAINT "FK_DIARY_CONTENT" FOREIGN KEY (CONTENT_ID) REFERENCES TB_CONTENT (CONTENT_ID) ON DELETE CASCADE;

ALTER TABLE TB_MESSAGE ADD CONSTRAINT "FK_MESSAGE_CONTENT" FOREIGN KEY (CONTENT_ID) REFERENCES TB_CONTENT (CONTENT_ID) ON DELETE CASCADE;

ALTER TABLE TB_CONTAINER_SCHEDULE ADD CONSTRAINT "FK_SCHED_CONTAINER" FOREIGN KEY (CONTAINER_NAME,CONTAINER_TYPE) REFERENCES TB_CONTAINER (NAME,TYPE) ON DELETE CASCADE;
                            
ALTER TABLE TB_CONTAINER_SCHEDULE ADD CONSTRAINT "FK_SCHED_HTML" FOREIGN KEY (CONTENT_ID) REFERENCES TB_HTML (CONTENT_ID) ON DELETE CASCADE;                                                  

ALTER TABLE TB_POPUP_SCHEDULE ADD CONSTRAINT "FK_POPUP_CONT_SCHED" FOREIGN KEY (SCHEDULE_ID) REFERENCES TB_CONTAINER_SCHEDULE (SCHEDULE_ID) ON DELETE CASCADE;

ALTER TABLE TB_HTML ADD CONSTRAINT "FK_HTML_CONTENT" FOREIGN KEY (CONTENT_ID) REFERENCES TB_CONTENT (CONTENT_ID) ON DELETE CASCADE;