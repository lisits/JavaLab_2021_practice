package ru.itis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        HikariConfig config = new HikariConfig();
        config.setPassword(properties.getProperty("db.password"));
        config.setUsername(properties.getProperty("db.user"));
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));

        DataSource dataSource = new HikariDataSource(config);

        CoursesRepository coursesRepository = new CoursesRepositoryJdbcTemplateImpl(dataSource);

        LessonsRepository lessonsRepository = new LessonsRepositoryJdbcTemplateImpl(dataSource);
        System.out.println(coursesRepository.findById(1));
        System.out.println(coursesRepository.findByIdWithStudents(1));
        System.out.println(coursesRepository.findByName("Алгебра"));
        System.out.println(lessonsRepository.findByName("Основы права"));
        System.out.println(lessonsRepository.findById(3));
    }
}
