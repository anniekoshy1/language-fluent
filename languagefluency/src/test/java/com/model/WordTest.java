package com.model;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class WordTest {

    private Word word;

    @Before
    public void setUp() {
        word = new Word("hello", "a greeting", "easy", "hola");
    }

    // Test getId
    @Test
    public void testGetId() {
        assertNotNull("ID should not be null", word.getId());
        assertTrue("ID should be a UUID instance", word.getId() instanceof UUID);
    }

    // Test getTranslation
    @Test
    public void testGetTranslation() {
        assertEquals("Primary translation should be 'hola'", "hola", word.getTranslation());
    }

    // Test getWordText
    @Test
    public void testGetWordText() {
        assertEquals("Word text should be 'hello'", "hello", word.getWordText());
    }

    // Test setWordText
    @Test
    public void testSetWordText() {
        word.setWordText("hi");
        assertEquals("Word text should be updated to 'hi'", "hi", word.getWordText());
    }

    // Test getDefinition
    @Test
    public void testGetDefinition() {
        assertEquals("Definition should be 'a greeting'", "a greeting", word.getDefinition());
    }

    // Test setDefinition
    @Test
    public void testSetDefinition() {
        word.setDefinition("an informal greeting");
        assertEquals("Definition should be updated to 'an informal greeting'", "an informal greeting", word.getDefinition());
    }

    // Test getTranslations
    @Test
    public void testGetTranslations_EmptyInitially() {
        assertTrue("Translations list should be initially empty", word.getTranslations().isEmpty());
    }

    // Test addTranslation
    @Test
    public void testAddTranslation_NewTranslation() {
        word.addTranslation("saludo");
        assertEquals("Translations list should contain one translation", 1, word.getTranslations().size());
        assertTrue("Translations list should contain 'saludo'", word.getTranslations().contains("saludo"));
    }

    @Test
    public void testAddTranslation_DuplicateTranslation() {
        word.addTranslation("saludo");
        word.addTranslation("saludo"); // Attempt to add duplicate
        assertEquals("Translations list should contain only one instance of 'saludo'", 1, word.getTranslations().size());
    }

    // Test removeTranslation
    @Test
    public void testRemoveTranslation_ExistingTranslation() {
        word.addTranslation("saludo");
        word.removeTranslation("saludo");
        assertFalse("Translations list should not contain 'saludo' after removal", word.getTranslations().contains("saludo"));
    }

    @Test
    public void testRemoveTranslation_NonExistingTranslation() {
        int initialSize = word.getTranslations().size();
        word.removeTranslation("saludo"); // Non-existing translation
        assertEquals("Translations list size should remain unchanged when removing non-existing translation", initialSize, word.getTranslations().size());
    }

    // Test getDifficulty
    @Test
    public void testGetDifficulty() {
        assertEquals("Difficulty should be 'easy'", "easy", word.getDifficulty());
    }

    // Test setDifficulty
    @Test
    public void testSetDifficulty() {
        word.setDifficulty("medium");
        assertEquals("Difficulty should be updated to 'medium'", "medium", word.getDifficulty());
    }

    // Test toString
    @Test
    public void testToString() {
        word.addTranslation("saludo");
        String expectedString = "Word: hello\nDefinition: a greeting\nTranslations: [saludo]";
        assertEquals("String representation should match the expected format", expectedString, word.toString());
    }
}
