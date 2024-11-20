package com.model;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class MultipleChoiceQuestionTest {

    private MultipleChoiceQuestion question;

    @Before
    public void setUp() {
        List<String> choices = Arrays.asList("Choice A", "Choice B", "Choice C", "Choice D");
        question = new MultipleChoiceQuestion("What is the capital of France?", choices, "Choice B");
    }

    @Test
    public void testConstructor() {
        assertEquals("What is the capital of France?", question.getQuestion());
        List<String> expectedChoices = Arrays.asList("Choice A", "Choice B", "Choice C", "Choice D");
        assertEquals(expectedChoices, question.getChoices());
        assertEquals("Choice B", question.getCorrectAnswer());
        assertEquals("", question.getUserAnswer());
    }

    @Test
    public void testSubmitAnswer_ValidAnswer() {
        question.submitAnswer("Choice B");
        assertEquals("Choice B", question.getUserAnswer());
    }

    @Test
    public void testSubmitAnswer_InvalidAnswer() {
        question.submitAnswer("Choice X");
        assertEquals("Choice X", question.getUserAnswer());
    }

    @Test
    public void testCheckAnswer_CorrectAnswer() {
        question.submitAnswer("Choice B");
        assertTrue("The answer should be marked as correct", question.checkAnswer());
    }

    @Test
    public void testCheckAnswer_IncorrectAnswer() {
        question.submitAnswer("Choice A");
        assertFalse("The answer should be marked as incorrect", question.checkAnswer());
    }

    @Test
    public void testCheckAnswer_CaseInsensitiveMatch() {
        question.submitAnswer("choice b");
        assertTrue("The answer should be marked as correct, ignoring case", question.checkAnswer());
    }

    @Test
    public void testReset() {
        question.submitAnswer("Choice A");
        question.reset();
        assertEquals("User answer should be reset to an empty string", "", question.getUserAnswer());
    }

    @Test
    public void testGetCorrectAnswer() {
        assertEquals("Choice B", question.getCorrectAnswer());
    }
}
