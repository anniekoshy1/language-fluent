package com.model;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class WordsListTest {

    private WordsList wordsList;
    private Word word1;
    private Word word2;

    @Before
    public void setUp() {
        wordsList = new WordsList();
        word1 = new Word("hello", "a greeting", "easy", "hola");
        word2 = new Word("book", "a set of pages", "medium", "libro");
    }

    // Test addWord
    @Test
    public void testAddWord_NewWord() {
        int initialSize = wordsList.getAllWords().size();
        wordsList.addWord(word1);
        assertEquals(initialSize + 1, wordsList.getAllWords().size());
        assertTrue("Words list should contain word1 after addition", wordsList.getAllWords().contains(word1));
    }

    @Test //bug Words list should ignore null entries expected:[0] but was:[1]
    public void testAddWord_DuplicateWord() {
        wordsList.addWord(word1);
        int initialSize = wordsList.getAllWords().size();
        wordsList.addWord(word1); // Attempt to add duplicate //Words list should ignore null entries expected:[0] but was:[1]
        assertEquals("Words list should not allow duplicate additions", initialSize, wordsList.getAllWords().size());
    }

    @Test //bug Words list should ignore null entries expected:[0] but was:[1]
    public void testAddWord_NullWord() {
        int initialSize = wordsList.getAllWords().size();
        wordsList.addWord(null); // Attempt to add null
        assertEquals("Words list should ignore null entries", initialSize, wordsList.getAllWords().size());
    }

    // Test getRandomWord
    @Test
    public void testGetRandomWord_NonEmptyList() {
        wordsList.addWord(word1);
        wordsList.addWord(word2);
        Word randomWord = wordsList.getRandomWord();
        assertNotNull("Random word should not be null when list is non-empty", randomWord);
        assertTrue("Random word should be in the words list", wordsList.getAllWords().contains(randomWord));
    }

    @Test
    public void testGetRandomWord_EmptyList() {
        assertNull("Random word should be null when list is empty", wordsList.getRandomWord());
    }

    // Test getAllWords
    @Test
    public void testGetAllWords_EmptyList() {
        assertTrue("Initial words list should be empty", wordsList.getAllWords().isEmpty());
    }

    @Test
    public void testGetAllWords_NonEmptyList() {
        wordsList.addWord(word1);
        wordsList.addWord(word2);
        List<Word> allWords = wordsList.getAllWords();
        assertEquals("Words list size should match the number of added words", 2, allWords.size());
        assertTrue("Words list should contain word1", allWords.contains(word1));
        assertTrue("Words list should contain word2", allWords.contains(word2));
    }
}
