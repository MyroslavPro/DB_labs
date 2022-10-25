CREATE DATABASE IF NOT EXISTS `tourism_agency.db`;
USE `tourism_agency.db`;

-- Drop all values from tables
DROP table IF exists client_has_tour_with_guide;
DROP table IF exists guide_has_tour;
DROP table IF exists guide_knows_language;
DROP table IF exists guide;
DROP table IF exists client;
DROP table IF exists tour;
DROP table IF exists language;
DROP table IF exists location;
DROP table IF exists country;
DROP table IF exists tour_type;
DROP table IF exists phone;


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
    REFERENCES   country (id),
  CONSTRAINT  fk_guide_phone1 
    FOREIGN KEY (phone_id)
    REFERENCES   phone (id))
ENGINE = InnoDB;


-- Table language 
CREATE TABLE language (
   id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name  VARCHAR(30) NOT NULL,
  UNIQUE INDEX  name_UNIQUE  (name))
ENGINE = InnoDB;


-- Table location 
CREATE TABLE location (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(45) NOT NULL,
   country_id INT NOT NULL,
  INDEX  fk_location_country1_idx  (country_id),
  CONSTRAINT  fk_location_country1 
    FOREIGN KEY (country_id)
    REFERENCES country (id))
ENGINE = InnoDB;


-- Table tour_type 
CREATE TABLE tour_type (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(45) NOT NULL,
  UNIQUE INDEX name_UNIQUE (name))
ENGINE = InnoDB;


-- Table tour
CREATE TABLE tour (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   price DOUBLE NOT NULL,
   tour_days INT NOT NULL,
   description VARCHAR(700) NOT NULL,
   language_id INT NOT NULL,
   location_id INT NOT NULL,
   type_id INT NOT NULL,
  INDEX  fk_tour_language1_idx  (language_id),
  INDEX  fk_tour_location1_idx  (location_id),
  INDEX  fk_tour_tour_type1_idx  (type_id),
  CONSTRAINT  fk_tour_language1 
    FOREIGN KEY (language_id)
    REFERENCES  language (id),
  CONSTRAINT  fk_tour_location1 
    FOREIGN KEY (location_id)
    REFERENCES  location (id),
  CONSTRAINT  fk_tour_tour_type1 
    FOREIGN KEY (type_id)
    REFERENCES  tour_type (id))
ENGINE = InnoDB;


-- Table guide_has_tour 
CREATE TABLE guide_has_tour (
   id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   guide_id  INT NOT NULL,
   tour_id  INT NOT NULL,
   date_start  DATE NOT NULL,
   guide_salary  DOUBLE NULL,
  INDEX  fk_guide_has_tour_tour1_idx  (tour_id),
  INDEX  fk_guide_has_tour_guide_idx  (guide_id),
  CONSTRAINT  fk_guide_has_tour_guide 
    FOREIGN KEY (guide_id)
    REFERENCES  guide (id),
  CONSTRAINT  fk_guide_has_tour_tour1 
    FOREIGN KEY (tour_id)
    REFERENCES  tour (id))
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
    REFERENCES  country (id),
  CONSTRAINT  fk_client_language1 
    FOREIGN KEY (language_id)
    REFERENCES  language (id),
  CONSTRAINT  fk_client_phone1 
    FOREIGN KEY (phone_id)
    REFERENCES  phone (id))
ENGINE = InnoDB;


-- Table  client_has_tour_with_guide 
CREATE TABLE client_has_tour_with_guide (
   client_id  INT NOT NULL,
   guide_has_tour_id  INT NOT NULL,
  PRIMARY KEY (client_id, guide_has_tour_id),
  INDEX  fk_client_has_guide_has_tour_guide_has_tour1_idx  (guide_has_tour_id),
  INDEX  fk_client_has_guide_has_tour_client1_idx  (client_id),
  CONSTRAINT  fk_client_has_guide_has_tour_client1 
    FOREIGN KEY (client_id)
    REFERENCES  client (id),
  CONSTRAINT  fk_client_has_guide_has_tour_guide_has_tour1 
    FOREIGN KEY (guide_has_tour_id)
    REFERENCES  guide_has_tour (id))
ENGINE = InnoDB;
ALTER TABLE client_has_tour_with_guide  ;


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
    REFERENCES  language  (id),
  CONSTRAINT  fk_language_has_guide_guide1 
    FOREIGN KEY (guide_id)
    REFERENCES  guide (id))
ENGINE = InnoDB;


-- Data for table  country 
INSERT INTO country (name) 
VALUES ('Ukraine'),
('Albania'),
('US'),
('UK'),
('Australia'),
('Canada'),
('France'),
('Bolivia'),
('Spain'),
('Italy'),
('Greece');


-- Data for table  phone 
INSERT INTO phone (number) 
VALUES ('+1 202-918-2132'),
('+355 69 039 7383'),
('+220 585 5602'),
('+385 92 904 6897'),
('+290 56868'),
('+32 454 86 34 07'),
('+32 234 82 14 11'),
('+1 331-539-6120'),
('+1 582-322-4764'),
('+1 231-268-9930'),
('+1 203-984-2838'),
('+1 228-729-0992'),
('+1 505-916-0150'),
('+1 505-916-0343'),
('+1 458-386-3122');


-- Data for table  guide 
INSERT INTO guide (name, surname, email, country_id, phone_id) 
VALUES ('Willy', 'Wonka', 'wonkasweet@gmail.com', 3, 11),
('Barack', 'Obama', 'potus@gmai.com', 2, 12),
('John', 'Kenedy', 'fpotus@gmai.com', 2, 13),
('Jack', 'Sparrow', NULL, 4, 13),
('Liam', 'Black', 'liamblack13@mail.com', 3, 14);


