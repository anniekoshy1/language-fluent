/**
 * Represents a phrase in the language learning system, including the phrase text, definition, and a list of synonyms. 
 * Each phrase has a unique identifier.
 */
package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Phrase {

    private final UUID id;
    private String phraseText;
    private String definition;
    private final ArrayList<String> synonyms;

    /**
     * Constructs a new Phrase with the specified text and definition.
     * @param phraseText the text of the phrase
     * @param definition the definition or meaning of the phrase
     */
    public Phrase(String phraseText, String definition) {
        this.id = UUID.randomUUID();
        this.phraseText = phraseText;
        this.definition = definition;
        this.synonyms = new ArrayList<>();
    }

    /**
     * Gets the unique identifier for this phrase.
     * @return the UUID of the phrase
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the text of the phrase.
     * @return the phrase text
     */
    public String getPhraseText() {
        return phraseText;
    }

    /**
     * Sets the text of the phrase.
     * @param phraseText the new text for the phrase
     */
    public void setPhraseText(String phraseText) {
        this.phraseText = phraseText;
    }

    /**
     * Gets the definition of the phrase.
     * @return the definition of the phrase
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Sets the definition of the phrase.
     * @param definition the new definition for the phrase
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * Gets the list of synonyms for this phrase.
     * @return the list of synonyms
     */
    public ArrayList<String> getSynonyms() {
        return synonyms;
    }

    /**
     * Adds a synonym to the list if it does not already exist.
     * @param synonym the synonym to add
     */
    public void addSynonym(String synonym) {
        if (!synonyms.contains(synonym)) {
            synonyms.add(synonym);
        }
    }

    /**
     * Removes a synonym from the list if it exists.
     * @param synonym the synonym to remove
     */
    public void removeSynonym(String synonym) {
        synonyms.remove(synonym);
    }
    
    /**
     * Returns a string representation of the phrase, including the text, definition, and synonyms.
     * @return a string describing the phrase
     */
    @Override
    public String toString() {
        return "Phrase: " + phraseText + "\nDefinition: " + definition + "\nSynonyms: " + synonyms;
    }
}