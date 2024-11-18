package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class CourseListTest {

    private CourseList courseList;
    private Course course1;
    private Course course2;

    @Before
    public void setUp() {
        // Initialize the singleton instance
        courseList = CourseList.getInstance();

        // Clear existing courses to start fresh for each test
        courseList.getCourses().clear();

        // Create sample courses
        course1 = new Course(
                UUID.randomUUID(),
                "Spanish Basics",
                "An introductory Spanish course",
                true,
                0.0,
                false,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );

        course2 = new Course(
                UUID.randomUUID(),
                "Advanced Spanish",
                "An advanced Spanish course",
                true,
                0.0,
                false,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );
    }

    // Test adding a new course

    @Test
    public void testAddCourse_NewCourse() {
        int initialSize = courseList.getCourses().size();
        Course addedCourse = courseList.addCourse(course1);
        assertEquals(initialSize + 1, courseList.getCourses().size());
        assertTrue(courseList.getCourses().contains(course1));
        assertEquals(course1, addedCourse);
    }

    // Test adding a duplicate course

    //bug -- git issue
    @Test
    public void testAddCourse_DuplicateCourse() {
        courseList.addCourse(course1);
        int initialSize = courseList.getCourses().size();
        courseList.addCourse(course1); // Attempt to add the same course again
        assertEquals(initialSize, courseList.getCourses().size());
    }

    // Test adding a null course


    @Test //bug //its because of datawriter which technically doesnt work that why there is no expected output -- git issue
    public void testAddCourse_NullCourse() {
        int initialSize = courseList.getCourses().size();
        courseList.addCourse(null);
        assertEquals(initialSize, courseList.getCourses().size());
    }

    // Test removing an existing course

    @Test
    public void testRemoveCourse_ExistingCourse() {
        courseList.addCourse(course1);
        boolean result = courseList.removeCourse(course1);
        assertTrue(result);
        assertFalse(courseList.getCourses().contains(course1));
    }

    // Test removing a course that does not exist

    @Test
    public void testRemoveCourse_NonExistingCourse() {
        boolean result = courseList.removeCourse(course1);
        assertFalse(result);
    }

    // Test removing a null course

    @Test
    public void testRemoveCourse_NullCourse() {
        boolean result = courseList.removeCourse(null);
        assertFalse(result);
    }

    // Test finding a course by name (case-insensitive)

    @Test
    public void testFindByName_ExistingCourse() {
        courseList.addCourse(course1);
        Course foundCourse = courseList.findByName("spanish basics");
        assertNotNull(foundCourse);
        assertEquals(course1, foundCourse);
    }

    // Test finding a course by name that does not exist

    @Test
    public void testFindByName_NonExistingCourse() {
        courseList.addCourse(course1);
        Course foundCourse = courseList.findByName("French Basics");
        assertNull(foundCourse);
    }

    // Test finding a course by name with null input

    @Test(expected = IllegalArgumentException.class)
    public void testFindByName_NullName() {
        courseList.findByName(null);
    }

    // Test getting a course by valid ID

    @Test
    public void testGetCourseById_ExistingId() {
        courseList.addCourse(course1);
        Course foundCourse = courseList.getCourseById(course1.getId().toString());
        assertNotNull(foundCourse);
        assertEquals(course1, foundCourse);
    }

    // Test getting a course by ID that does not exist

    @Test
    public void testGetCourseById_NonExistingId() {
        Course foundCourse = courseList.getCourseById(UUID.randomUUID().toString());
        assertNull(foundCourse);
    }

    // Test getting a course by null ID

    @Test
    public void testGetCourseById_NullId() {
        Course foundCourse = courseList.getCourseById(null);
        assertNull(foundCourse);
    }

    // Test saving courses

    @Test
    public void testSaveCourses() {
        // Since saveCourses() interacts with file I/O, we can mock DataWriter or check that no exceptions occur
        try {
            courseList.addCourse(course1);
            courseList.saveCourses();
            // If no exceptions occur, we assume success
            assertTrue(true);
        } catch (Exception e) {
            fail("saveCourses() threw an exception: " + e.getMessage());
        }
    }
}
