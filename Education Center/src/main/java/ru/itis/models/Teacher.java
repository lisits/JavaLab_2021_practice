package ru.itis.models;

import java.util.ArrayList;

public class Teacher {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer experience;
    private ArrayList<Course> courses;

    public Teacher(String firstName, String lastName, Integer experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
    }

    public Teacher(Integer id, String firstName, String lastName, Integer experience, ArrayList<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.courses = courses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", experience=" + experience +
                ", courses=" + courses +
                '}';
    }
}
