package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of Word objects, providing methods to add, filter, and access words by different criteria.
 */
public class WordsList {
    private final List<Word> words;
    private static WordsList wordsList;
    private boolean isLoaded = false;

    /**
     * Private constructor initializes an empty words list.
     */
    private WordsList() {
        words = new ArrayList<>();
    }
    
    /**
     * Returns the singleton instance of WordsList and loads words if not yet loaded.
     *
     * @return the singleton instance of WordsList
     */
    public static WordsList getInstance() {
        if (wordsList == null) {
            wordsList = new WordsList();
        }
        return wordsList;
    }

        /**
     * Loads words data if it has not been loaded already.
     */
    public void loadWords() {
        DataLoader.loadWords(); // Populates this WordsList instance after instantiation
    }

    /**
     * Adds a word to the list.
     *
     * @param word The word to be added.
     */
    public void addWord(Word word) {
        this.words.add(word);
    }

    /**
     * Retrieves a random word from the list.
     *
     * @return A randomly selected word, or null if the list is empty.
     */
    public Word getRandomWord() {
        if (words.isEmpty()) return null;
        int randomIndex = (int) (Math.random() * words.size());
        return words.get(randomIndex);
    }

    /**
     * Provides access to the full list of words.
     *
     * @return The complete list of words.
     */
    public List<Word> getAllWords() {
        return words;
    }

    /**
     * Saves the list of words to the JSON file.
     */
    public void saveWords() {
        DataWriter.saveWords();
    }
}
