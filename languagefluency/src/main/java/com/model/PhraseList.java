/**
 * Manages a list of phrases in the language learning system. 
 * Provides functionality to add phrases, retrieve a random phrase, and retrieve all phrases.
 */
package com.model;

import java.util.ArrayList;
import java.util.List;

public class PhraseList {
    private final List<Phrase> phrases;
    private static PhraseList phraseList;

    /**
     * Constructs a new, empty PhraseList
     */
    private PhraseList() {
        this.phrases = new ArrayList<>();
    }

    public static PhraseList getInstance() {
        if (phraseList == null) {
            phraseList = new PhraseList();
        }
        return phraseList;
    }

    public void loadPhrase(){
        DataLoader.loadPhrases();
    }

    /**
     * Adds a phrase to the list
     * @param phrase the Phrase object to add
     */
    public void addPhrase(Phrase phrase) {
        phrases.add(phrase);
    }

    /**
     * Gets a random phrase from the list
     * @return a randomly selected Phrase, or null if the list is empty
     */
    public Phrase getRandomPhrase() {
        if (phrases.isEmpty()) return null;
        int randomIndex = (int) (Math.random() * phrases.size());
        return phrases.get(randomIndex);
    }

    /**
     * Retrieves all phrases in the list
     * @return a list of all Phrase objects
     */
    public List<Phrase> getAllPhrases() {
        return phrases;
    }

    /**
     * Saves the list of phrases to the JSON file.
     */
    public void savePhrases() {
        DataWriter.savePhrases();
    }
}