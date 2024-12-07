package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MultipleChoiceQuestion extends Questions {

    private int correctAnswersIndex;  // Index of the correct answer in the choices list
    private Word correctAnswer;       // The correct answer as a Word
    private ArrayList<Word> choices;  // List of choices (4 choices)
    private String userAnswer;        // The user's selected answer

    /**x
     * Constructs a MultipleChoiceQuestion.
     * 
     * @param language             The language of the question.
     * @param correctAnswer       The correct answer (Word object).
     * @param wordsList           The WordsList containing all available words.
     */
    public MultipleChoiceQuestion(String language, Word correctAnswer, WordsList wordsList) {
        super(language);  // Call the parent constructor to set the language
        this.correctAnswer = correctAnswer;
        this.choices = generateChoices(correctAnswer, wordsList.getAllWords());  // Generate 4 choices including the correct one
        this.correctAnswersIndex = choices.indexOf(correctAnswer);  // Find the correct answer index
    }

    /**
     * Generates 4 random choices including the correct answer.
     * 
     * @param correctAnswer The correct answer to ensure it is part of the choices.
     * @param allWords      The full list of available words (50 words).
     * @return A list of 4 choices including the correct answer.
     */
    private ArrayList<Word> generateChoices(Word correctAnswer, List<Word> allWords) {
        ArrayList<Word> selectedChoices = new ArrayList<>();
        List<Word> availableChoices = new ArrayList<>(allWords);

        // Ensure the correct answer is in the list of available choices
        availableChoices.remove(correctAnswer);

        // Add the correct answer to the choices list
        selectedChoices.add(correctAnswer);

        // Randomly select 3 other words (excluding the correct answer)
        Random random = new Random();
        while (selectedChoices.size() < 4) {
            int randomIndex = random.nextInt(availableChoices.size());
            selectedChoices.add(availableChoices.remove(randomIndex));
        }
        // Shuffle the selected choices so the correct answer is not always at the same position
        Collections.shuffle(selectedChoices);
        return selectedChoices;
    }

    /**
     * Sets the user's answer to this question (index of selected answer).
     * 
     * @param userAnswer The answer provided by the user (index as String).
     */
    @Override
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    /**
     * Checks if the user's answer is correct.
     * 
     * @return true if the user's answer is correct, false otherwise.
     */
    @Override
    public boolean isCorrect() {
        // Check if the userAnswer is a valid integer
        if (userAnswer != null && !userAnswer.isEmpty()) {
            // Check if the userAnswer is a valid number
            if (userAnswer.matches("\\d+")) {  // Regex to check if the input contains only digits
                int userChoiceIndex = Integer.parseInt(userAnswer) - 1;  // Convert the user answer to 0-based index
                return userChoiceIndex == correctAnswersIndex;  // Check if the user's choice matches the correct index
            }
        }
        return false;  // Return false if the answer is invalid
    }

    /**
     * Gets a string representation of the question with choices for display.
     * 
     * @return A string representing the multiple-choice question.
     */
    @Override
    public String toString() {
        StringBuilder questionText = new StringBuilder();
    
        // Ask for the Spanish translation of the English word
        String englishWord = correctAnswer.getTranslation(); // English word (translation of the correct answer)
        questionText.append("What's the Spanish translation for (" + englishWord + ")?\n");
    
        // Display the choices with their Spanish translation
        for (int i = 0; i < choices.size(); i++) {
            questionText.append((i + 1) + ". " + choices.get(i).getWordText() + "\n");
        }
    
        return questionText.toString();
    }
}
