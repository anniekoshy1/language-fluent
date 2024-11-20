package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class LessonTest {

    private Lesson lesson;
    private UUID lessonId;
    
    @Before
    public void setUp() {
        lessonId = UUID.randomUUID();
        lesson = new Lesson("Introduction", lessonId, "Basic Spanish phrases", 50.0, "Hello", "Hola");
    }

    @Test
    public void testConstructor_WithAllFields() {
        assertEquals("Introduction", lesson.getLessonName());
        assertEquals(lessonId, lesson.getId());
        assertEquals("Basic Spanish phrases", lesson.getDescription());
        assertEquals(50.0, lesson.getLessonProgress(), 0.01);
        assertEquals("Hello", lesson.getEnglishContent());
        assertEquals("Hola", lesson.getSpanishContent());
        assertFalse("Lesson should not be marked as completed initially", lesson.isCompleted());
    }

    @Test
    public void testConstructor_WithPartialFields() {
        Lesson partialLesson = new Lesson("Greetings", "Learn greetings", "Hi", "Hola");
        assertEquals("Greetings", partialLesson.getLessonName());
        assertEquals("Learn greetings", partialLesson.getDescription());
        assertEquals(0.0, partialLesson.getLessonProgress(), 0.01);
        assertFalse("Lesson should not be marked as completed initially", partialLesson.isCompleted());
    }

    @Test
    public void testSetDescription() {
        lesson.setDescription("Advanced Spanish phrases");
        assertEquals("Advanced Spanish phrases", lesson.getDescription());
    }

    @Test
    public void testSetLessonProgress_ProgressBelow100() {
        lesson.setLessonProgress(70.0);
        assertEquals(70.0, lesson.getLessonProgress(), 0.01);
        assertFalse("Lesson should not be completed when progress is below 100%", lesson.isCompleted());
    }

    @Test
    public void testSetLessonProgress_ProgressAt100() {
        lesson.setLessonProgress(100.0);
        assertEquals(100.0, lesson.getLessonProgress(), 0.01);
        assertTrue("Lesson should be marked as completed when progress reaches 100%", lesson.isCompleted());
    }

    @Test
    public void testSetLessonProgress_InvalidProgress() {
        lesson.setLessonProgress(-20.0);
        assertEquals(-20.0, lesson.getLessonProgress(), 0.01);
        assertFalse("Lesson should not be completed with invalid progress", lesson.isCompleted());
    }

    @Test
    public void testSetEnglishContent() {
        lesson.setEnglishContent("Goodbye");
        assertEquals("Goodbye", lesson.getEnglishContent());
    }

    @Test
    public void testSetSpanishContent() {
        lesson.setSpanishContent("Adiós");
        assertEquals("Adiós", lesson.getSpanishContent());
    }

    @Test
    public void testMarkAsCompleted() {
        lesson.markAsCompleted();
        assertTrue("Lesson should be marked as completed", lesson.isCompleted());
        assertEquals(100.0, lesson.getLessonProgress(), 0.01);
    }
}
