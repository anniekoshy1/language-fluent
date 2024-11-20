package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the LanguageList class.
 */
public class LanguageListTest {

    private LanguageList languageList;
    private Language spanish;
    private Language french;
    private Language italian;

    @Before
    public void setUp() {
        // Reset singleton instance before each test
        languageList = LanguageList.getInstance();
        languageList.getLanguages().clear();

        // Initialize test languages with UUIDs
        spanish = new Language(UUID.randomUUID(), "Spanish");
        french = new Language(UUID.randomUUID(), "French");
        italian = new Language(UUID.randomUUID(), "Italian");
    }

    // Test addLanguage method with a new language
    @Test
    public void testAddLanguage_NewLanguage() {
        languageList.addLanguage(spanish);
        assertEquals(1, languageList.getLanguages().size());
        assertEquals("Spanish", languageList.getLanguages().get(0).getName());
    }

    // Test addLanguage with duplicate language (same instance)
    @Test
    public void testAddLanguage_DuplicateLanguage() {
        languageList.addLanguage(spanish);
        languageList.addLanguage(spanish); // Attempting to add the same instance again
        assertEquals(2, languageList.getLanguages().size());
    }

    // Test removeLanguage method with existing language
    @Test
    public void testRemoveLanguage_ExistingLanguage() {
        languageList.addLanguage(spanish);
        assertTrue(languageList.removeLanguage(spanish));
        assertTrue(languageList.getLanguages().isEmpty());
    }

    // Test removeLanguage with non-existing language
    @Test
    public void testRemoveLanguage_NonExistingLanguage() {
        assertFalse(languageList.removeLanguage(french));
        assertTrue(languageList.getLanguages().isEmpty());
    }

    // Test getLanguages when list is populated
    @Test
    public void testGetLanguages_PopulatedList() {
        languageList.addLanguage(spanish);
        languageList.addLanguage(french);
        ArrayList<Language> languages = languageList.getLanguages();
        assertEquals(2, languages.size());
        assertEquals("Spanish", languages.get(0).getName());
        assertEquals("French", languages.get(1).getName());
    }

    // Test getLanguages when list is empty
    @Test
    public void testGetLanguages_EmptyList() {
        assertTrue(languageList.getLanguages().isEmpty());
    }

    // Test findLanguageByName with existing language name
    @Test
    public void testFindLanguageByName_ExistingName() {
        languageList.addLanguage(spanish);
        languageList.addLanguage(french);
        Language result = languageList.findLanguageByName("Spanish");
        assertNotNull(result);
        assertEquals("Spanish", result.getName());
    }

    // Test findLanguageByName with non-existing language name
    @Test
    public void testFindLanguageByName_NonExistingName() {
        languageList.addLanguage(spanish);
        assertNull(languageList.findLanguageByName("German"));
    }

    // Test findLanguageByName with case-insensitive match
    @Test
    public void testFindLanguageByName_CaseInsensitive() {
        languageList.addLanguage(french);
        Language result = languageList.findLanguageByName("french");
        assertNotNull(result);
        assertEquals("French", result.getName());
    }

    // Test singleton instance behavior
    @Test
    public void testSingletonInstance() {
        LanguageList instance1 = LanguageList.getInstance();
        LanguageList instance2 = LanguageList.getInstance();
        assertSame(instance1, instance2); // Both references should point to the same instance
    }
}
