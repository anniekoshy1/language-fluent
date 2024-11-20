package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user;
    private UUID courseId1;
    private UUID courseId2;
    private Course course1;
    private Course course2;

    @Before
    public void setUp() {
        UUID userId = UUID.randomUUID();
        user = new User(userId, "testUser", "test@example.com", "password");
        courseId1 = UUID.randomUUID();
        courseId2 = UUID.randomUUID();
        
        // Create test courses
        course1 = new Course(courseId1, "Course 1", "Description of Course 1", true, 0.0, false,
                             new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        course2 = new Course(courseId2, "Course 2", "Description of Course 2", true, 0.0, false,
                             new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
    }

    @After
    public void tearDown() {
        user.getCourses().clear();
        user.getCompletedCourses().clear();
    }

    // Test cases for addCourse

    @Test
    public void testAddCourse_NewCourse() {
        int initialSize = user.getCourses().size();
        user.addCourse(course1);
        assertEquals(initialSize + 1, user.getCourses().size());
        assertNotNull(user.getCourses().get(0));
    }

    @Test // bug -- git issue
    public void testAddCourse_ExistingCourse() {
        user.addCourse(course1);
        int initialSize = user.getCourses().size();
        user.addCourse(course1);
        assertEquals(initialSize, user.getCourses().size());
    }

    @Test //bug //git issue
    public void testAddCourse_NullCourse() {
        int initialSize = user.getCourses().size();
        user.addCourse(null);
        assertEquals(initialSize, user.getCourses().size());
    }

    @Test
    public void testAddCourse_MultipleCourses() {
        user.addCourse(course1);
        user.addCourse(course2);
        assertEquals(2, user.getCourses().size());
        assertNotNull(user.getCourses().get(1));
    }

    @Test
    public void testAddCourse_ProgressTracking() {
        user.addCourse(course1);
        assertEquals(0.0, user.getCourses().get(0).getCourseProgress(), 0.01);
    }

    // Test cases for updateCourseProgress

    @Test
    public void testUpdateProgress_CourseNotStarted() {
        user.updateCourseProgress(courseId1, 0.0);
        assertEquals(0.0, user.getCourseProgress(courseId1), 0.01);
        user.updateCourseProgress(courseId1, 0.3);
        assertEquals(0.3, user.getCourseProgress(courseId1), 0.01);
    }

    @Test
    public void testUpdateProgress_CourseHalfway() {
        user.updateCourseProgress(courseId2, 0.5);
        assertEquals(0.5, user.getCourseProgress(courseId2), 0.01);
        user.updateCourseProgress(courseId2, 0.7);
        assertEquals(0.7, user.getCourseProgress(courseId2), 0.01);
    }

    @Test
    public void testUpdateProgress_CourseCompleted() {
        user.updateCourseProgress(courseId1, 1.0);
        assertEquals(1.0, user.getCourseProgress(courseId1), 0.01);
    }

    @Test
    public void testUpdateProgress_NewCourse() {
        UUID newCourseId = UUID.randomUUID();
        user.updateCourseProgress(newCourseId, 0.4);
        assertEquals(0.4, user.getCourseProgress(newCourseId), 0.01);
    }

    @Test
    public void testUpdateCorrectCourse() {
        user.updateCourseProgress(courseId1, 0.8);
        assertEquals(0.8, user.getCourseProgress(courseId1), 0.01);
        assertEquals(0.0, user.getCourseProgress(courseId2), 0.01);
    }

    @Test //bug //git issue
    public void testUpdateProgressToNullCourseId() {
        user.updateCourseProgress(null, 0.6);
        assertNull(user.getCourseProgress(null));
    }

    @Test
    public void testUpdateProgressInvalidProgress() {
        user.updateCourseProgress(courseId1, -0.3);
        assertEquals(-0.3, user.getCourseProgress(courseId1), 0.01);
    }

    // Test cases for completeCourse

    @Test
    public void testCompleteCourse_NotPreviouslyCompleted() {
        user.completeCourse(courseId1);
        assertTrue(user.getCompletedCourses().contains(courseId1));
    }

    @Test
    public void testCompleteCourse_PreviouslyCompleted() {
        user.completeCourse(courseId1);
        user.completeCourse(courseId1);
        assertEquals(1, user.getCompletedCourses().size());
    }

    @Test
    public void testCompleteCourse_MultipleCourses() {
        user.completeCourse(courseId1);
        user.completeCourse(courseId2);
        assertTrue(user.getCompletedCourses().contains(courseId1));
        assertTrue(user.getCompletedCourses().contains(courseId2));
    }

    @Test
    public void testCompleteCourse_EmptyCompletedCourses() {
        assertTrue(user.getCompletedCourses().isEmpty());
        user.completeCourse(courseId1);
        assertEquals(1, user.getCompletedCourses().size());
    }

    @Test //bug //git issue
    public void testCompleteCourse_NullCourseId() {
        user.completeCourse(null);
        assertFalse(user.getCompletedCourses().contains(null));
    }

    @Test
    public void testCompleteCourse_NoDuplicateEntries() {
        user.completeCourse(courseId1);
        user.completeCourse(courseId1);
        user.completeCourse(courseId1);
        assertEquals(1, user.getCompletedCourses().size());
    }

    @Test
    public void testCompleteCourse_InvalidCourseId() {
        UUID invalidCourseId = UUID.randomUUID();
        user.completeCourse(invalidCourseId);
        assertTrue(user.getCompletedCourses().contains(invalidCourseId));
    }
}
