USE Labor_SQL;
# Варіант 59.

-- 1. БД «Аеропорт». Знайдіть номери всіх рейсів, що не бувають у місті
-- 'Rostov'. Вивести: trip_no, town_from, town_to. Вихідні дані
-- впорядкувати за зростанням за стовпцем plane.
SELECT trip_no as TripNumber, town_from as StartPoint, town_to as EndPoint 
FROM trip as t
WHERE t.town_from <> 'Rostov' && t.town_to  <> 'Rostov'
ORDER BY t.plane;

-- 2. бД «Кораблі». вивести назви битв, які складаються із двох слів та
-- друге слово не закінчується літерою 'c'.
SELECT * FROM battles as b
WHERE b.name LIKE '% %' AND b.name NOT LIKE '%c';

-- 3. БД «Комп. фірма». Виведіть виробника, номер моделі та ціну кож-pc
-- ного комп’ютера, що є в БД. Вивести: maker, model, price.
SELECT maker, pc.model, price 
FROM product JOIN pc ON product.model = pc.model;  

-- 4. БД «Комп. фірма». Знайдіть виробників, що випускають одночасно
-- ПК та ноутбуки (використати ключове слово SOME). Вивести maker.
SELECT maker 
FROM product WHERE maker= SOME(SELECT maker FROM product WHERE type ='Laptop') AND type ='PC' 
GROUP BY maker ORDER BY maker;

-- 5. БД «Комп. фірма». Знайдіть виробників, які б випускали одночасно
-- ПК та ноутбуки зі швидкістю 750 МГц та вище. Виведіть: maker.
SELECT maker 
FROM product as p JOIN PC as pc ON p.model = pc.model
WHERE maker= SOME(SELECT maker FROM product as p JOIN laptop as l ON p.model = l.model WHERE type ='Laptop' AND speed >= 750) AND type ='PC' AND speed >= 750 
GROUP BY maker ORDER BY maker;

-- 6. БД «Кораблі». Вивести значення таблиці Ships із коментарями, на-
-- приклад, 'name: California', 'class: Tennessee', 'launched: 1921'.
SELECT concat('name: ',name) as name, concat('class: ',class) as class, concat('launched: ',launched) as launched 
FROM ships;

-- 7. бД «Аеропорт». визначіть кількість рейсів із міста 'London' для кож-
-- ної дати таблиці Pass_in_trip. Вивести: date, число рейсів.
SELECT date, count(*) as 'number of races' 
FROM trip as t JOIN Pass_in_trip as p ON t.trip_no = p.trip_no 
WHERE t.town_from = 'London' 
GROUP BY date;

-- 8. БД «Кораблі». Вкажіть назву, водотоннажність та число гармат ко-
-- раблів, що брали участь у битві при 'guadalcanal'. вивести: ship,
-- displacement, numguns. (Підказка: використовувати підзапити в
-- якості обчислювальних стовпців)
SELECT ship, displacement, numGuns 
FROM classes join (SELECT * FROM outcomes as o JOIN ships as s ON o.ship = s.name
WHERE battle = 'Guadalcanal') as subquery ON classes.class =subquery.class; 

-- 9. БД «комп. фірма». для таблиці Product отримати підсумковий набір
-- у вигляді таблиці зі стовпцями maker, pc, у якій для кожного вироб-
-- ника необхідно вказати, чи виробляє він ('yes'), чи ні ('no') відповідний
-- тип продукції. У першому випадку ('yes') додатково вказати поруч у
-- круглих дужках загальну кількість наявної (тобто, що знаходиться в
-- таблиці PC) продукції, наприклад, 'yes(2)'. (Підказка: використовувати
-- підзапити в якості обчислювальних стовпців та оператор CASE)
SELECT maker, 
CASE 
	WHEN maker=ANY(SELECT maker FROM product WHERE type= 'PC') THEN concat('yes(',(SELECT count(*) FROM product WHERE type= 'PC' GROUP BY maker HAVING maker=p.maker),')') 
	ELSE 'no'
END as pc 
FROM product as p 
GROUP BY maker;

-- 10. БД «Кораблі». Для кожного класу порахувати кількість кораблів,
-- що входить до нього (врахувати також кораблі в таблиці Outcomes,
-- яких немає в таблиці Ships). Вивести: class, кількість кораблів у класі.
-- (Підказка: використовувати оператор UNION та операцію EXISTS)
SELECT class,(SELECT count(*) FROM Ships WHERE Ships.class = Classes.class) as number_of_ships 
FROM Classes 
UNION 
SELECT 'Unknown' as class, count(Outcomes.ship) 
FROM Outcomes
WHERE NOT EXISTS(SELECT * FROM Ships WHERE Outcomes.ship = Ships.name);
