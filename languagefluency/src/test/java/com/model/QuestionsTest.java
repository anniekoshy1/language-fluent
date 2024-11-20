package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class QuestionsTest {

    private Questions trueFalseQuestion;
    private Questions multipleChoiceQuestion;
    private Questions openEndedQuestion;
    private List<String> mcOptions;

    @Before
    public void setUp() {
        mcOptions = Arrays.asList("Option A", "Option B", "Option C");
        trueFalseQuestion = new Questions("Is the sky blue?", true, Difficulty.RUDIMENTARY);
        multipleChoiceQuestion = new Questions("What is the capital of France?", mcOptions, "Option B", Difficulty.INTERMEDIATE);
        openEndedQuestion = new Questions("What is 2 + 2?", "4", Difficulty.RUDIMENTARY);
    }

    @Test
    public void testConstructor_TrueFalseQuestion() {
        assertEquals("Question text should match", "Is the sky blue?", trueFalseQuestion.getQuestionText());
        assertEquals("Difficulty should match", Difficulty.RUDIMENTARY, trueFalseQuestion.getDifficulty());
        assertTrue("Correct answer should match", trueFalseQuestion.getCorrectAnswer());
    }

    //bug //git issue
    @Test
    public void testConstructor_MultipleChoiceQuestion() {
        assertEquals("Question text should match", "What is the capital of France?", multipleChoiceQuestion.getQuestionText());
        assertEquals("Difficulty should match", Difficulty.INTERMEDIATE, multipleChoiceQuestion.getDifficulty());
        assertEquals("Options should match", mcOptions, multipleChoiceQuestion.getOptions());
        assertEquals("Correct option should match", "Option B", multipleChoiceQuestion.getCorrectAnswer());
    }

    @Test
    public void testConstructor_OpenEndedQuestion() {
        assertEquals("Question text should match", "What is 2 + 2?", openEndedQuestion.getQuestionText());
        assertEquals("Difficulty should match", Difficulty.RUDIMENTARY, openEndedQuestion.getDifficulty());
    }

    @Test
    public void testSubmitAnswer_TrueFalseQuestion() {
        trueFalseQuestion.submitAnswer("true");
        assertEquals("User answer should match", "true", trueFalseQuestion.getUserAnswer());
    }

    @Test
    public void testSubmitAnswer_MultipleChoiceQuestion() {
        multipleChoiceQuestion.submitAnswer("Option B");
        assertEquals("User answer should match", "Option B", multipleChoiceQuestion.getUserAnswer());
    }

    @Test
    public void testSubmitAnswer_OpenEndedQuestion() {
        openEndedQuestion.submitAnswer("4");
        assertEquals("User answer should match", "4", openEndedQuestion.getUserAnswer());
    }

    @Test
    public void testCheckAnswers_TrueFalseCorrectAnswer() {
        trueFalseQuestion.submitAnswer("true");
        assertTrue("Answer should be correct", trueFalseQuestion.checkAnswers());
    }

    @Test
    public void testCheckAnswers_TrueFalseIncorrectAnswer() {
        trueFalseQuestion.submitAnswer("false");
        assertFalse("Answer should be incorrect", trueFalseQuestion.checkAnswers());
    }

    @Test
    public void testCheckAnswers_MultipleChoiceCorrectAnswer() {
        multipleChoiceQuestion.submitAnswer("Option B");
        assertTrue("Answer should be correct", multipleChoiceQuestion.checkAnswers());
    }

    @Test
    public void testCheckAnswers_MultipleChoiceIncorrectAnswer() {
        multipleChoiceQuestion.submitAnswer("Option A");
        assertFalse("Answer should be incorrect", multipleChoiceQuestion.checkAnswers());
    }

    @Test
    public void testCheckAnswers_OpenEndedCorrectAnswer() {
        openEndedQuestion.submitAnswer("4");
        assertTrue("Answer should be correct", openEndedQuestion.checkAnswers());
    }

    @Test
    public void testCheckAnswers_OpenEndedIncorrectAnswer() {
        openEndedQuestion.submitAnswer("five");
        assertFalse("Answer should be incorrect", openEndedQuestion.checkAnswers());
    }

    @Test
    public void testResetAnswer() {
        openEndedQuestion.submitAnswer("4");
        openEndedQuestion.resetAnswer();
        assertEquals("User answer should be reset to empty", "", openEndedQuestion.getUserAnswer());
    }

    @Test
    public void testSetAndGetQuestionText() {
        String newText = "What is the color of grass?";
        trueFalseQuestion.setQuestionText(newText);
        assertEquals("Question text should be updated", newText, trueFalseQuestion.getQuestionText());
    }

    @Test
    public void testSetAndGetDifficulty() {
        trueFalseQuestion.setDifficulty(Difficulty.ADVANCED);
        assertEquals("Difficulty should be updated", Difficulty.ADVANCED, trueFalseQuestion.getDifficulty());
    }

    @Test
    public void testSetAndGetCorrectAnswer() {
        trueFalseQuestion.setCorrectAnswer(false);
        assertFalse("Correct answer should be updated", trueFalseQuestion.getCorrectAnswer());
    }

    //bug  -- git issue
    @Test
    public void testToString() {
        String expectedString = "Question: " + openEndedQuestion.getQuestionText() +
            "\nOptions: null\nDifficulty: EASY\nCorrect Answer: false";
        assertEquals("String representation should match expected format", expectedString, openEndedQuestion.toString());
    }
}
