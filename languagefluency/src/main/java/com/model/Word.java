/**
 * Represents a word with its properties such as text, definition, difficulty level, primary translation, and a list of additional translations.
 */
package com.model;


import java.util.ArrayList;
import java.util.UUID;

public class Word {

    private final UUID id; // Unique identifier for each word
    private String translation;
    private String wordText;
    private String definition;
    private final ArrayList<String> translations = new ArrayList<>();
    private String difficulty;

    /**
     * Constructs a Word object with specified properties.
     *
     * @param wordText    the text of the word
     * @param definition  the definition or meaning of the word
     * @param difficulty  the difficulty level of the word
     * @param translation the primary translation of the word
     */
    public Word(UUID id, String wordText, String definition, String difficulty, String translation) {
        this.id = id != null ? id : UUID.randomUUID();
        this.wordText = wordText;
        this.definition = definition;
        this.difficulty = difficulty;
        this.translation = translation;
    }

    /**
     * Gets the unique identifier for the word
     *
     * @return the UUID of the word
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the primary translation of the word
     *
     * @return the primary translation
     */
    public String getTranslation() {
        return this.translation;
    }

    /**
     * Gets the text of the word
     *
     * @return the text of the word
     */
    public String getWordText() {
        return wordText;
    }
    
    /**
     * Sets the text of the word
     *
     * @param wordText the new text for the word
     */
    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    /**
     * Gets the definition of the word
     *
     * @return the definition of the word
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Sets the definition of the word.
     *
     * @param definition the new definition for the word
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * Gets the list of additional translations for the word.
     *
     * @return the list of translations
     */
    public ArrayList<String> getTranslations() {
        return translations;
    }

    /**
     * Adds a new translation to the list if it does not already exist.
     *
     * @param translation the translation to add
     */
    public void addTranslation(String translation) {
        if (!translations.contains(translation)) {
            translations.add(translation);
        }
    }

    /**
     * Removes a translation from the list if it exists.
     *
     * @param translation the translation to remove
     */
    public void removeTranslation(String translation) {
        translations.remove(translation);
    }

    /**
     * Gets the difficulty level of the word.
     *
     * @return the difficulty level
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty level of the word.
     *
     * @param difficulty the new difficulty level
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Returns a string representation of the word, including its text, definition, and translations.
     *
     * @return a string describing the word
     */
    @Override
    public String toString() {
        return "Word: " + wordText + "\nDefinition: " + definition + "\nTranslations: " + translations;
    }
}