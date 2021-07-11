package ru.itis.models;

public class Lesson {
    private Integer id;
    private String name;
    private String dayAndTime;
    private Integer course;

    public Lesson(String name, String dayAndTime, Integer course) {
        this.name = name;
        this.dayAndTime = dayAndTime;
        this.course = course;
    }

    public Lesson(Integer id, String name, String dayAndTime, Integer course) {
        this.id = id;
        this.name = name;
        this.dayAndTime = dayAndTime;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayAndTime() {
        return dayAndTime;
    }

    public void setDayAndTime(String dayAndTime) {
        this.dayAndTime = dayAndTime;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", dayAndTime='" + dayAndTime + '\'' +
                ", course=" + course +
                '}';
    }
}