-- Data for table  language 
INSERT INTO language (name) 
VALUES ('English'),
('French'),
('German'),
('Ukrainian'),
('Spanish'),
('Portuguese'),
('Hindi'),
('Italian'),
('Dutch'),
('Polish');


-- Data for table  location 
INSERT INTO location (name, country_id) 
VALUES ('Grand Canyon', 2),
('Mississippi ', 1),
('Black wood', 1),
('Baltimor', 3),
('Pyramids', 6),
('NYPark', 4),
('HolANDdiablo', 6),
('Cottage le france', 2),
('Hollywood', 7),
('North Pole', 9);


-- Data for table  tour_type 
INSERT INTO tour_type (name) 
VALUES ('Cruise'),
('Bus'),
('Pedestrian'),
('Hotel Stay');


-- Data for table  tour 
INSERT INTO tour (name, price, tour_days, description, language_id, location_id, type_id) 
VALUES ('RelaxHoliday', 856, 3, 'From the illuminated sunset strip of Las Vegas and the cactus-filled plains of Joshua Tree to the bright red bridge that dominates San Francisco and California\'s Disneyland where dreams come true. The West Coast of the USA is a paradise offering a range of adventures for the whole family', 1, 2, 4),
('AlaskaExplore ', 999, 5, 'From scenic flights and glacier walks to white water rafting trips, Alaska offers endless possibilities for adventure. Explore the snow-capped mountains in Denali National Park, watch the whimsical Northern Lights dance above Fairbanks, or visit Point Woronzof to see incredible wildlife such as bald eagles, moose, and perhaps even beluga whales. After your first visit to the Last Frontier, you’ll find yourself happily returning again and again.', 1, 1, 2),
('NYTrip', 450, 2, 'Trying to work out the best time to visit the Eastern United States? You’ve come to the right place! From the natural wonders of Maine to New York City’s architectural icons, read on for East Coast vacation ideas and itinerary inspiration for your next', 1, 3, 2),
('Stonehenge, Windsor Castle, and Bath from London', 450, 4, 'Visit some of the top attractions outside of London on this day trip to Stonehenge, Windsor Castle and the historic town of Bath. Start at Windsor Castle, home to the British royal family, for a tour of the State Apartments and St George’s Chapel, and then continue west of London to Salisbury, home of the mysterious Stonehenge rock formations.', 2, 4, 3),
('Canda Trip', 1260, 5, 'Canada really does have it all - from the dancing Northern Lights in Yukon and the fresh-powdered slopes of Banff, to the French heritage of Québec and the thundering waters of Niagara Falls. Whatever the season, Canada never ceases to amaze from sunlit hiking trails in the Canadian Rockies to glistening glaciers nestled in the parks of Alberta. Pair your Canadian adventure with Alaska for an ocean cruise along the West Coast, or time it right and see some whip crackin\' at the Calgary Stampede! ', 1, 3, 3),
('Rockies Premium Tour from Vancouver', 545, 4, 'Start and end in Vancouver! With the In-depth Cultural tour Rockies Premium Tour from Vancouver (34 Seats), you have a 5 days tour package taking you through Vancouver, Canada and 8 other destinations in Canada. Rockies Premium Tour from Vancouver (34 Seats) includes accommodation, an expert guide, meals, transport and more.', 1, 6, 3),
('Caribou', 1293, 8, 'Caribou includes accommodation in a hostel as well as an expert guide, meals, transport and more.', 3, 4, 2),
('Sea Horizon', 3000, 14, 'Relax at a biggest cruise liner ever built', 1, 3, 1);


-- Data for table  guide_has_tour 
INSERT INTO guide_has_tour (guide_id, tour_id, date_start, guide_salary) 
VALUES (3, 2, '2022-08-06', 200),
(1, 1, '2022-02-04', 333),
(2, 3, '2023-01-02', 121),
(1, 6, '2022-05-01', 444),
(4, 3, '2022-03-07', 587),
(3, 7, '2023-11-01', 378),
(2, 8, '2022-04-04', 242),
(4, 3, '2022-02-02', 3888),
(3, 5, '2023-01-01', 650),
(3, 4, '2022-02-16', 150);


-- Data for table  client 
INSERT INTO client (name, country_id, language_id, phone_id) 
VALUES ('Bond', 2, 4, 1),
('John', 3, 2, 2),
('Julia', 4, 1, 3),
('Marta', 3, 1, 4),
('Sam', 2, 1, 5),
('Bob', 5, 4, 6),
('Samanta', 6, 5, 7),
('Frederic', 7, 2, 8),
('Myron', 5, 1, 9),
('Willy', 8, 1, 10);

-- Data for table  client_has_tour_with_guide 
INSERT INTO client_has_tour_with_guide (client_id, guide_has_tour_id) 
VALUES (1, 4),
(3, 5),
(2, 5),
(4, 3),
(5, 5),
(2, 3),
(5, 2),
(4, 1),
(2, 1),
(7, 1);

-- Data for table  guide_knows_language 
INSERT INTO guide_knows_language (language_id, guide_id, level_of_proficiency) 
VALUES (1, 2, 'fluent'),
(2, 1, 'medium'),
(1, 3, 'medium'),
(3, 4, 'fluent'),
(5, 1, 'fluent'),
(3, 2, 'fluent'),
(4, 3, 'fluent'),
(2, 4, 'native'),
(6, 1, 'native');

SHOW INDEX FROM country;
SHOW INDEX FROM tour;