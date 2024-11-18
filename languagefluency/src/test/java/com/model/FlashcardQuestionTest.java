package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the FlashcardQuestion class.
 */
public class FlashcardQuestionTest {

    private FlashcardQuestion flashcard;

    @Before
    public void setUp() {
        // Initialize a FlashcardQuestion before each test
        flashcard = new FlashcardQuestion("What is the capital of France?", "Paris");
    }

    // Test submitAnswer with valid input
    @Test
    public void testSubmitAnswer_ValidInput() {
        flashcard.submitAnswer("Paris");
        assertEquals("Paris", flashcard.getUserAnswer());
    }

    // Test submitAnswer with null input
    @Test(expected = IllegalArgumentException.class)
    public void testSubmitAnswer_NullInput() {
        flashcard.submitAnswer(null);
    }

    // Test submitAnswer with empty input
    @Test(expected = IllegalArgumentException.class)
    public void testSubmitAnswer_EmptyInput() {
        flashcard.submitAnswer("");
    }

    // Test submitAnswer with whitespace input
    @Test(expected = IllegalArgumentException.class)
    public void testSubmitAnswer_WhitespaceInput() {
        flashcard.submitAnswer("   ");
    }

    // Test checkAnswer with correct answer
    @Test
    public void testCheckAnswer_Correct() {
        flashcard.submitAnswer("Paris");
        assertTrue(flashcard.checkAnswer());
    }

    // Test checkAnswer with incorrect answer
    @Test
    public void testCheckAnswer_Incorrect() {
        flashcard.submitAnswer("Berlin");
        assertFalse(flashcard.checkAnswer());
    }

    // Test checkAnswer with case-insensitive correct answer
    @Test
    public void testCheckAnswer_CaseInsensitive() {
        flashcard.submitAnswer("paris");
        assertTrue(flashcard.checkAnswer());
    }

    // Test markAsCompleted with "done" input
    @Test
    public void testMarkAsCompleted_DoneInput() {
        flashcard.markAsCompleted("done");
        assertTrue(flashcard.isCompleted());
        assertEquals(100.0, flashcard.getFlashcardProgress(), 0.01);
    }

    // Test markAsCompleted with "Done" input (case-insensitive)
    @Test
    public void testMarkAsCompleted_DoneInput_CaseInsensitive() {
        flashcard.markAsCompleted("Done");
        assertTrue(flashcard.isCompleted());
        assertEquals(100.0, flashcard.getFlashcardProgress(), 0.01);
    }

    // Test markAsCompleted with input other than "done"
    @Test
    public void testMarkAsCompleted_OtherInput() {
        flashcard.markAsCompleted("finish");
        assertFalse(flashcard.isCompleted());
        assertEquals(0.0, flashcard.getFlashcardProgress(), 0.01);
    }

    // Test isCompleted before marking as completed
    @Test
    public void testIsCompleted_BeforeMarking() {
        assertFalse(flashcard.isCompleted());
    }

    // Test setFlashcardProgress with valid value
    @Test
    public void testSetFlashcardProgress_ValidValue() {
        flashcard.setFlashcardProgress(50.0);
        assertEquals(50.0, flashcard.getFlashcardProgress(), 0.01);
    }

    // Test setFlashcardProgress with value 0.0
    @Test
    public void testSetFlashcardProgress_ZeroValue() {
        flashcard.setFlashcardProgress(0.0);
        assertEquals(0.0, flashcard.getFlashcardProgress(), 0.01);
    }

    // Test setFlashcardProgress with value 100.0
    @Test
    public void testSetFlashcardProgress_MaxValue() {
        flashcard.setFlashcardProgress(100.0);
        assertEquals(100.0, flashcard.getFlashcardProgress(), 0.01);
    }

    // Test setFlashcardProgress with negative value
    @Test(expected = IllegalArgumentException.class)
    public void testSetFlashcardProgress_NegativeValue() {
        flashcard.setFlashcardProgress(-10.0);
    }

    // Test setFlashcardProgress with value greater than 100.0
    @Test(expected = IllegalArgumentException.class)
    public void testSetFlashcardProgress_OverMaxValue() {
        flashcard.setFlashcardProgress(110.0);
    }

    // Test showDefinition
    @Test
    public void testShowDefinition() {
        String definition = flashcard.showDefinition();
        assertEquals("Paris", definition);
    }

    // Test showCorrectAnswer
    @Test
    public void testShowCorrectAnswer() {
        String correctAnswer = flashcard.showCorrectAnswer();
        assertEquals("Paris", correctAnswer);
    }

    // Test getFrontInfo
    @Test
    public void testGetFrontInfo() {
        assertEquals("What is the capital of France?", flashcard.getFrontInfo());
    }

    // Test getBackAnswer
    @Test
    public void testGetBackAnswer() {
        assertEquals("Paris", flashcard.getBackAnswer());
    }

    // Test getUserAnswer before submitting answer
    @Test
    public void testGetUserAnswer_BeforeSubmit() {
        assertEquals("", flashcard.getUserAnswer());
    }

    // Test getUserAnswer after submitting answer
    @Test
    public void testGetUserAnswer_AfterSubmit() {
        flashcard.submitAnswer("Paris");
        assertEquals("Paris", flashcard.getUserAnswer());
    }
}
