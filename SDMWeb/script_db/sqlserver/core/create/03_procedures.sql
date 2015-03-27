-- =============================================
-- Creating the store procedure
-- =============================================
IF EXISTS (SELECT name 
	   FROM   sysobjects 
	   WHERE  name = 'FILLS_TB_HOURS_OF_DAY' 
	   AND 	  type = 'P')
    DROP PROCEDURE FILLS_TB_HOURS_OF_DAY
GO

CREATE PROCEDURE FILLS_TB_HOURS_OF_DAY
AS
	declare @hour  INTEGER
  	declare @m     INTEGER
  	declare @sHour VARCHAR(2)
  	declare @sM    VARCHAR(2)

	delete from TB_HOURS_OF_DAY
	
	set @hour = 0
	set @m = 0
	
	while (@hour < 24)
	begin
		set @sHour = cast(@hour as VARCHAR(2))
		if (@hour < 10)
			set @sHour = '0' + @sHour
		while (@m < 60)
		begin
		set @sM = cast(@m as VARCHAR(2))
		if (@m < 10)
			set @sM = '0' + @sM
			insert into TB_HOURS_OF_DAY (HOUR, MINUTE) values (@sHour, @sM)
			set @m = @m + 1
		end
		set @hour = @hour + 1
		set @m = 0
	end
GO