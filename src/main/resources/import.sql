INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (1, 'Manuel', 'Rojas', 'MALE', 'manuelarr99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (2, 'Get', 'Tirso', 'FEMALE', 'getCarter99@gmail.com', '1999-12-12');


INSERT INTO manager (person_id) VALUES (1);
INSERT INTO employee (person_id, manager_person_id, type) VALUES (2, 1, 'TEMPORAL');