-------------------------------------
-- Data di inizio delle statistiche:
-------------------------------------

SELECT MIN (session_start) 
FROM TB_TRACKING

----------------------------------------------
-- Numero totale di visitatori ( = sessioni )
----------------------------------------------

SELECT COUNT(DISTINCT( TRACKING_ID )) 
FROM  TB_TRACKING

-------------------------------------------------------
-- Numero medio di visitatori ( = sessioni ) al giorno
-------------------------------------------------------

SELECT CASE WHEN SESSION_START IS NOT NULL 
	THEN 
	       (
			SELECT COUNT(*) / COUNT(DISTINCT (CONVERT(CHAR(10),SESSION_START,110)))
			FROM TB_TRACKING
		) 
	END 
FROM TB_TRACKING


--------------------------------------------------------
-- Durata media in minuti della sessione di navigazione
--------------------------------------------------------

SELECT  avg(datediff (minute,SESSION_START,SESSION_END) )
FROM TB_TRACKING
WHERE NOT SESSION_START IS NULL AND NOT SESSION_END IS NULL

------------------------------------------------
-- Numero medio di pagine visitate dagli utenti
------------------------------------------------

SELECT AVG(c.COUNT) 
FROM ( 
	SELECT COUNT(*) AS COUNT 
	FROM TB_PAGES 
	GROUP BY TRACKING_ID
     ) c

-----------------------------------------------------
-- Numero massimo di visite ( = sessioni ) al giorno
-----------------------------------------------------


SELECT CONVERT(CHAR(10),SESSION_START,110) AS GIORNO, COUNT(*) AS TOTALE
FROM TB_TRACKING 
GROUP BY CONVERT(CHAR(10),SESSION_START,110)
ORDER BY TOTALE DESC

-------------------------------------------------
-- Numero massimo di visite ( = sessioni ) al mese
-------------------------------------------------

SELECT DATEPART(mm, SESSION_START)AS MESE,DATEPART(yyyy, SESSION_START) AS ANNO, COUNT(*) AS TOTALE 
FROM TB_TRACKING 
GROUP BY DATEPART(mm, SESSION_START),DATEPART(yyyy, SESSION_START)
ORDER BY TOTALE  DESC