USE `tourism_agency.db`;

-- 1. Get name from guide table, order them by name 
SELECT name FROM guide ORDER BY name;

-- 2. All tours where names has 'a' init and costs more then 800$
SELECT name, price FROM tour WHERE name LIKE '%a%' AND price > 800; 

-- 3. Select all tours with lowest cost
SELECT Name, Price FROM tour WHERE price = (SELECT min(price) FROM tour);

-- 4. Join of location and it's country
SELECT location.name as 'Location', country.name as 'Country'
FROM location JOIN country ON location.country_id = country.id;

-- 5. Many to many join Names of guides and languages they know
SELECT name,LanName FROM guide as g 
JOIN (SELECT guide_id,l.name as LanName FROM guide_knows_language as gl JOIN language as l ON gl.language_id = l.id) as knowlage ON g.id = knowlage.guide_id;

-- 6. Count how many languages does guide knows fluently
SELECT name, concat('Fluently: ', count(*)) as languages FROM guide as g 
JOIN (SELECT guide_id,l.name as LanName FROM guide_knows_language as gl JOIN language as l ON gl.language_id = l.id WHERE level_of_proficiency  = 'fluent')
as knowlage ON g.id = knowlage.guide_id
GROUP BY name;

-- 7. Скільки гіди отримали разом зв всі тури
SELECT name, surname, sum(guide_salary) as Income 
FROM guide JOIN guide_has_tour as gt ON guide.id = gt.guide_id 
GROUP BY name ORDER BY Income DESC;

-- 8. Порахувати для кожного клієнта всі доступні для нього тури його країною 
SELECT client.name as Name, count(*) as 'Awailable tours' 
FROM (SELECT client.name,country.name as client_country FROM client JOIN country ON client.country_id = country.id) as client
JOIN (SELECT tour.name ,loc.name as tour_country FROM tour JOIN (SELECT l.id, c.name FROM location as l JOIN country as c ON l.country_id = c.id) as loc ON loc.id = tour.location_id) as tour 
ON tour.tour_country = client.client_country 
GROUP BY Name;

-- 9. Якщо не знає англійської - приділити більше уваги
SELECT client.name as Name, language.name as Language, 
CASE 
	WHEN language.name = 'English' THEN 'YES' 
    ELSE 'More Attention' 
END as 'Knows English'
FROM client JOIN language ON client.language_id = language.id 
ORDER BY Name;

-- 10. Union of clients and tours and their languages
SELECT c.name as Name, l.name as Language FROM client as c JOIN language as l ON c.language_id = l.id
UNION 
SELECT concat('Tour ',t.name) as Name, l.name FROM tour as t JOIN language as l ON t.language_id = l.id;