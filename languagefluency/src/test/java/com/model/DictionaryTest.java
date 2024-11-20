package com.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Dictionary class.
 */
public class DictionaryTest {

    private Dictionary dictionary;
    private WordsList wordsList;
    private Word word1;
    private Word word2;

    @Before
    public void setUp() {
        // Initialize WordsList with some words
        wordsList = new WordsList();
        word1 = new Word("Hola", "Hello", "Easy", "Hello");
        word2 = new Word("Adiós", "Goodbye", "Easy", "Goodbye");
        wordsList.addWord(word1);
        wordsList.addWord(word2);

        // Initialize Dictionary with the WordsList
        dictionary = new Dictionary(wordsList);
    }

    // Test translate(String word)

    @Test
    public void testTranslate_WordExists() {
        String translation = dictionary.translate("Hola");
        assertEquals("hello", translation);
    }

    @Test
    public void testTranslate_WordDoesNotExist() {
        String translation = dictionary.translate("Gracias");
        assertEquals("Translation not found!", translation);
    }

    @Test
    public void testTranslate_CaseInsensitive() {
        String translation = dictionary.translate("hOlA");
        assertEquals("hello", translation);
    }

    @Test
    public void testTranslate_NullInput() {
        String translation = dictionary.translate((String) null);
        assertEquals("Translation not found!", translation);
    }

    // Test translate(List<String> words)

    @Test
    public void testTranslate_ListOfWords_AllExist() {
        List<String> words = Arrays.asList("Hola", "Adiós");
        Map<String, String> translations = dictionary.translate(words);
        Map<String, String> expected = new HashMap<>();
        expected.put("Hola", "hello");
        expected.put("Adiós", "goodbye");
        assertEquals(expected, translations);
    }

    @Test
    public void testTranslate_ListOfWords_SomeDoNotExist() {
        List<String> words = Arrays.asList("Hola", "Gracias");
        Map<String, String> translations = dictionary.translate(words);
        Map<String, String> expected = new HashMap<>();
        expected.put("Hola", "hello");
        expected.put("Gracias", "Translation not found!");
        assertEquals(expected, translations);
    }

    @Test
    public void testTranslate_ListOfWords_EmptyList() {
        List<String> words = Arrays.asList();
        Map<String, String> translations = dictionary.translate(words);
        assertTrue(translations.isEmpty());
    }

    @Test
    public void testTranslate_ListOfWords_NullList() {
        Map<String, String> translations = dictionary.translate((List<String>) null);
        assertNotNull(translations);
        assertTrue(translations.isEmpty());
    }

    // Test addTranslation(Word word)

    @Test
    public void testAddTranslation_NewWord() {
        Word newWord = new Word("Gracias", "Thank you", "Easy", "Thank you");
        dictionary.addTranslation(newWord);
        String translation = dictionary.translate("Gracias");
        assertEquals("thank you", translation);
    }

    @Test
    public void testAddTranslation_ExistingWord() {
        Word newWord = new Word("Hola", "Hi", "Easy", "Hi");
        dictionary.addTranslation(newWord);
        String translation = dictionary.translate("Hola");
        assertEquals("hi", translation); // Should update the translation
    }

    @Test
    public void testAddTranslation_NullWord() {
        dictionary.addTranslation(null);
        // Ensure that adding null does not cause errors
        // The word count should remain the same
        assertEquals(2, dictionary.getWordCount());
    }

    // Test removeTranslation(String wordText)

    @Test
    public void testRemoveTranslation_WordExists() {
        dictionary.removeTranslation("Hola");
        String translation = dictionary.translate("Hola");
        assertEquals("Translation not found!", translation);
        assertEquals(1, dictionary.getWordCount());
    }

    @Test
    public void testRemoveTranslation_WordDoesNotExist() {
        dictionary.removeTranslation("Gracias");
        // Ensure that removing a non-existent word does not cause errors
        assertEquals(2, dictionary.getWordCount());
    }

    @Test
    public void testRemoveTranslation_NullInput() {
        dictionary.removeTranslation(null);
        // Ensure that removing null does not cause errors
        assertEquals(2, dictionary.getWordCount());
    }

    // Test getWordCount()

    @Test
    public void testGetWordCount() {
        assertEquals(2, dictionary.getWordCount());
    }

    @Test
    public void testGetWordCount_AfterAddAndRemove() {
        Word newWord = new Word("Gracias", "Thank you", "Easy", "Thank you");
        dictionary.addTranslation(newWord);
        assertEquals(3, dictionary.getWordCount());
        dictionary.removeTranslation("Adiós");
        assertEquals(2, dictionary.getWordCount());
    }

    // Test getAllTranslations()

    @Test
    public void testGetAllTranslations() {
        Map<String, String> translations = dictionary.getAllTranslations();
        Map<String, String> expected = new HashMap<>();
        expected.put("hola", "hello");
        expected.put("adiós", "goodbye");
        assertEquals(expected, translations);
    }

    @Test
    public void testGetAllTranslations_AfterAddAndRemove() {
        dictionary.addTranslation(new Word("Gracias", "Thank you", "Easy", "Thank you"));
        dictionary.removeTranslation("Adiós");
        Map<String, String> translations = dictionary.getAllTranslations();
        Map<String, String> expected = new HashMap<>();
        expected.put("hola", "hello");
        expected.put("gracias", "thank you");
        assertEquals(expected, translations);
    }
}
