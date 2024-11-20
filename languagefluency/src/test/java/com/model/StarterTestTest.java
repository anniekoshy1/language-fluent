package com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StarterTestTest {

    private StarterTest starterTest;
    private List<Questions> questions;

    @Before
    public void setUp() {
        Questions question1 = new Questions("Is the sky blue?", true, Difficulty.RUDIMENTARY);
        Questions question2 = new Questions("What is 2 + 2?", "4", Difficulty.RUDIMENTARY);
        Questions question3 = new Questions("What is the capital of France?", Arrays.asList("Paris", "Berlin", "London"), "Paris", Difficulty.INTERMEDIATE);
        questions = new ArrayList<>(Arrays.asList(question1, question2, question3));
        starterTest = new StarterTest(questions);
    }

    @Test
    public void testConstructor() {
        assertEquals("Total questions should match", 3, starterTest.getTotalQuestions());
        assertEquals("Question list should match", questions, starterTest.getQuestions());
        assertEquals("Initial user score should be zero", 0, starterTest.getUserScore());
    }

    @Test
    public void testAddQuestion() {
        Questions newQuestion = new Questions("Is water wet?", true, Difficulty.RUDIMENTARY);
        starterTest.addQuestion(newQuestion);
        assertEquals("Total questions should be updated", 4, starterTest.getTotalQuestions());
        assertTrue("New question should be added", starterTest.getQuestions().contains(newQuestion));
    }

    @Test
    public void testRemoveQuestion() {
        Questions questionToRemove = questions.get(0);
        starterTest.removeQuestion(questionToRemove);
        assertEquals("Total questions should be updated", 2, starterTest.getTotalQuestions());
        assertTrue("Question should be removed", !starterTest.getQuestions().contains(questionToRemove));
    }

    @Test
    public void testSubmitTest_AllCorrect() {
        // Submit all correct answers
        questions.get(0).submitAnswer("true");  // Correct
        questions.get(1).submitAnswer("4");     // Correct
        questions.get(2).submitAnswer("Paris"); // Correct
        
        starterTest.submitTest();
        assertEquals("User score should be 3", 3, starterTest.getUserScore());
    }

    @Test
    public void testSubmitTest_PartialCorrect() {
        questions.get(0).submitAnswer("true");  // Correct
        questions.get(1).submitAnswer("5");     // Incorrect
        questions.get(2).submitAnswer("Berlin"); // Incorrect

        starterTest.submitTest();
        assertEquals("User score should be 1", 1, starterTest.getUserScore());
    }

    @Test
    public void testGetScorePercentage_AllCorrect() {
        questions.get(0).submitAnswer("true");
        questions.get(1).submitAnswer("4");
        questions.get(2).submitAnswer("Paris");
        
        starterTest.submitTest();
        assertEquals("Score percentage should be 100", 100.0, starterTest.getScorePercentage(), 0.01);
    }

    @Test
    public void testGetScorePercentage_NoCorrect() {
        questions.get(0).submitAnswer("false");
        questions.get(1).submitAnswer("5");
        questions.get(2).submitAnswer("Berlin");

        starterTest.submitTest();
        assertEquals("Score percentage should be 0", 0.0, starterTest.getScorePercentage(), 0.01);
    }

    @Test
    public void testGetScorePercentage_PartialCorrect() {
        questions.get(0).submitAnswer("true");
        questions.get(1).submitAnswer("4");
        questions.get(2).submitAnswer("Berlin");

        starterTest.submitTest();
        assertEquals("Score percentage should be 66.67", 66.67, starterTest.getScorePercentage(), 0.01);
    }

    @Test
    public void testResetTest() {
        questions.get(0).submitAnswer("true");
        questions.get(1).submitAnswer("4");
        questions.get(2).submitAnswer("Paris");

        starterTest.submitTest();
        starterTest.resetTest();
        
        assertEquals("User score should be reset to 0", 0, starterTest.getUserScore());
        for (Questions question : questions) {
            assertEquals("Each question's answer should be reset", "", question.getUserAnswer());
        }
    }

    @Test
    public void testDetermineLevel_Advanced() {
        questions.get(0).submitAnswer("true");
        questions.get(1).submitAnswer("4");
        questions.get(2).submitAnswer("Paris");

        starterTest.submitTest();
        assertEquals("Difficulty level should be ADVANCED", Difficulty.ADVANCED, starterTest.determineLevel());
    }

    @Test
    public void testDetermineLevel_Intermediate() {
        questions.get(0).submitAnswer("true");
        questions.get(1).submitAnswer("4");
        questions.get(2).submitAnswer("Berlin"); // Incorrect

        starterTest.submitTest();
        assertEquals("Difficulty level should be INTERMEDIATE", Difficulty.INTERMEDIATE, starterTest.determineLevel());
    }

    @Test
    public void testDetermineLevel_Rudimentary() {
        questions.get(0).submitAnswer("false"); // Incorrect
        questions.get(1).submitAnswer("5");     // Incorrect
        questions.get(2).submitAnswer("London"); // Incorrect

        starterTest.submitTest();
        assertEquals("Difficulty level should be RUDIMENTARY", Difficulty.RUDIMENTARY, starterTest.determineLevel());
    }
}
