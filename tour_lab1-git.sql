-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tourism_agency.db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tourism_agency.db` DEFAULT CHARACTER SET utf8 ;
USE `tourism_agency.db` ;

-- ----------------------------------------------------
-- Drop all values from tables
-- ----------------------------------------------------

DROP table IF exists `tourism_agency.db`.`client`;
DROP table IF exists `tourism_agency.db`.`client_has_tour_with_guide`;
DROP table IF exists `tourism_agency.db`.`country`;
DROP table IF exists `tourism_agency.db`.`guide`;
DROP table IF exists `tourism_agency.db`.`guide_has_tour`;
DROP table IF exists `tourism_agency.db`.`guide_knows_language`;
DROP table IF exists `tourism_agency.db`.`language`;
DROP table IF exists `tourism_agency.db`.`location`;
DROP table IF exists `tourism_agency.db`.`phone`;
DROP table IF exists `tourism_agency.db`.`tour_type`;
DROP table IF exists `tourism_agency.db`.`tour`;

-- -----------------------------------------------------
-- Table `tourism_agency.db`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_agency.db`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tourism_agency.db`.`phone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_agency.db`.`phone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tourism_agency.db`.`guide`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_agency.db`.`guide` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(50) NULL,
  `country_id` INT NOT NULL,
  `phone_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_guide_country1_idx` (`country_id` ASC) VISIBLE,
  INDEX `fk_guide_phone1_idx` (`phone_id` ASC) VISIBLE,
  CONSTRAINT `fk_guide_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `tourism_agency.db`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_guide_phone1`
    FOREIGN KEY (`phone_id`)
    REFERENCES `tourism_agency.db`.`phone` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tourism_agency.db`.`language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_agency.db`.`language` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tourism_agency.db`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_agency.db`.`location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_location_country1_idx` (`country_id` ASC) VISIBLE,
  CONSTRAINT `fk_location_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `tourism_agency.db`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tourism_agency.db`.`tour_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_agency.db`.`tour_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tourism_agency.db`.`tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_agency.db`.`tour` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `price` DOUBLE NOT NULL,
  `tour_days` INT NOT NULL,
  `description` VARCHAR(700) NOT NULL,
  `language_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  `type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tour_language1_idx` (`language_id` ASC) VISIBLE,
  INDEX `fk_tour_location1_idx` (`location_id` ASC) VISIBLE,
  INDEX `fk_tour_tour_type1_idx` (`type_id` ASC) VISIBLE,
  CONSTRAINT `fk_tour_language1`
    FOREIGN KEY (`language_id`)
    REFERENCES `tourism_agency.db`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tour_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `tourism_agency.db`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tour_tour_type1`
    FOREIGN KEY (`type_id`)
    REFERENCES `tourism_agency.db`.`tour_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tourism_agency.db`.`guide_has_tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_agency.db`.`guide_has_tour` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `guide_id` INT NOT NULL,
  `tour_id` INT NOT NULL,
  `date_start` DATE NOT NULL,
  `guide_salary` DOUBLE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_guide_has_tour_tour1_idx` (`tour_id` ASC) VISIBLE,
  INDEX `fk_guide_has_tour_guide_idx` (`guide_id` ASC) VISIBLE,
  CONSTRAINT `fk_guide_has_tour_guide`
    FOREIGN KEY (`guide_id`)
    REFERENCES `tourism_agency.db`.`guide` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_guide_has_tour_tour1`
    FOREIGN KEY (`tour_id`)
    REFERENCES `tourism_agency.db`.`tour` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tourism_agency.db`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_agency.db`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT NOT NULL,
  `language_id` INT NOT NULL,
  `phone_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_client_country1_idx` (`country_id` ASC) VISIBLE,
  INDEX `fk_client_language1_idx` (`language_id` ASC) VISIBLE,
  INDEX `fk_client_phone1_idx` (`phone_id` ASC) VISIBLE,
  CONSTRAINT `fk_client_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `tourism_agency.db`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_language1`
    FOREIGN KEY (`language_id`)
    REFERENCES `tourism_agency.db`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_phone1`
    FOREIGN KEY (`phone_id`)
    REFERENCES `tourism_agency.db`.`phone` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tourism_agency.db`.`client_has_tour_with_guide`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_agency.db`.`client_has_tour_with_guide` (
  `client_id` INT NOT NULL,
  `guide_has_tour_id` INT NOT NULL,
  PRIMARY KEY (`client_id`, `guide_has_tour_id`),
  INDEX `fk_client_has_guide_has_tour_guide_has_tour1_idx` (`guide_has_tour_id` ASC) VISIBLE,
  INDEX `fk_client_has_guide_has_tour_client1_idx` (`client_id` ASC) VISIBLE,
  CONSTRAINT `fk_client_has_guide_has_tour_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `tourism_agency.db`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_has_guide_has_tour_guide_has_tour1`
    FOREIGN KEY (`guide_has_tour_id`)
    REFERENCES `tourism_agency.db`.`guide_has_tour` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tourism_agency.db`.`guide_knows_language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_agency.db`.`guide_knows_language` (
  `language_id` INT NOT NULL,
  `guide_id` INT NOT NULL,
  `level_of_proficiency` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`guide_id`, `language_id`),
  INDEX `fk_language_has_guide_guide1_idx` (`guide_id` ASC) VISIBLE,
  INDEX `fk_language_has_guide_language1_idx` (`language_id` ASC) VISIBLE,
  CONSTRAINT `fk_language_has_guide_language1`
    FOREIGN KEY (`language_id`)
    REFERENCES `tourism_agency.db`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_language_has_guide_guide1`
    FOREIGN KEY (`guide_id`)
    REFERENCES `tourism_agency.db`.`guide` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `tourism_agency.db`.`country`
-- -----------------------------------------------------
START TRANSACTION;
USE `tourism_agency.db`;
INSERT INTO `tourism_agency.db`.`country` (`id`, `name`) VALUES (DEFAULT, 'Ukraine');
INSERT INTO `tourism_agency.db`.`country` (`id`, `name`) VALUES (DEFAULT, 'Albania');
INSERT INTO `tourism_agency.db`.`country` (`id`, `name`) VALUES (DEFAULT, 'US');
INSERT INTO `tourism_agency.db`.`country` (`id`, `name`) VALUES (DEFAULT, 'UK');
INSERT INTO `tourism_agency.db`.`country` (`id`, `name`) VALUES (DEFAULT, 'Australia');
INSERT INTO `tourism_agency.db`.`country` (`id`, `name`) VALUES (DEFAULT, 'Canada');
INSERT INTO `tourism_agency.db`.`country` (`id`, `name`) VALUES (DEFAULT, 'France');
INSERT INTO `tourism_agency.db`.`country` (`id`, `name`) VALUES (DEFAULT, 'Bolivia');
INSERT INTO `tourism_agency.db`.`country` (`id`, `name`) VALUES (DEFAULT, 'Spain');
INSERT INTO `tourism_agency.db`.`country` (`id`, `name`) VALUES (DEFAULT, 'Italy');
INSERT INTO `tourism_agency.db`.`country` (`id`, `name`) VALUES (DEFAULT, 'Greece');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tourism_agency.db`.`phone`
-- -----------------------------------------------------
START TRANSACTION;
USE `tourism_agency.db`;
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+1 202-918-2132');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+355 69 039 7383');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+220 585 5602');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+385 92 904 6897');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+290 56868');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+32 454 86 34 07');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+32 234 82 14 11');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+1 331-539-6120');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+1 582-322-4764');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+1 231-268-9930');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+1 203-984-2838');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+1 228-729-0992');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+1 505-916-0150');
INSERT INTO `tourism_agency.db`.`phone` (`id`, `number`) VALUES (DEFAULT, '+1 458-386-3122');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tourism_agency.db`.`guide`
-- -----------------------------------------------------
START TRANSACTION;
USE `tourism_agency.db`;
INSERT INTO `tourism_agency.db`.`guide` (`id`, `name`, `surname`, `email`, `country_id`, `phone_id`) VALUES (DEFAULT, 'Willy', 'Wonka', 'wonkasweet@gmail.com', 3, 11);
INSERT INTO `tourism_agency.db`.`guide` (`id`, `name`, `surname`, `email`, `country_id`, `phone_id`) VALUES (DEFAULT, 'Barack', 'Obama', 'potus@gmai.com', 2, 12);
INSERT INTO `tourism_agency.db`.`guide` (`id`, `name`, `surname`, `email`, `country_id`, `phone_id`) VALUES (DEFAULT, 'Jack', 'Sparrow', NULL, 4, 13);
INSERT INTO `tourism_agency.db`.`guide` (`id`, `name`, `surname`, `email`, `country_id`, `phone_id`) VALUES (DEFAULT, 'Liam', 'Black', 'liamblack13@mail.com', 3, 14);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tourism_agency.db`.`language`
-- -----------------------------------------------------
START TRANSACTION;
USE `tourism_agency.db`;
INSERT INTO `tourism_agency.db`.`language` (`id`, `name`) VALUES (DEFAULT, 'English');
INSERT INTO `tourism_agency.db`.`language` (`id`, `name`) VALUES (DEFAULT, 'French');
INSERT INTO `tourism_agency.db`.`language` (`id`, `name`) VALUES (DEFAULT, 'German');
INSERT INTO `tourism_agency.db`.`language` (`id`, `name`) VALUES (DEFAULT, 'Ukrainian');
INSERT INTO `tourism_agency.db`.`language` (`id`, `name`) VALUES (DEFAULT, 'Spanish ');
INSERT INTO `tourism_agency.db`.`language` (`id`, `name`) VALUES (DEFAULT, 'Portuguese ');
INSERT INTO `tourism_agency.db`.`language` (`id`, `name`) VALUES (DEFAULT, 'Hindi ');
INSERT INTO `tourism_agency.db`.`language` (`id`, `name`) VALUES (DEFAULT, 'Italian');
INSERT INTO `tourism_agency.db`.`language` (`id`, `name`) VALUES (DEFAULT, 'Polish');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tourism_agency.db`.`location`
-- -----------------------------------------------------
START TRANSACTION;
USE `tourism_agency.db`;
INSERT INTO `tourism_agency.db`.`location` (`id`, `name`, `country_id`) VALUES (DEFAULT, 'Grand Canyon', 2);
INSERT INTO `tourism_agency.db`.`location` (`id`, `name`, `country_id`) VALUES (DEFAULT, 'Mississippi ', 1);
INSERT INTO `tourism_agency.db`.`location` (`id`, `name`, `country_id`) VALUES (DEFAULT, 'Black wood', 1);
INSERT INTO `tourism_agency.db`.`location` (`id`, `name`, `country_id`) VALUES (DEFAULT, 'Baltimor', 3);
INSERT INTO `tourism_agency.db`.`location` (`id`, `name`, `country_id`) VALUES (DEFAULT, 'Pyramids', 6);
INSERT INTO `tourism_agency.db`.`location` (`id`, `name`, `country_id`) VALUES (DEFAULT, 'NYPark', 4);
INSERT INTO `tourism_agency.db`.`location` (`id`, `name`, `country_id`) VALUES (DEFAULT, 'HolANDdiablo', 6);
INSERT INTO `tourism_agency.db`.`location` (`id`, `name`, `country_id`) VALUES (DEFAULT, 'Cottage le france', 2);
INSERT INTO `tourism_agency.db`.`location` (`id`, `name`, `country_id`) VALUES (DEFAULT, 'Hollywood', 7);
INSERT INTO `tourism_agency.db`.`location` (`id`, `name`, `country_id`) VALUES (DEFAULT, 'North Pole', 9);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tourism_agency.db`.`tour_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `tourism_agency.db`;
INSERT INTO `tourism_agency.db`.`tour_type` (`id`,`name`) VALUES (DEFAULT,'Cruise');
INSERT INTO `tourism_agency.db`.`tour_type` (`id`,`name`) VALUES (DEFAULT,'Bus');
INSERT INTO `tourism_agency.db`.`tour_type` (`id`,`name`) VALUES (DEFAULT,'Pedestrian');
INSERT INTO `tourism_agency.db`.`tour_type` (`id`,`name`) VALUES (DEFAULT,'Hotel Stay');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tourism_agency.db`.`tour`
-- -----------------------------------------------------
START TRANSACTION;
USE `tourism_agency.db`;
INSERT INTO `tourism_agency.db`.`tour` (`id`, `name`, `price`, `tour_days`, `description`, `language_id`, `location_id`, `type_id`) VALUES (DEFAULT, 'RelaxHoliday', 856, 3, 'From the illuminated sunset strip of Las Vegas and the cactus-filled plains of Joshua Tree to the bright red bridge that dominates San Francisco and California\'s Disneyland where dreams come true. The West Coast of the USA is a paradise offering a range of adventures for the whole family', 1, 2, 4);
INSERT INTO `tourism_agency.db`.`tour` (`id`, `name`, `price`, `tour_days`, `description`, `language_id`, `location_id`, `type_id`) VALUES (DEFAULT, 'AlaskaExplore ', 999, 5, 'From scenic flights and glacier walks to white water rafting trips, Alaska offers endless possibilities for adventure. Explore the snow-capped mountains in Denali National Park, watch the whimsical Northern Lights dance above Fairbanks, or visit Point Woronzof to see incredible wildlife such as bald eagles, moose, and perhaps even beluga whales. After your first visit to the Last Frontier, you’ll find yourself happily returning again and again.', 1, 1, 2);
INSERT INTO `tourism_agency.db`.`tour` (`id`, `name`, `price`, `tour_days`, `description`, `language_id`, `location_id`, `type_id`) VALUES (DEFAULT, 'NYTrip', 450, 2, 'Trying to work out the best time to visit the Eastern United States? You’ve come to the right place! From the natural wonders of Maine to New York City’s architectural icons, read on for East Coast vacation ideas and itinerary inspiration for your next', 1, 3, 2);
INSERT INTO `tourism_agency.db`.`tour` (`id`, `name`, `price`, `tour_days`, `description`, `language_id`, `location_id`, `type_id`) VALUES (DEFAULT, 'Stonehenge, Windsor Castle, and Bath from London', 450, 4, 'Visit some of the top attractions outside of London on this day trip to Stonehenge, Windsor Castle and the historic town of Bath. Start at Windsor Castle, home to the British royal family, for a tour of the State Apartments and St George’s Chapel, and then continue west of London to Salisbury, home of the mysterious Stonehenge rock formations.', 2, 4, 3);
INSERT INTO `tourism_agency.db`.`tour` (`id`, `name`, `price`, `tour_days`, `description`, `language_id`, `location_id`, `type_id`) VALUES (DEFAULT, 'Canda Trip', 1260, 5, 'Canada really does have it all - from the dancing Northern Lights in Yukon and the fresh-powdered slopes of Banff, to the French heritage of Québec and the thundering waters of Niagara Falls. Whatever the season, Canada never ceases to amaze from sunlit hiking trails in the Canadian Rockies to glistening glaciers nestled in the parks of Alberta. Pair your Canadian adventure with Alaska for an ocean cruise along the West Coast, or time it right and see some whip crackin\' at the Calgary Stampede! ', 1, 3, 3);
INSERT INTO `tourism_agency.db`.`tour` (`id`, `name`, `price`, `tour_days`, `description`, `language_id`, `location_id`, `type_id`) VALUES (DEFAULT, 'Rockies Premium Tour from Vancouver', 545, 4, 'Start and end in Vancouver! With the In-depth Cultural tour Rockies Premium Tour from Vancouver (34 Seats), you have a 5 days tour package taking you through Vancouver, Canada and 8 other destinations in Canada. Rockies Premium Tour from Vancouver (34 Seats) includes accommodation, an expert guide, meals, transport and more.', 1, 6, 3);
INSERT INTO `tourism_agency.db`.`tour` (`id`, `name`, `price`, `tour_days`, `description`, `language_id`, `location_id`, `type_id`) VALUES (DEFAULT, 'Caribou', 1293, 8, 'Caribou includes accommodation in a hostel as well as an expert guide, meals, transport and more.', 3, 4, 2);
INSERT INTO `tourism_agency.db`.`tour` (`id`, `name`, `price`, `tour_days`, `description`, `language_id`, `location_id`, `type_id`) VALUES (DEFAULT, 'Sea Horizon', 3000, 14, 'Relax at a biggest cruise liner ever built', 1, 3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tourism_agency.db`.`guide_has_tour`
-- -----------------------------------------------------
START TRANSACTION;
USE `tourism_agency.db`;
-- INSERT INTO `tourism_agency.db`.`guide_has_tour` (`id`, `guide_id`, `tour_id`, `date_start`, `guide_salary`) VALUES (DEFAULT, 3, 2, '2022-08-06', 200);
INSERT INTO `tourism_agency.db`.`guide_has_tour` (`id`, `guide_id`, `tour_id`, `date_start`, `guide_salary`) VALUES (DEFAULT, 1, 1, '2022-02-04', 333);
INSERT INTO `tourism_agency.db`.`guide_has_tour` (`id`, `guide_id`, `tour_id`, `date_start`, `guide_salary`) VALUES (DEFAULT, 2, 3, '2023-01-02', 121);
INSERT INTO `tourism_agency.db`.`guide_has_tour` (`id`, `guide_id`, `tour_id`, `date_start`, `guide_salary`) VALUES (DEFAULT, 1, 6, '2022-05-01', 444);
INSERT INTO `tourism_agency.db`.`guide_has_tour` (`id`, `guide_id`, `tour_id`, `date_start`, `guide_salary`) VALUES (DEFAULT, 4, 3, '2022-03-07', 587);
INSERT INTO `tourism_agency.db`.`guide_has_tour` (`id`, `guide_id`, `tour_id`, `date_start`, `guide_salary`) VALUES (DEFAULT, 3, 7, '2023-11-01', 378);
INSERT INTO `tourism_agency.db`.`guide_has_tour` (`id`, `guide_id`, `tour_id`, `date_start`, `guide_salary`) VALUES (DEFAULT, 2, 8, '2022-04-04', 242);
INSERT INTO `tourism_agency.db`.`guide_has_tour` (`id`, `guide_id`, `tour_id`, `date_start`, `guide_salary`) VALUES (DEFAULT, 4, 3, '2022-02-02', 3888);
INSERT INTO `tourism_agency.db`.`guide_has_tour` (`id`, `guide_id`, `tour_id`, `date_start`, `guide_salary`) VALUES (DEFAULT, 3, 5, '2023-01-01', 650);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tourism_agency.db`.`client`
-- -----------------------------------------------------
START TRANSACTION;
USE `tourism_agency.db`;
INSERT INTO `tourism_agency.db`.`client` (`id`, `name`, `country_id`, `language_id`, `phone_id`) VALUES (DEFAULT, 'Bond', 2, 4, 1);
INSERT INTO `tourism_agency.db`.`client` (`id`, `name`, `country_id`, `language_id`, `phone_id`) VALUES (DEFAULT, 'John', 3, 2, 2);
INSERT INTO `tourism_agency.db`.`client` (`id`, `name`, `country_id`, `language_id`, `phone_id`) VALUES (DEFAULT, 'Julia', 4, 1, 3);
INSERT INTO `tourism_agency.db`.`client` (`id`, `name`, `country_id`, `language_id`, `phone_id`) VALUES (DEFAULT, 'Marta', 3, 1, 4);
INSERT INTO `tourism_agency.db`.`client` (`id`, `name`, `country_id`, `language_id`, `phone_id`) VALUES (DEFAULT, 'Sam', 2, 1, 5);
INSERT INTO `tourism_agency.db`.`client` (`id`, `name`, `country_id`, `language_id`, `phone_id`) VALUES (DEFAULT, 'Bob', 5, 4, 6);
INSERT INTO `tourism_agency.db`.`client` (`id`, `name`, `country_id`, `language_id`, `phone_id`) VALUES (DEFAULT, 'Samanta', 6, 5, 7);
INSERT INTO `tourism_agency.db`.`client` (`id`, `name`, `country_id`, `language_id`, `phone_id`) VALUES (DEFAULT, 'Frederic', 7, 2, 8);
INSERT INTO `tourism_agency.db`.`client` (`id`, `name`, `country_id`, `language_id`, `phone_id`) VALUES (DEFAULT, 'Myron', 5, 1, 9);
INSERT INTO `tourism_agency.db`.`client` (`id`, `name`, `country_id`, `language_id`, `phone_id`) VALUES (DEFAULT, 'Willy', 8, 1, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tourism_agency.db`.`client_has_tour_with_guide`
-- -----------------------------------------------------
START TRANSACTION;
USE `tourism_agency.db`;
INSERT INTO `tourism_agency.db`.`client_has_tour_with_guide` (`client_id`, `guide_has_tour_id`) VALUES (1, 4);
INSERT INTO `tourism_agency.db`.`client_has_tour_with_guide` (`client_id`, `guide_has_tour_id`) VALUES (3, 5);
INSERT INTO `tourism_agency.db`.`client_has_tour_with_guide` (`client_id`, `guide_has_tour_id`) VALUES (2, 5);
INSERT INTO `tourism_agency.db`.`client_has_tour_with_guide` (`client_id`, `guide_has_tour_id`) VALUES (4, 3);
INSERT INTO `tourism_agency.db`.`client_has_tour_with_guide` (`client_id`, `guide_has_tour_id`) VALUES (5, 5);
INSERT INTO `tourism_agency.db`.`client_has_tour_with_guide` (`client_id`, `guide_has_tour_id`) VALUES (2, 3);
INSERT INTO `tourism_agency.db`.`client_has_tour_with_guide` (`client_id`, `guide_has_tour_id`) VALUES (7, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tourism_agency.db`.`guide_knows_language`
-- -----------------------------------------------------
START TRANSACTION;
USE `tourism_agency.db`;
INSERT INTO `tourism_agency.db`.`guide_knows_language` (`language_id`, `guide_id`, `level_of_proficiency`) VALUES (1, 2, 'fluent');
INSERT INTO `tourism_agency.db`.`guide_knows_language` (`language_id`, `guide_id`, `level_of_proficiency`) VALUES (2, 1, 'medium');
INSERT INTO `tourism_agency.db`.`guide_knows_language` (`language_id`, `guide_id`, `level_of_proficiency`) VALUES (1, 3, 'medium');
INSERT INTO `tourism_agency.db`.`guide_knows_language` (`language_id`, `guide_id`, `level_of_proficiency`) VALUES (3, 4, 'fluent');
INSERT INTO `tourism_agency.db`.`guide_knows_language` (`language_id`, `guide_id`, `level_of_proficiency`) VALUES (5, 1, 'fluent');
INSERT INTO `tourism_agency.db`.`guide_knows_language` (`language_id`, `guide_id`, `level_of_proficiency`) VALUES (3, 2, 'fluent');
INSERT INTO `tourism_agency.db`.`guide_knows_language` (`language_id`, `guide_id`, `level_of_proficiency`) VALUES (4, 3, 'fluent');
INSERT INTO `tourism_agency.db`.`guide_knows_language` (`language_id`, `guide_id`, `level_of_proficiency`) VALUES (2, 4, 'native');
INSERT INTO `tourism_agency.db`.`guide_knows_language` (`language_id`, `guide_id`, `level_of_proficiency`) VALUES (6, 1, 'native');

COMMIT;

