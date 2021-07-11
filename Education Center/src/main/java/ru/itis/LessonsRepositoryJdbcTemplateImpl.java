package ru.itis;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.models.Lesson;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Optional;

public class LessonsRepositoryJdbcTemplateImpl implements LessonsRepository {

    //language=SQL
    private static final String SQL_LESSONS_INSERT = "insert into lesson(name, day_and_time, course) values (?, ?, ?)";
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from lesson left join course on lesson.course = course.id where lesson.id = ?";
    //language=SQL
    private static final String SQL_SELECT_BY_NAME = "select * from lesson where name = ?";
    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update lesson set name = ?, day_and_time = ?, course = ? where id = ?";

    private JdbcTemplate jdbcTemplate;

    private final ResultSetExtractor<Lesson> lessonResultSetExtractor = resultSet -> null;

    private final RowMapper<Lesson> lessonRowMapper = (row, rowNumber) -> {
        int id = row.getInt("id");
        String name = row.getString("name");
        String dayAndTime = row.getString("day_and_time");
        int course = row.getInt("course");
        Lesson lesson = new Lesson(id, name, dayAndTime, course);
        return lesson;
    };

    public LessonsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Lesson> findById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, lessonRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Lesson> findByName(String name) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, lessonRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Lesson lesson) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_LESSONS_INSERT, new String[]{"id"});
            statement.setString(1, lesson.getName());
            statement.setString(2, lesson.getDayAndTime());
            statement.setInt(3, lesson.getCourse());
            return statement;
        }, keyHolder);
        lesson.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Integer id, Lesson lesson) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            statement.setString(1, lesson.getName());
            statement.setString(2, lesson.getDayAndTime());
            statement.setInt(3, lesson.getCourse());
            statement.setInt(4, id);
            return statement;
        });
    }
}
