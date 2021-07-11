package ru.itis;

import ru.itis.models.Lesson;

import java.util.Optional;

public interface LessonsRepository {
    Optional<Lesson> findById (Integer id);
    Optional<Lesson> findByName (String name);
    void save (Lesson lesson);
    void update (Integer id, Lesson lesson);
}
