package ru.itis.models;

import java.util.ArrayList;

public class Course {
    private Integer id;
    private String name;
    private String dateOfStart;
    private String dateOfEnd;
    private Integer teacher;
    private ArrayList<Student> students;

    public Course(String name, String dateOfStart, String dateOfEnd, Integer teacher) {
        this.name = name;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.teacher = teacher;
    }

    public Course(Integer id, String name, String dateOfStart, String dateOfEnd, Integer teacher, ArrayList<Student> students) {
        this.id = id;
        this.name = name;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.teacher = teacher;
        this.students = students;
    }

    public Course(Integer id, String name, String dateOfStart, String dateOfEnd, Integer teacher) {
        this.id = id;
        this.name = name;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(String dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public String getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(String dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", date='" + dateOfStart + '\'' +
                ", teacher=" + teacher +
                ", students=" + students +
                '}';
    }
}
