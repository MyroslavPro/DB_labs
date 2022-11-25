USE `tourism_agency.db`;

DROP TRIGGER IF EXISTS AfterDeleteCountry;
DROP TRIGGER IF EXISTS BeforeInsertClient;
DROP TRIGGER IF EXISTS BeforeInsertGuide;

DROP TRIGGER IF EXISTS BeforeInsertSight;
DROP TRIGGER IF EXISTS BeforeUpdateSight;
DROP TRIGGER IF EXISTS AfterCountryDeleteForSight;
DROP TRIGGER IF EXISTS AfterCountryUpdateForSight;



# 1 for new table sight with M:1 to country
DELIMITER //
CREATE TRIGGER BeforeInsertSight
BEFORE INSERT 
ON sight FOR EACH ROW
BEGIN
	IF NEW.`country_id` not in (SELECT id FROM country) THEN 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Error: insert with this FK is Not Possible';
	END IF;
END  
//
DELIMITER 
--
DELIMITER //
CREATE TRIGGER BeforeUpdateSight
BEFORE UPDATE 
ON sight FOR EACH ROW
BEGIN
	IF NEW.`country_id` not in (SELECT id FROM country) THEN 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Error: Update with this FK is Not Possible';
	END IF;
END  
//
DELIMITER 
-- -------------------
DELIMITER //
CREATE TRIGGER AfterCountryDeleteForSight
AFTER DELETE 
ON country FOR EACH ROW
BEGIN
	DELETE FROM sight WHERE country_id=old.`id`;
END  
//
DELIMITER 
--
DELIMITER //
CREATE TRIGGER AfterCountryUpdateForSight
AFTER UPDATE 
ON country FOR EACH ROW
BEGIN
	UPDATE sight SET country_id=new.`id` WHERE country_id=old.`id`;
END  
//
DELIMITER 



# 3.D - min cardinality
DELIMITER //
CREATE TRIGGER AfterDeleteCountry
AFTER DELETE 
ON country FOR EACH ROW
BEGIN
	IF (SELECT COUNT(*) FROM country) < 6 THEN 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Error:can`t be less than 6 rows';
	END IF;
END  
//
DELIMITER 

# 3.j
DELIMITER //
CREATE TRIGGER BeforeInsertClient
BEFORE INSERT 
ON client FOR EACH ROW
BEGIN
	IF NEW.`name` NOT IN ('Myron','Willy','Frederic','Samanta','Sam','Bob','Julia','Marta','John','Bond') THEN 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Error: name should be one of the permitted';
	END IF;
END  
//
DELIMITER 

# 3.k 
DELIMITER //
CREATE TRIGGER BeforeInsertGuide 
BEFORE INSERT 
ON guide FOR EACH ROW
BEGIN
	IF left(NEW.`surname`,1) != left(NEW.`name`,1) THEN 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Error: name and surname should start with the same letter';
	END IF;
END  
//
DELIMITER 

