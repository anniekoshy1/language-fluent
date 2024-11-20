package com.model;


public class TrueFalseQuestion extends Questions {

    public Word usedWord;
    private Word correctAnswer;  // Correct answer (true/false)
    private String userAnswer;   // User's answer

    /**
     * Constructs a True/False question.
     * 
     * @param language The language in which the question should be presented, defaults to "Spanish" if null.
     * @param correctAnswer The correct answer (true/false) for the question.
     */
    public TrueFalseQuestion(String language, Word correctAnswer, Word usedWord) {
        super(language);
        this.correctAnswer = correctAnswer;
        this.usedWord = usedWord;
    }

    /**
     * Checks if the user's answer is correct.
     * 
     * @param user The user whose answer is to be validated.
     * @return true if the user's answer is correct, false otherwise.
     */
    @Override
    public boolean isCorrect() {
        if (userAnswer != null) {

            if (userAnswer.equalsIgnoreCase("false")) {
                return usedWord.getTranslation().equals(correctAnswer.getWordText());
            } 
            else if (userAnswer.equalsIgnoreCase("true")) {
                return !usedWord.getTranslation().equals(correctAnswer.getWordText());
            }
        }
        return false;
    }

    /**
     * Sets the user's answer to this question.
     * 
     * @param userAnswer The answer provided by the user ("true" or "false").
     */
    @Override
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    /**
     * Gets a string representation of the question for display.
     * 
     * @return A string representing the True/False question.
     */
    @Override
    public String toString() {
        return "Does this word " + usedWord.getWordText() + " Mean this " + correctAnswer.getTranslation() + " true or false "; // Adjust the question text as needed
    }
}
