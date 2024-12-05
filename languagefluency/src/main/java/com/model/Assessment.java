package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Assessment {

    private final List<Questions> questions;

    public Assessment() {
        this.questions = new ArrayList<>();
    }

    /**
     * Generates a random question of either True/False or Multiple Choice.
     * @param wordsList The list of available words.
     * @return A random question.
     */
    public Questions generateRandomQuestion(WordsList wordsList) {
        // Get the list of all words from WordsList
        List<Word> allWords = wordsList.getAllWords();
    
        // Randomly choose a question type: True/False (0) or Multiple Choice (1)
        Questions question;
        switch (new Random().nextInt(2)) {
            case 0:
                // Create True/False question
                Word randomWordTrueFalse = allWords.get(new Random().nextInt(allWords.size()));
                question = new TrueFalseQuestion("Spanish", randomWordTrueFalse, randomWordTrueFalse);
                break;
            case 1:
                // Create Multiple Choice question
                Word correctAnswer = allWords.get(new Random().nextInt(allWords.size()));
                question = new MultipleChoiceQuestion("Spanish", correctAnswer, wordsList);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + new Random().nextInt(2));
        }
    
        return question;
    }

        public FlashcardChild generateFlashcardQuestion(WordsList wordsList) {
        // Get the list of all words from WordsList
        List<Word> allWords = wordsList.getAllWords();

        // Randomly select a Spanish word and its corresponding English word
        Word spanishWord = allWords.get(new Random().nextInt(allWords.size()));
        Word englishWord = allWords.get(new Random().nextInt(allWords.size()));

        // Create and return a FlashcardChild question
        return new FlashcardChild("Spanish", spanishWord, englishWord);
    }

        /**
     * Add a question to the list of answered questions.
     */
    public void addQuestion(Questions question) {
        questions.add(question);
    }

    /**
     * Evaluates the performance of the user based on their answers.
     */
    public void evaluatePerformance() {
        int score = 0;

        for (Questions question : questions) {
            if (question.isCorrect()) {
                score++;
            }
        }

        // Calculate the final score as a percentage
        int finalScore = (score * 100) / questions.size();  // Using multiplication to avoid integer division
        System.out.println("Assessment completed. Your score: " + finalScore + "%");
    }
}
