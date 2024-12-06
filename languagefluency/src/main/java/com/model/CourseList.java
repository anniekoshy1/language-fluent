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
            courseList.loadCourses();
        }
        return courseList;
    }


    public void loadCourses() {
        DataLoader.loadCourses();
    }

    //add course to list of course
    public void addCourse(Course course) {
        courses.add(course);
    }


    //gets indivdual course by its name
    public Course getCourse(String identifier ){
        for(Course course: courses){
        if (course.getName().equalsIgnoreCase(identifier) || course.getId().toString().equals(identifier)) {
            return course;
        }
        }
        return null;
    }

    //list of all courses
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourseWithoutSaving(Course course) {
        courses.add(course);
    }

    //later problem
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

    //supposed to update a courses progress as complete
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

    
    public void saveCourses() {
        DataWriter.saveCourses();
    }
    
}
