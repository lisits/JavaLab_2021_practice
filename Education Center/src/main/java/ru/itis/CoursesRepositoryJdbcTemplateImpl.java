package ru.itis;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.models.Course;
import ru.itis.models.Student;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoursesRepositoryJdbcTemplateImpl implements CoursesRepository {

    //language=SQL
    private static final String SQL_COURSES_INSERT = "insert into course(name, dateOfStart, dateOfEnd, teacher) values (?, ?, ?, ?)";
    //language=SQL
    private static final String SQL_SELECT_BY_ID_WITHOUT_STUDENTS = "select * from course where id = ?";
    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update course set name = ?, dateofstart = ?, dateofend = ?, teacher = ? where id = ?";
    //language=SQL
    private static final String SQL_SELECT_BY_NAME = "select * from course where name = ?";
    //language-SQL
    private static final String SQL_SELECT_BY_ID_WITH_STUDENTS = "select * from students_on_courses left join student on students_on_courses.student_id = student.id where course_id = ?";

    private JdbcTemplate jdbcTemplate;

    private final ResultSetExtractor<Course> courseResultSetExtractor = resultSet -> null;

    public CoursesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Course> courseRowMapper = (row, rowNumber) -> {
        int id = row.getInt("id");
        String name = row.getString("name");
        String dateOfStart = row.getString("dateofstart");
        String dateOfEnd = row.getString("dateofend");
        int teacher = row.getInt("teacher");
        Course course = new Course(id, name, dateOfStart, dateOfEnd, teacher);
        course.setStudents(new ArrayList<>());
        return course;
    };

    @Override
    public Optional<Course> findById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID_WITHOUT_STUDENTS, courseRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Course> findByIdWithStudents(Integer id) {
        ArrayList<Student> students = (ArrayList<Student>) jdbcTemplate.query(SQL_SELECT_BY_ID_WITH_STUDENTS,
                new ResultSetExtractor<List<Student>>() {
                    @Override
                    public ArrayList<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        ArrayList<Student> list = new ArrayList<Student>();
                        while (rs.next()) {
                            Student student = new Student("", "", 0);
                            student.setId(rs.getInt("id"));
                            student.setFirstName(rs.getString("first_name"));
                            student.setLastName(rs.getString("last_name"));
                            student.setAge(rs.getInt("age"));
                            list.add(student);
                        }
                        return list;
                    }
                }, id);
        Optional<Course> course;
        course = findById(id);
        if (!findById(id).equals(Optional.empty())) {
            Course course1 = course.get();
            course1.setStudents(students);
            return Optional.of(course1);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Course> findByName(String name) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, courseRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_COURSES_INSERT, new String[]{"id"});
            statement.setString(1, course.getName());
            statement.setString(2, course.getDateOfStart());
            statement.setString(3, course.getDateOfEnd());
            statement.setInt(4, course.getTeacher());
            return statement;
        }, keyHolder);
        course.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Integer id, Course course) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            statement.setString(1, course.getName());
            statement.setString(2, course.getDateOfStart());
            statement.setString(3, course.getDateOfEnd());
            statement.setInt(4, course.getTeacher());
            statement.setInt(5, id);
            return statement;
        });
    }
}
