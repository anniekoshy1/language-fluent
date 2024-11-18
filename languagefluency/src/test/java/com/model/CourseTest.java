package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CourseTest {

    private Course course;
    private UUID courseId;
    private Lesson lesson1;
    private Lesson lesson2;
    private FlashcardQuestion flashcard;
    private Assessment assessment1;
    private Assessment assessment2;

    @Before
    public void setUp() {
        courseId = UUID.randomUUID();

        // Initialize the course with default values
        course = new Course(
                courseId,
                "Test Course",
                "A course for testing",
                true,
                0.0,
                false,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                flashcard
        );

        // Create test lessons
        lesson1 = new Lesson("Lesson 1", "Introduction", "Content in English", "Contenido en Español");
        lesson2 = new Lesson("Lesson 2", "Advanced Topics", "Advanced English Content", "Contenido Avanzado en Español");

        // Create a flashcard with initial progress
        flashcard = new FlashcardQuestion("Flashcard Content", "Flashcard Answer");

        // Create assessments
        assessment1 = new Assessment(UUID.randomUUID(), Assessment.AssessmentType.MULTIPLE_CHOICE, new ArrayList<>());
        assessment2 = new Assessment(UUID.randomUUID(), Assessment.AssessmentType.TRUE_FALSE, new ArrayList<>());
    }

    @Test
    public void testDefaultConstructor() {
        Course defaultCourse = new Course();

        // Check if the generated ID is not null
        assertNotNull(defaultCourse.getId());

        // Verify default values of other fields
        assertEquals("", defaultCourse.getName());
        assertEquals("", defaultCourse.getDescription());
        assertFalse(defaultCourse.getUserAccess());
        assertEquals(0.0, defaultCourse.getCourseProgress(), 0.01);
        assertFalse(defaultCourse.isCompletedCourse());
        assertNotNull(defaultCourse.getAllLessons());
        assertTrue(defaultCourse.getAllLessons().isEmpty());
        assertNotNull(defaultCourse.getAllAssessments());
        assertTrue(defaultCourse.getAllAssessments().isEmpty());
        assertNotNull(defaultCourse.getKeyWords());
        assertTrue(defaultCourse.getKeyWords().isEmpty());
        assertNull(defaultCourse.getFlashcard());
    }

    // Test adding lessons to the course
    @Test
    public void testAddLesson_NewLesson() {
        int initialSize = course.getAllLessons().size();
        course.addLesson(lesson1);
        assertEquals(initialSize + 1, course.getAllLessons().size());
        assertTrue(course.getAllLessons().contains(lesson1));
    }

    @Test // bug -- git issue
    public void testAddLesson_DuplicateLesson() {
        course.addLesson(lesson1);
        int initialSize = course.getAllLessons().size();
        course.addLesson(lesson1); // Attempt to add duplicate
        assertEquals(initialSize, course.getAllLessons().size());
    }

    @Test // bug //git issue
    public void testAddLesson_NullLesson() {
        int initialSize = course.getAllLessons().size();
        course.addLesson(null);
        assertEquals(initialSize, course.getAllLessons().size());
    }

    @Test
    public void testAddLesson_MultipleLessons() {
        course.addLesson(lesson1);
        course.addLesson(lesson2);
        assertEquals(2, course.getAllLessons().size());
        assertTrue(course.getAllLessons().contains(lesson1));
        assertTrue(course.getAllLessons().contains(lesson2));
    }

    // Test calculateProgress
    @Test
    public void testCalculateProgress_LessonAndFlashcardIncomplete() {
        // Arrange
        course.setCurrentLesson(lesson1); // Assume lesson1 is set as the current lesson
        course.setCourseProgress(0.0); // Start with no progress on course
        course.calculateProgress(); // Both lesson and flashcard are incomplete
        
        // Act & Assert
        assertEquals("Course progress should be 0% when both lesson and flashcard are incomplete",
                0.0, course.getCourseProgress(), 0.01);
    }

    @Test
    public void testCalculateProgress_LessonComplete_FlashcardIncomplete() {
        // Arrange
        lesson1.markAsCompleted(); // Complete the lesson
        course.setCurrentLesson(lesson1);
        course.setCourseProgress(0.0);
        course.setCurrentLesson(lesson1); // Set current lesson to the completed lesson
        course.calculateProgress(); // Flashcard is still incomplete

        // Act & Assert
        assertEquals("Course progress should be 50% when lesson is complete but flashcard is incomplete",
                50.0, course.getCourseProgress(), 0.01);
    }

    @Test
    public void testCalculateProgress_LessonIncomplete_FlashcardComplete() {
        // Arrange
        flashcard.setFlashcardProgress(100.0); // Set flashcard progress to 100%
        course.setCurrentLesson(lesson1); // Current lesson is still incomplete
        course.setCourseProgress(0.0);
        course.calculateProgress();

        // Act & Assert
        assertEquals("Course progress should be 50% when flashcard is complete but lesson is incomplete",
                50.0, course.getCourseProgress(), 0.01);
    }

    @Test
    public void testCalculateProgress_LessonAndFlashcardComplete() {
        // Arrange
        lesson1.markAsCompleted(); // Complete the lesson
        flashcard.setFlashcardProgress(100.0); // Complete the flashcard
        course.setCurrentLesson(lesson1);
        course.setCourseProgress(0.0);
        course.calculateProgress(); // Both lesson and flashcard are complete

        // Act & Assert
        assertEquals("Course progress should be 100% when both lesson and flashcard are complete",
                100.0, course.getCourseProgress(), 0.01);
    }

    // Test setCourseProgress with invalid values
    @Test
    public void testSetCourseProgress_ValidValue() {
        course.setCourseProgress(75.0);
        assertEquals(75.0, course.getCourseProgress(), 0.01);
    }

    @Test
    public void testSetCourseProgress_NegativeValue() {
        course.setCourseProgress(-10.0);
        assertEquals(0.0, course.getCourseProgress(), 0.01);
    }

    //bug -- git issue
    @Test
    public void testSetCourseProgress_OverMaxValue() {
        course.setCourseProgress(120.0);
        assertEquals(100.0, course.getCourseProgress(), 0.01);
    }

    // Test setUserAccess
    @Test
    public void testSetUserAccess() {
        course.setUserAccess(false);
        assertFalse(course.getUserAccess());
        course.setUserAccess(true);
        assertTrue(course.getUserAccess());
    }

    // Test isCompletedCourse and setCompleted
    @Test
    public void testSetCompleted() {
        course.setCompleted(true);
        assertTrue(course.isCompletedCourse());
        assertEquals(100.0, course.getCourseProgress(), 0.01);
    }

    @Test
    public void testSetCompleted_False() {
        course.setCompleted(false);
        assertFalse(course.isCompletedCourse());
    }

    // Test addAssessment and getAllAssessments
    @Test
    public void testAddAssessment() {
        int initialSize = course.getAllAssessments().size();
        course.addAssessment(assessment1);
        assertEquals(initialSize + 1, course.getAllAssessments().size());
        assertTrue(course.getAllAssessments().contains(assessment1));
    }

    @Test // bug -- git issue
    public void testAddAssessment_Duplicate() {
        course.addAssessment(assessment1);
        int initialSize = course.getAllAssessments().size();
        course.addAssessment(assessment1); // Attempt to add duplicate
        assertEquals(initialSize, course.getAllAssessments().size());
    }

    @Test // bug //git issue
    public void testAddAssessment_Null() {
        int initialSize = course.getAllAssessments().size();
        course.addAssessment(null);
        assertEquals(initialSize, course.getAllAssessments().size());
    }

    // Test getCompletedAssessments
    @Test //bug //git issue
    public void testGetCompletedAssessments() {
        // Set up assessments with results
        assessment1.calculateScore(); // User score is 0
        assessment2.calculateScore(); // User score is 0

        // Set userScore manually for testing
        assessment1.userScore = 80; // Assume user passed
        assessment2.userScore = 60; // Assume user did not pass

        course.addAssessment(assessment1);
        course.addAssessment(assessment2);

        ArrayList<String> completedAssessments = course.getCompletedAssessments();
        assertEquals(1, completedAssessments.size());
        assertTrue(completedAssessments.contains(assessment1.toString()));
        assertFalse(completedAssessments.contains(assessment2.toString()));
    }

    // Test addKeyWord and getKeyWords
    @Test
    public void testAddKeyWord() {
        course.addKeyWord("Beginner");
        course.addKeyWord("Spanish");
        assertEquals(2, course.getKeyWords().size());
        assertTrue(course.getKeyWords().contains("Beginner"));
        assertTrue(course.getKeyWords().contains("Spanish"));
    }

    @Test // bug -- git issue
    public void testAddKeyWord_Duplicate() {
        course.addKeyWord("Spanish");
        course.addKeyWord("Spanish"); // Attempt to add duplicate
        assertEquals(1, course.getKeyWords().size());
    }

    @Test // bug -- git issue
    public void testAddKeyWord_Null() {
        int initialSize = course.getKeyWords().size();
        course.addKeyWord(null);
        assertEquals(initialSize, course.getKeyWords().size());
    }

    // Test setCompletedCourse and completedCourse
    @Test
    public void testSetCompletedCourse() {
        course.setCompletedCourse();
        assertTrue(course.isCompletedCourse());
        assertEquals(100.0, course.getCourseProgress(), 0.01);
    }

    @Test//bug //git issue
    public void testCompletedCourse() {
        course.setCourseProgress(100.0);
        assertTrue(course.isCompletedCourse());
    }

    @Test
    public void testCompletedCourse_False() {
        course.setCourseProgress(50.0);
        assertFalse(course.isCompletedCourse());
    }

    @Test
    public void testConstructorWithProgress() {
        double progress = 50.0;
        Course courseWithProgress = new Course(courseId, "Test Course", "A course for testing", true, progress, false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        assertEquals(progress, courseWithProgress.getCourseProgress(), 0.01);
    }

    @Test
    public void testConstructorWithoutProgress() {
        Course courseWithoutProgress = new Course(courseId, "Test Course", "A course for testing", true, 0.0, false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        assertEquals(0.0, courseWithoutProgress.getCourseProgress(), 0.01);
    }
}
