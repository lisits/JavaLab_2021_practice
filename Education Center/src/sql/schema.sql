create table lesson
(
    id           serial primary key,
    name         varchar(30) not null,
    day_and_time varchar(20) not null,
    id_course       int         not null,
    foreign key (id_course) references course(id)
);

create table course
(
    id          serial primary key,
    name        varchar(30) not null,
    dateOfStart varchar(10) not null,
    dateOfEnd   varchar(10) not null,
    teacher     int         not null
);

create table teachers_and_courses
(
    course_id  integer,
    teacher_id integer
);

create table student
(
    id         serial primary key,
    first_name varchar(20)                             not null,
    last_name  varchar(20)                             not null,
    age        integer check (age >= 0 and age <= 120) not null default 1
);

create table teacher
(
    id         serial primary key,
    first_name varchar(20)                                           not null,
    last_name  varchar(20)                                           not null,
    experience integer check (experience >= 0 and experience <= 120) not null default 0
);

create table students_on_courses
(
    student_id integer,
    course_id  integer
);