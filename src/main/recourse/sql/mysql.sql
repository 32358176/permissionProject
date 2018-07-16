CREATE TRIGGER updateUserType BEFORE UPDATE ON users FOR EACH ROW
  BEGIN
    declare userWrong INT;
    SELECT new.PsdWrongTime INTO userWrong FOR UPDATE;
    IF userWrong >= 5 THEN
      SET new.IsLockout = '是',new.LockTime = now();
    END IF;
  END;

DROP TRIGGER updateUserType