package com.model;

import java.util.ArrayList;

public class CourseList {
    private final ArrayList<Course> courses;
    private static CourseList courseList;

    private CourseList() {
        courses = new ArrayList<>();
    }

    public static CourseList getInstance() {
        if (courseList == null) {
            courseList = new CourseList();
            courseList.loadCourses();
        }
        return courseList;
    }

    public void loadCourses() {
        DataLoader.loadCourses();
    }

    public void addCourse(Course course) {
        courses.add(course);
        saveCourses(); // Save after adding a course
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void saveCourses() {
        DataWriter.saveCourses();
    }

    public void addCourseWithoutSaving(Course course) {
        courses.add(course);
    }
}
