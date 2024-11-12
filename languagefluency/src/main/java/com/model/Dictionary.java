/**
 * A dictionary that provides translations for words, allowing addition, removal, and retrieval of translations based on a WordsList
 */
package com.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {

    private final WordsList wordsList;
    private final Map<String, String> translationMap;

    /**
     * Constructs a Dictionary using a provided WordsList, initializing the translation map with translations from the WordsList.
     * @param wordsList the WordsList containing words and their translations
     */
    public Dictionary(WordsList wordsList) {
        this.wordsList = wordsList;
        this.translationMap = new HashMap<>();

        for (Word word : wordsList.getAllWords()) {
            translationMap.put(word.getWordText().toLowerCase(), word.getTranslation().toLowerCase());
        }
    }

    /**
     * Translates a single word from the source language to the target language.
     * @param word the word to be translated
     * @return the translated word if found, or "Translation not found!" if not
     */
    public String translate(String word) {
        word = word.toLowerCase();
        return translationMap.getOrDefault(word, "Translation not found!");
    }

    /**
     * Translates a list of words from the source language to the target language.
     * @param words the list of words to be translated
     * @return a map of each word and its corresponding translation
     */
    public Map<String, String> translate(List<String> words) {
        Map<String, String> translations = new HashMap<>();
        for (String word : words) {
            translations.put(word, translate(word));
        }
        return translations;
    }

    /**
     * Adds a new word and its translation to the dictionary.
     * @param word the Word object containing the word text and its translation
     */
    public void addTranslation(Word word) {
        wordsList.addWord(word);
        translationMap.put(word.getWordText().toLowerCase(), word.getTranslation().toLowerCase());
    }

    /**
     * Removes a word and its translation from the dictionary.
     * @param wordText the word text to be removed
     */
    public void removeTranslation(String wordText) {
        String finalWordText = wordText.toLowerCase();
        List<Word> words = wordsList.getAllWords();
        words.removeIf(word -> word.getWordText().equalsIgnoreCase(finalWordText));
        translationMap.remove(finalWordText);
    }

    /**
     * Retrieves the total number of words in the dictionary.
     * @return the number of words in the dictionary
     */
    public int getWordCount() {
        return wordsList.getAllWords().size();
    }

    /**
     * Retrieves all translations in the dictionary
     * @return a copy of the translation map containing all word translations
     */
    public Map<String, String> getAllTranslations() {
        return new HashMap<>(translationMap);
    }
}