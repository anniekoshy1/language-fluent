package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class CourseList {

    private final ArrayList<Course> courses;  
    private static CourseList courseList;

    private CourseList() {
        courses = new ArrayList<>();
    }

    public static CourseList getInstance() {
        if (courseList == null) {
            courseList = new CourseList();
        }
        return courseList;
    }

    public void loadCourses(){
        DataLoader.loadCourses();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void saveCourses() {
        DataWriter.saveCourses();
    }

public ArrayList<Course> getAvailableCourses(UUID currentCourseId) {
    ArrayList<Course> availableCourses = new ArrayList<>();
    for (Course course : courses) {
        // Always add the course to the list
        availableCourses.add(course);

        // Restrict access if this is not the current course and user does not have access
        if (!course.getId().equals(currentCourseId) && !course.getUserAccess()) {
            System.out.println(course.getName() + " is locked. Complete the previous course to unlock.");
        }
    }
    return availableCourses;
}

public void completeCourse(UUID courseId) {
    for (int i = 0; i < courses.size(); i++) {
        if (courses.get(i).getId().equals(courseId)) {
            courses.get(i).setCompleted(true);
            if (i + 1 < courses.size()) {
                courses.get(i + 1).setUserAccess(true);
            }
            break;
        }
    }
}


    
}
