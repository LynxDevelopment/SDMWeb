CREATE OR REPLACE  PROCEDURE FILLS_TB_HOURS_OF_DAY
    as  
  hour NUMBER;
  m    NUMBER;  
begin
  delete TB_HOURS_OF_DAY;
  
  hour := 0;
  m := 0;
  loop
      loop
          insert into TB_HOURS_OF_DAY (HOUR, MINUTE) values (trim(to_char(hour, '09')), trim(to_char(m, '09')));
          m := m + 1;
      exit when m >= 60;
      end loop;
      hour := hour + 1;
      m := 0;
  exit when hour >= 24;
  end loop;
end;
/

alter procedure FILLS_TB_HOURS_OF_DAY compile;