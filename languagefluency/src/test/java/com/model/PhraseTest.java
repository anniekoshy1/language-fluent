package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class PhraseTest {

    private Phrase phrase;
    private String initialText;
    private String initialDefinition;

    @Before
    public void setUp() {
        initialText = "Hola";
        initialDefinition = "Hello";
        phrase = new Phrase(initialText, initialDefinition);
    }

    @Test
    public void testConstructor() {
        assertNotNull("Phrase ID should not be null", phrase.getId());
        assertEquals("Phrase text should match initial text", initialText, phrase.getPhraseText());
        assertEquals("Phrase definition should match initial definition", initialDefinition, phrase.getDefinition());
        assertTrue("Synonyms list should be initialized as empty", phrase.getSynonyms().isEmpty());
    }

    @Test
    public void testSetAndGetPhraseText() {
        String newText = "Buenos días";
        phrase.setPhraseText(newText);
        assertEquals("Phrase text should be updated", newText, phrase.getPhraseText());
    }

    @Test
    public void testSetAndGetDefinition() {
        String newDefinition = "Good morning";
        phrase.setDefinition(newDefinition);
        assertEquals("Phrase definition should be updated", newDefinition, phrase.getDefinition());
    }

    @Test
    public void testAddSynonym_NewSynonym() {
        String synonym = "Hi";
        phrase.addSynonym(synonym);
        assertTrue("Synonyms list should contain the added synonym", phrase.getSynonyms().contains(synonym));
    }

    @Test
    public void testAddSynonym_DuplicateSynonym() {
        String synonym = "Hi";
        phrase.addSynonym(synonym);
        int initialSize = phrase.getSynonyms().size();
        phrase.addSynonym(synonym);
        assertEquals("Adding a duplicate synonym should not increase list size", initialSize, phrase.getSynonyms().size());
    }

    @Test
    public void testRemoveSynonym_ExistingSynonym() {
        String synonym = "Hello";
        phrase.addSynonym(synonym);
        phrase.removeSynonym(synonym);
        assertFalse("Synonyms list should not contain the removed synonym", phrase.getSynonyms().contains(synonym));
    }

    @Test
    public void testRemoveSynonym_NonExistingSynonym() {
        String synonym = "Hey";
        int initialSize = phrase.getSynonyms().size();
        phrase.removeSynonym(synonym);
        assertEquals("Removing a non-existing synonym should not affect list size", initialSize, phrase.getSynonyms().size());
    }

    @Test
    public void testGetId() {
        UUID id = phrase.getId();
        assertNotNull("Phrase ID should not be null", id);
    }

    @Test
    public void testToString() {
        String synonym = "Hi";
        phrase.addSynonym(synonym);
        String expectedString = "Phrase: " + initialText + "\nDefinition: " + initialDefinition + "\nSynonyms: [Hi]";
        assertEquals("String representation should match expected format", expectedString, phrase.toString());
    }
}
