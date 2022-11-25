USE `tourism_agency.db`;
DROP PROCEDURE IF EXISTS addNewCountry;
DROP PROCEDURE IF EXISTS addTenCountries;
DROP PROCEDURE IF EXISTS fillManyToManyForClientsAndTrips;
DROP FUNCTION IF EXISTS findMaxId;
DROP PROCEDURE IF EXISTS generateTablesFromCountriesWithCursor;
# 2
# A
DELIMITER //
CREATE PROCEDURE addNewCountry(
	IN input_name VARCHAR(60),
    OUT out_name VARCHAR(60))
BEGIN
	INSERT INTO country(name) VALUES(input_name);
    SET out_name = input_name;
END //
DELIMITER ;


#B
DELIMITER //
CREATE PROCEDURE fillManyToManyForClientsAndTrips(
	IN id1 INT,
    IN id2 INT
)
BEGIN
	IF EXISTS (SELECT * FROM client WHERE id = id1) AND EXISTS (SELECT * FROM trip WHERE id = id2) THEN
		INSERT INTO `client_has_tour_with_guide`(`client_id`,`trip_id`) VALUES (id1,id2);
    ELSE 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'CHECK error for Insert in M:M table';
	END IF;
END // 
DELIMITER ;


set global log_bin_trust_function_creators = 1;

#D
DELIMITER //
CREATE FUNCTION findMaxId()
	RETURNS INT
BEGIN
	RETURN (SELECT MAX(id) FROM country);
END //
DELIMITER ;

# C
DELIMITER //
CREATE PROCEDURE addTenCountries()
BEGIN
    DECLARE str varchar(50);
	DECLARE x int;
    DECLARE id_found int;
	SET x = 1;
	cycle: WHILE x<=10 DO
		SET str = 'Noname';
		SET x = x + 1, id_found = findMaxId()+1;
		SET str = CONCAT(str,'-',id_found);
		INSERT INTO country(name) VALUES(str);
	END WHILE;
END //
DELIMITER ;

# E  cursor 3
DELIMITER //
CREATE PROCEDURE generateTablesFromCountriesWithCursor() 
BEGIN
	DECLARE done BOOL DEFAULT false;
    DECLARE NameOfCurrCountry VARCHAR(15);
    DECLARE random_number int;
    DECLARE name_of_table  VARCHAR(15);
    DECLARE cntry_Cursor CURSOR FOR SELECT name FROM country;
    
    DECLARE CONTINUE HANDLER
	FOR NOT FOUND SET done = true;

    OPEN cntry_Cursor;
    thisloop: LOOP
		FETCH cntry_Cursor INTO NameOfCurrCountry;
		IF done=true THEN LEAVE thisloop;
		END IF;
        SET NameOfCurrCountry = LOWER(NameOfCurrCountry);
        
        SET @query_for_drop = CONCAT('DROP DATABASE IF EXISTS ', NameOfCurrCountry);
        PREPARE drop_query FROM @query_for_drop;
        EXECUTE drop_query;
		DEALLOCATE PREPARE drop_query;
        
        # DROP DATABASE IF EXISTS NameOfCurrCountry;
		SET @temp_query=CONCAT('CREATE DATABASE IF NOT EXISTS ',
		NameOfCurrCountry);

		PREPARE myquery FROM @temp_query;
		EXECUTE myquery;
		DEALLOCATE PREPARE myquery;
        
        SET random_number = FLOOR(RAND() * 6 +1);
        innerCycle : WHILE random_number> 0 DO
			SET name_of_table = CONCAT(NameOfCurrCountry,'_',random_number);
            
			SET @query_to_create_tables=CONCAT('CREATE Table ',
			NameOfCurrCountry,'.',name_of_table,' (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20))');
			
            PREPARE inner_query FROM @query_to_create_tables;
            EXECUTE inner_query; 
            DEALLOCATE PREPARE inner_query;
            
            SET random_number = random_number  - 1;
        END WHILE;
	END LOOP;
	CLOSE cntry_Cursor;    
END //
DELIMITER ;

