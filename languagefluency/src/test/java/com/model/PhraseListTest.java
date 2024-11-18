package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PhraseListTest {

    private PhraseList phraseList;
    private Phrase phrase1;
    private Phrase phrase2;

    @Before
    public void setUp() {
        phraseList = new PhraseList();
        phrase1 = new Phrase("Hola", "Hello");
        phrase2 = new Phrase("Gracias", "Thank you");
    }

    @Test
    public void testConstructor() {
        List<Phrase> phrases = phraseList.getAllPhrases();
        assertNotNull("Phrase list should be initialized", phrases);
        assertEquals("Phrase list should be empty on initialization", 0, phrases.size());
    }

    @Test
    public void testAddPhrase() {
        phraseList.addPhrase(phrase1);
        List<Phrase> phrases = phraseList.getAllPhrases();
        assertEquals("Phrase list should contain 1 phrase after adding", 1, phrases.size());
        assertEquals("Added phrase should match", phrase1, phrases.get(0));

        phraseList.addPhrase(phrase2);
        assertEquals("Phrase list should contain 2 phrases after adding", 2, phrases.size());
        assertEquals("Second added phrase should match", phrase2, phrases.get(1));
    }

    @Test
    public void testGetRandomPhrase_NotEmptyList() {
        phraseList.addPhrase(phrase1);
        phraseList.addPhrase(phrase2);
        Phrase randomPhrase = phraseList.getRandomPhrase();
        assertNotNull("Randomly retrieved phrase should not be null when list is not empty", randomPhrase);
        assertTrue("Randomly retrieved phrase should be in the list", 
                phraseList.getAllPhrases().contains(randomPhrase));
    }

    @Test
    public void testGetRandomPhrase_EmptyList() {
        Phrase randomPhrase = phraseList.getRandomPhrase();
        assertNull("Randomly retrieved phrase should be null when list is empty", randomPhrase);
    }

    @Test
    public void testGetAllPhrases() {
        phraseList.addPhrase(phrase1);
        phraseList.addPhrase(phrase2);
        List<Phrase> allPhrases = phraseList.getAllPhrases();
        
        assertEquals("All phrases should return the full list of added phrases", 2, allPhrases.size());
        assertTrue("List should contain phrase1", allPhrases.contains(phrase1));
        assertTrue("List should contain phrase2", allPhrases.contains(phrase2));
    }
}
