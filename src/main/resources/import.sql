INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (1, 'Manuel', 'Rojas', 'MALE', 'manuelarr99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (2, 'Get', 'Tirso', 'FEMALE', 'getCarter99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (3, 'Victor', 'Arias', 'MALE', 'victor99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (4, 'Axl', 'Crespo', 'MALE', 'axl99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (5, 'Leonardo', 'Torres', 'MALE', 'leo99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (6, 'Indra', 'Cruz', 'FEMALE', 'indra99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (7, 'Teresita', 'Roldan', 'FEMALE', 'teresita99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (8, 'Susana', 'Cuevas', 'FEMALE', 'susana99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (9, 'Jose', 'Ortega', 'MALE', 'jose@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (10, 'Hermes', 'Montes', 'MALE', 'hermes99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (11, 'Jaime', 'Rabadán', 'MALE', 'jaime99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (12, 'Mariana', 'Tapia', 'FEMALE', 'mariana99@gmail.com', '1999-12-12');
INSERT INTO person (id, first_name, first_surname, gender, email, birthday) VALUES (13, 'Osiris', 'Flores', 'FEMALE', 'osiris99@gmail.com', '1999-12-12');


INSERT INTO manager (person_id) VALUES (1);
INSERT INTO manager (person_id) VALUES (2);
INSERT INTO employee (person_id, manager_person_id, type) VALUES (3, 1, 'REGULAR');
INSERT INTO employee (person_id, manager_person_id, type) VALUES (4, 1, 'TEMPORAL');
INSERT INTO employee (person_id, manager_person_id, type) VALUES (5, 1, 'REGULAR');
INSERT INTO employee (person_id, manager_person_id, type) VALUES (6, 1, 'REGULAR');
INSERT INTO employee (person_id, manager_person_id, type) VALUES (7, 1, 'REGULAR');
INSERT INTO employee (person_id, manager_person_id, type) VALUES (8, 1, 'TEMPORAL');
INSERT INTO employee (person_id, manager_person_id, type) VALUES (9, 1, 'TEMPORAL');
INSERT INTO employee (person_id, manager_person_id, type) VALUES (10, 1, 'TEMPORAL');
INSERT INTO employee (person_id, manager_person_id, type) VALUES (11, 1, 'REGULAR');
INSERT INTO employee (person_id, manager_person_id, type) VALUES (12, 1, 'TEMPORAL');
INSERT INTO employee (person_id, manager_person_id, type) VALUES (13, 1, 'REGULAR');