
-------------------------------------
-- Data di inizio delle statistiche:
-------------------------------------

SELECT MIN(SESSION_START) 
FROM TB_TRACKING;


----------------------------------------------
-- Numero totale di visitatori ( = sessioni )
----------------------------------------------

SELECT COUNT(*) 
FROM TB_TRACKING;


-------------------------------------------------------
-- Numero medio di visitatori ( = sessioni ) al giorno
-------------------------------------------------------

SELECT 
CASE WHEN SESSION_START IS NOT NULL THEN 
(SELECT COUNT(*) / COUNT(DISTINCT TRUNC(SESSION_START)) FROM TB_TRACKING) END
FROM TB_TRACKING;


--------------------------------------------------------
-- Durata media in minuti della sessione di navigazione
--------------------------------------------------------

SELECT AVG(SESSION_END-SESSION_START)*1440 
FROM TB_TRACKING 
WHERE SESSION_START IS NOT NULL AND SESSION_END IS NOT NULL;


------------------------------------------------
-- Numero medio di pagine visitate dagli utenti
------------------------------------------------

SELECT AVG(COUNT(*)) 
FROM TB_PAGES 
GROUP BY TRACKING_ID;


-------------------------------------------------
-- Numero massimo di visite ( = sessioni ) al mese
-------------------------------------------------

SELECT TRUNC(LAST_DAY(SESSION_START)) AS MONTH, COUNT(*) AS SESSIONS
FROM TB_TRACKING 
GROUP BY TRUNC(LAST_DAY(SESSION_START)) 
ORDER BY SESSIONS DESC;


-----------------------------------------------------
-- Numero massimo di visite ( = sessioni ) al giorno
-----------------------------------------------------

SELECT (TRUNC(SESSION_START)) AS DAY, COUNT(*) AS SESSIONS
FROM TB_TRACKING 
GROUP BY (TRUNC(SESSION_START)) 
ORDER BY SESSIONS DESC;


----------------------------------------------------
-- Numero visite ( = sessioni ) nei giorni del mese
----------------------------------------------------

SELECT TO_CHAR(SESSION_START,'dd/MM/yyyy') AS DAY, COUNT(*) AS SESSIONS 
FROM TB_TRACKING 
WHERE TO_CHAR(SESSION_START,'MM/yyyy') = TO_CHAR(SYSDATE,'MM/yyyy')
GROUP BY TO_CHAR(SESSION_START,'dd/MM/yyyy') 
ORDER BY DAY ASC;


--------------------
-- Utilizzo browser
--------------------

SELECT BROWSER, COUNT(*) AS USAGE 
FROM TB_TRACKING
GROUP BY BROWSER
ORDER BY BROWSER ASC


------------------------
-- Utilizzo piattaforme
------------------------

SELECT PLATFORM, COUNT(*) AS USAGE
FROM TB_TRACKING
GROUP BY PLATFORM
ORDER BY PLATFORM ASC


------------------------------
-- Utilizzo risoluzione video
------------------------------

SELECT SCREEN_SIZE, COUNT(*) AS USAGE
FROM TB_TRACKING
GROUP BY SCREEN_SIZE
ORDER BY SCREEN_SIZE DESC


------------------------
-- Pagine piu' visitate
------------------------

SELECT PAGE_NAME, COUNT(*) AS VISITS
FROM TB_PAGES 
GROUP BY PAGE_NAME 
ORDER BY VISITS DESC


--------------------------------------------------------------------------
-- Numero di accessi ( sessioni e utenti ) per "REGIONE" nell'ultimo mese
--------------------------------------------------------------------------

SELECT REGIONE AS GROUPING, COUNT(*) AS SESSIONS, COUNT(DISTINCT(USER_ID)) AS USERS
FROM (SISAL_TIES INNER JOIN SISAL_RICE_COMM ON SISAL_TIES.COD_TIES=SISAL_RICE_COMM.COD_TIES) INNER JOIN TB_TRACKING ON SISAL_RICE_COMM.CODICE_IVR=TB_TRACKING.USER_ID
WHERE TO_CHAR(SESSION_START,'MM/yyyy') = TO_CHAR(SYSDATE,'MM/yyyy')
GROUP BY REGIONE
ORDER BY REGIONE;


------------------------------------------------------------------------------------------
-- Numero di accessi ( sessioni e utenti ) per "REGIONE" nelle settimane dell'ultimo mese
------------------------------------------------------------------------------------------

SELECT REGIONE AS GROUPING, TO_CHAR(SESSION_START, 'W') AS WEEK, COUNT (*) AS SESSIONS, COUNT(DISTINCT(USER_ID)) AS USERS
FROM (SISAL_TIES INNER JOIN SISAL_RICE_COMM ON SISAL_TIES.COD_TIES=SISAL_RICE_COMM.COD_TIES) INNER JOIN TB_TRACKING ON SISAL_RICE_COMM.CODICE_IVR=TB_TRACKING.USER_ID
WHERE TO_CHAR(SESSION_START,'MM/yyyy') = TO_CHAR(SYSDATE,'MM/yyyy')
GROUP BY REGIONE, TO_CHAR(SESSION_START, 'W')
ORDER BY REGIONE, TO_CHAR(SESSION_START, 'W');




