package com.model;

import java.util.ArrayList;
import java.util.List;

public class WordsList {
    private final ArrayList<Word> words; // List of words in the system
    private static WordsList wordsList;

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
            wordsList.loadWords();
        }
        return wordsList;
    }

    /**
     * Loads words data using DataLoader if not loaded already.
     */
    public void loadWords() {
        DataLoader.loadWords();
    }

    /**
     * Adds a word to the list.
     *
     * @param word The word to be added.
     */
    public void addWord(Word word) {
        words.add(word);
        saveWords(); // Save after adding to maintain persistence
    }

    /**
     * Adds multiple words to the list without triggering save.
     *
     * @param newWords The words to be added.
     */
    public void addWords(List<Word> newWords) {
        words.addAll(newWords);
    }

    /**
     * Retrieves all the words in the list.
     *
     * @return The complete list of words.
     */
    public ArrayList<Word> getAllWords() {
        return words;
    }

    /**
     * Saves the list of words to the JSON file using DataWriter.
     */
    public void saveWords() {
        DataWriter.saveWords();
    }

    /**
     * Adds a word to the list without saving (used during initial load).
     *
     * @param word The word to be added.
     */
    public void addWordWithoutSaving(Word word) {
        words.add(word);
    }
}
