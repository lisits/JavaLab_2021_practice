package ru.itis;

import ru.itis.models.Course;

import java.util.Optional;

public interface CoursesRepository {
    Optional<Course> findById (Integer id);
    Optional<Course> findByIdWithStudents(Integer id);
    Optional<Course> findByName (String name);
    void save (Course course);
    void update (Integer id, Course course);
}
