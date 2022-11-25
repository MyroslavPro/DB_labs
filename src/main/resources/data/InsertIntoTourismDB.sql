USE `tourism_agency.db`;

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
('Barack', 'Bobama', 'potus@gmai.com', 2, 12),
('John', 'Jenedy', 'fpotus@gmai.com', 2, 13),
('Jack', 'Jsparrow', 'karamba@gmai.com', 4, 14),
('Liam', 'LiBlack', 'liamblack13@mail.com', 3, 15);


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


-- Data for table  tour 
INSERT INTO tour (name, price, tour_days, description, language_id, location_id) 
VALUES ('RelaxHoliday', 856, 3, 'From the illuminated sunset strip of Las Vegas and the cactus-filled plains of Joshua Tree to the bright red bridge that dominates San Francisco and California\'s Disneyland where dreams come true. The West Coast of the USA is a paradise offering a range of adventures for the whole family', 1, 2),
('AlaskaExplore ', 999, 5, 'From scenic flights and glacier walks to white water rafting trips, Alaska offers endless possibilities for adventure. Explore the snow-capped mountains in Denali National Park, watch the whimsical Northern Lights dance above Fairbanks, or visit Point Woronzof to see incredible wildlife such as bald eagles, moose, and perhaps even beluga whales. After your first visit to the Last Frontier, you’ll find yourself happily returning again and again.', 1, 1),
('NYTrip', 450, 2, 'Trying to work out the best time to visit the Eastern United States? You’ve come to the right place! From the natural wonders of Maine to New York City’s architectural icons, read on for East Coast vacation ideas and itinerary inspiration for your next', 1, 3),
('Stonehenge, Windsor Castle, and Bath from London', 450, 4, 'Visit some of the top attractions outside of London on this day trip to Stonehenge, Windsor Castle and the historic town of Bath. Start at Windsor Castle, home to the British royal family, for a tour of the State Apartments and St George’s Chapel, and then continue west of London to Salisbury, home of the mysterious Stonehenge rock formations.', 2, 4),
('Canda Trip', 1260, 5, 'Canada really does have it all - from the dancing Northern Lights in Yukon and the fresh-powdered slopes of Banff, to the French heritage of Québec and the thundering waters of Niagara Falls. Whatever the season, Canada never ceases to amaze from sunlit hiking trails in the Canadian Rockies to glistening glaciers nestled in the parks of Alberta. Pair your Canadian adventure with Alaska for an ocean cruise along the West Coast, or time it right and see some whip crackin\' at the Calgary Stampede! ', 1, 3),
('Rockies Premium Tour from Vancouver', 545, 4, 'Start and end in Vancouver! With the In-depth Cultural tour Rockies Premium Tour from Vancouver (34 Seats), you have a 5 days tour package taking you through Vancouver, Canada and 8 other destinations in Canada. Rockies Premium Tour from Vancouver (34 Seats) includes accommodation, an expert guide, meals, transport and more.', 1, 6),
('Caribou', 1293, 8, 'Caribou includes accommodation in a hostel as well as an expert guide, meals, transport and more.', 3, 4),
('Sea Horizon', 3000, 14, 'Relax at a biggest cruise liner ever built', 1, 3);


-- Data for table  trip
INSERT INTO trip (guide_id, tour_id, date_start, guide_salary) 
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
INSERT INTO client_has_tour_with_guide (client_id, trip_id) 
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

-- Data for table sight
INSERT INTO sight (name, country_id) 
VALUES ('New2',1),
('AFAFd',5),
('gfgsf',2),
('New1',3);
