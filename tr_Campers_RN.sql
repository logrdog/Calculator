CREATE OR REPLACE TRIGGER tr_Campers_RN
BEFORE INSERT ON Campers
FOR EACH ROW
BEGIN
	set transaction name 'camperEntry';
	:new.revNum := :old.revNum + 1;
	commit;
	EXCEPTION 
		WHEN OTHERS THEN 
			ROLLBACK;
END;
/