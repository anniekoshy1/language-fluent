package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Language class.
 */
public class LanguageTest {

    private Language language;
    private User testUser;
    private Course course1;
    private Course course2;
    private Assessment assessment1;
    private Assessment assessment2;

    @Before
    public void setUp() {
        testUser = new User(UUID.randomUUID(), "testUser", "test@example.com", "password");
        language = new Language(testUser, "Spanish");
        
        course1 = new Course(UUID.randomUUID(), "Spanish 101", "Introductory Spanish course", true, 0.0, false, 
                            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        
        course2 = new Course(UUID.randomUUID(), "Spanish 102", "Intermediate Spanish course", true, 0.0, false, 
                            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
                        
        assessment1 = new Assessment(UUID.randomUUID(), Assessment.AssessmentType.MULTIPLE_CHOICE, new ArrayList<>());
        assessment2 = new Assessment(UUID.randomUUID(), Assessment.AssessmentType.TRUE_FALSE, new ArrayList<>());
    }

    // Test constructor with user and language name
    @Test
    public void testConstructorWithUserAndName() {
        assertNotNull(language.getId());
        assertEquals("Spanish", language.getName());
        assertEquals(testUser, language.getUser());
    }

    // Test setUser method
    @Test
    public void testSetUser() {
        User newUser = new User(UUID.randomUUID(), "newUser", "newuser@example.com", "newpassword");
        language.setUser(newUser);
        assertEquals(newUser, language.getUser());
    }

    // Test setCourseAccess with valid course
    @Test
    public void testSetCourseAccess() {
        language.setCourseAccess(course1, true);
        assertTrue(language.getCourseAccess().containsKey(course1));
        assertTrue(language.getCourseAccess().get(course1));
    }

    // Test addKeyWord method
    @Test
    public void testAddKeyWord() {
        language.addKeyWord("beginner");
        assertTrue(language.getKeyWords().contains("beginner"));
    }

    // Test setLanguageProgress updates languageProgress and totalPercentage
    @Test
    public void testSetLanguageProgress() {
        language.setLanguageProgress(50.0);
        assertEquals(50.0, language.getLanguageProgress(), 0.01);
        assertEquals(25.0, language.getTotalPercentage(), 0.01);  // totalPercentage should reflect average
    }

    // Test setCompletedCourses with multiple courses
    @Test //bug -- git issue
    public void testSetCompletedCourses() {
        ArrayList<Course> completedCourses = new ArrayList<>();
        completedCourses.add(course1);
        completedCourses.add(course2);

        language.setCompletedCourses(completedCourses);
        assertEquals(100.0, language.getCoursePercentage(), 0.01);
        assertEquals(100.0, language.getTotalPercentage(), 0.01);
    }

    // Test takenStarterTest method
    @Test
    public void testTakenStarterTest_NoStarterTest() {
        assertFalse(language.takenStarterTest());
    }

    // Test addCompletedAssessment adds assessment to completedAssessments
    @Test
    public void testAddCompletedAssessment() {
        language.addCompletedAssessment(assessment1);
        assertTrue(language.getCompletedAssessments().contains(assessment1));
    }

    // Test getCompletedCourses with non-empty list
    @Test
    public void testGetCompletedCourses_NonEmpty() {
        language.setCompletedCourses(new ArrayList<>(List.of(course1, course2)));
        assertEquals(2, language.getCompletedCourses().size());
    }

    // Test getKeyWords with multiple keywords
    @Test
    public void testGetKeyWords_MultipleKeywords() {
        language.addKeyWord("basic");
        language.addKeyWord("intermediate");
        assertTrue(language.getKeyWords().contains("basic"));
        assertTrue(language.getKeyWords().contains("intermediate"));
    }

    // Test getCompletedAssessments with multiple assessments
    @Test
    public void testGetCompletedAssessments_MultipleAssessments() {
        language.addCompletedAssessment(assessment1);
        language.addCompletedAssessment(assessment2);
        assertEquals(2, language.getCompletedAssessments().size());
    }
}
