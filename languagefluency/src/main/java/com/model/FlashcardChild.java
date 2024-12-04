package com.model;

public class FlashcardChild extends Questions{

    public Word spanishWord;
    public Word englishWord;  // Correct answer (true/false)
    private String userAnswer; 


public FlashcardChild(String language, Word spanishWord, Word englishWord){
    super(language);
    this.spanishWord = spanishWord;
    this.englishWord = englishWord;
}

@Override
public boolean isCorrect() {
if(userAnswer.equalsIgnoreCase(englishWord.getTranslation())){
    return true;
}
return false;
}

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
    return "Whats the English translation of " + spanishWord.getWordText();
}

}