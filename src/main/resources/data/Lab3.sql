# DROP DATABASE IF EXISTS `tourism_agency.db`;
CREATE DATABASE IF NOT EXISTS `tourism_agency.db`;
USE `tourism_agency.db`;


-- Drop all values from tables
DROP table IF exists client_has_tour_with_guide;
DROP table IF exists trip;
DROP table IF exists guide_knows_language;
DROP table IF exists guide;
DROP table IF exists client;
DROP table IF exists tour;
DROP table IF exists language;
DROP table IF exists location;
DROP table IF exists country;
DROP table IF exists phone;
DROP table IF exists sight;

-- Table country
CREATE TABLE country (
   id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name  VARCHAR(60) NOT NULL,
  UNIQUE INDEX name_UNIQUE (name))
ENGINE = InnoDB;


-- Table phone 
CREATE TABLE phone (
   id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   number  VARCHAR(20) NOT NULL,
  UNIQUE INDEX  number_UNIQUE (number))
ENGINE = InnoDB;

-- Table guide 
CREATE TABLE guide (
   id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name  VARCHAR(45) NOT NULL,
   surname  VARCHAR(45) NOT NULL,
   email  VARCHAR(50) NULL,
   country_id  INT NOT NULL,
   phone_id  INT NOT NULL,
  INDEX  fk_guide_country1_idx (country_id),
  INDEX  fk_guide_phone1_idx  (phone_id),
  CONSTRAINT  fk_guide_country1 
    FOREIGN KEY (country_id)
    REFERENCES   country (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT  fk_guide_phone1 
    FOREIGN KEY (phone_id)
    REFERENCES   phone (id) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB;


-- Table language 
CREATE TABLE language (
   id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name  VARCHAR(30) NOT NULL,
  UNIQUE INDEX  name_UNIQUE  (name))
ENGINE = InnoDB;


-- Table location sightFKboh7kkr7bs6k59h092oet146u
CREATE TABLE location (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(45) NOT NULL,
   country_id INT NOT NULL,
  INDEX  fk_location_country1_idx  (country_id),
  CONSTRAINT  fk_location_country1 
    FOREIGN KEY (country_id)
    REFERENCES country (id)ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB;


-- Table tour
CREATE TABLE tour (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(60) NOT NULL,
   price DOUBLE NOT NULL,
   tour_days INT NOT NULL,
   description VARCHAR(700) NOT NULL,
   language_id INT NOT NULL,
   location_id INT NOT NULL,
  INDEX  fk_tour_language1_idx  (language_id),
  INDEX  fk_tour_location1_idx  (location_id),
  CONSTRAINT  fk_tour_language1 
    FOREIGN KEY (language_id)
    REFERENCES  language (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT  fk_tour_location1 
    FOREIGN KEY (location_id)
    REFERENCES  location (id) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB;


-- Table Trip 
CREATE TABLE trip (
   id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   guide_id  INT NOT NULL,
   tour_id  INT NOT NULL,
   date_start  DATE NOT NULL,
   guide_salary  DOUBLE NULL,
  INDEX  fk_trip_tour_idx  (tour_id),
  INDEX  fk_trip_guide_idx  (guide_id),
  CONSTRAINT  fk_trip_guide 
    FOREIGN KEY (guide_id)
    REFERENCES  guide (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT  fk_trip_tour 
    FOREIGN KEY (tour_id)
    REFERENCES  tour (id) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB;


-- Table  client 
CREATE TABLE client (
   id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name  VARCHAR(45) NOT NULL,
   country_id  INT NOT NULL,
   language_id  INT NOT NULL,
   phone_id  INT NOT NULL,
  INDEX  fk_client_country1_idx  (country_id),
  INDEX  fk_client_language1_idx  (language_id),
  INDEX  fk_client_phone1_idx  (phone_id),
  CONSTRAINT  fk_client_country1 
    FOREIGN KEY (country_id)
    REFERENCES  country (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT  fk_client_language1 
    FOREIGN KEY (language_id)
    REFERENCES  language (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT  fk_client_phone1 
    FOREIGN KEY (phone_id)
    REFERENCES  phone (id) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB;


-- Table  client_has_tour_with_guide 
CREATE TABLE client_has_tour_with_guide (
   client_id  INT NOT NULL,
   trip_id  INT NOT NULL,
  PRIMARY KEY (client_id, trip_id),
  INDEX  fk_client_has_guide_has_tour_trip1_idx  (trip_id),
  INDEX  fk_client_has_trip_client1_idx  (client_id),
  CONSTRAINT  fk_client_has_trip1 
    FOREIGN KEY (client_id)
    REFERENCES  client (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT  fk_guide_has_trip1 
    FOREIGN KEY (trip_id)
    REFERENCES  trip (id) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB;


-- Table  guide_knows_language 
CREATE TABLE guide_knows_language (
   language_id  INT NOT NULL,
   guide_id  INT NOT NULL,
   level_of_proficiency  VARCHAR(15) NOT NULL,
  PRIMARY KEY (guide_id, language_id),
  INDEX  fk_language_has_guide_guide1_idx  (guide_id),
  INDEX  fk_language_has_guide_language1_idx  (language_id),
  CONSTRAINT  fk_language_has_guide_language1 
    FOREIGN KEY (language_id)
    REFERENCES  language  (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT  fk_language_has_guide_guide1 
    FOREIGN KEY (guide_id)
    REFERENCES  guide (id) ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB;

-- Table country
CREATE TABLE sight (
   id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name  VARCHAR(60) NOT NULL,
   country_id INT NOT NULL)
ENGINE = InnoDB;


# SHOW INDEX FROM country;
# SHOW INDEX FROM tour;