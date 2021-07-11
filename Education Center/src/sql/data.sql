insert into teacher (first_name, last_name, experience) VALUES ('Марат Мирзаевич', 'Арсланов', 50);
insert into teacher (first_name, last_name, experience) VALUES ('Максим Витальевич', 'Зубков', 10);
insert into teacher (first_name, last_name, experience) VALUES ('Елена Александровна', 'Широкова', 35);

insert into lesson (name, day_and_time, course) VALUES ('Основы права', '1 сентября', 2);
insert into lesson (name, day_and_time, course) VALUES ('Основы лева', '2 сентября', 1);
insert into lesson (name, day_and_time, course) VALUES ('Дискретная математика', '5 сентября', 3);

insert into course (name, dateOfStart, dateOfEnd, teacher) VALUES ('Алгебра и геометрия', '1 sep', '30th may', 1);
insert into course (name, dateOfStart, dateOfEnd, teacher) VALUES ('Математический анализ', '30th may', 1);
insert into course (name, dateOfStart, dateOfEnd, teacher) VALUES ('Дискретная математика', '1 sep', '30th may', 1);

insert into student (first_name, last_name, age) VALUES ('Кирилл', 'Аникин', 19);
insert into student (first_name, last_name, age) VALUES ('Ильдар', 'Асфандьяров', 20);
insert into student (first_name, last_name, age) VALUES ('Марк', 'Сафин', 20);

insert into students_on_courses (student_id, course_id) VALUES (1, 1);
insert into students_on_courses (student_id, course_id) VALUES (2, 1);
insert into students_on_courses (student_id, course_id) VALUES (2, 2);
insert into students_on_courses (student_id, course_id) VALUES (2, 3);

insert into teachers_and_courses (course_id, teacher_id) VALUES (1, 1);
insert into teachers_and_courses (course_id, teacher_id) VALUES (2, 3);
insert into teachers_and_courses (course_id, teacher_id) VALUES (3, 2);