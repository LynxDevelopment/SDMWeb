delimiter $$

CREATE PROCEDURE FILLS_TB_HOURS_OF_DAY ()
BEGIN

   declare h INTEGER;
   declare m INTEGER;
   declare sHour CHAR(2);
   declare sM    CHAR(2);

   set h = 0;
   set m = 0;

  delete from TB_HOURS_OF_DAY;

   WHILE h < 24 DO
      set sHour = CAST(h as CHAR(2));

      if h < 10 then
        set sHour = '0' + sHour;
      end if;

      WHILE m < 60 DO

        set sM = CAST(m as CHAR(2));

        if sM < 10 then
          set sM = '0' + sM;
        end if;

        insert into TB_HOURS_OF_DAY (HOUR, MINUTE) values (sHour, sM);
  			set m = m + 1;

      END WHILE;
      set m = 0;
      set h = h + 1;
   END WHILE;

END$$

delimiter ;
