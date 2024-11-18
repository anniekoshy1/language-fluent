package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TrueFalseQuestionTest {

    private TrueFalseQuestion question;

    @Before
    public void setUp() {
        question = new TrueFalseQuestion("Is the sky blue?", true, Difficulty.RUDIMENTARY);
    }

    @Test
    public void testConstructor() {
        assertEquals("Question text should match", "Is the sky blue?", question.getQuestionText());
        assertTrue("Correct answer should be true", question.getCorrectAnswer());
        assertEquals("Initial user answer should be false", "False", question.getUserAnswer());
    }

    @Test
    public void testSubmitAnswerTrue() {
        question.submitAnswer(true);
        assertEquals("User answer should be 'True'", "True", question.getUserAnswer());
        assertTrue("Answer should be correct", question.checkAnswers());
    }

    @Test
    public void testSubmitAnswerFalse() {
        question.submitAnswer(false);
        assertEquals("User answer should be 'False'", "False", question.getUserAnswer());
        assertFalse("Answer should be incorrect", question.checkAnswers());
    }

    @Test
    public void testCheckAnswersCorrect() {
        question.submitAnswer(true);
        assertTrue("checkAnswers should return true for correct answer", question.checkAnswers());
    }

    @Test
    public void testCheckAnswersIncorrect() {
        question.submitAnswer(false);
        assertFalse("checkAnswers should return false for incorrect answer", question.checkAnswers());
    }

    @Test
    public void testResetAnswer() {
        question.submitAnswer(true);
        question.resetAnswer();
        assertEquals("User answer should be reset to 'False'", "False", question.getUserAnswer());
        assertFalse("checkAnswers should return false after reset", question.checkAnswers());
    }

    @Test
    public void testToString() {
        String expected = "True/False Question: Is the sky blue?\nCorrect Answer: True";
        assertEquals("toString output should match expected", expected, question.toString());
    }
}
